package com.itwill.approval.controller;

import com.itwill.approval.dto.*;
import com.itwill.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalAdminController {
	private final ServletContext servletContext;
	private final ApprovalService approvalService;

	@GetMapping("/admin")
	public String showAdminPage() {
		return "approval/approval-admin-inbox";
	}

	// 로그인 사용자의 결재 대기 목록 조회 (AJAX용)
	@GetMapping("/inbox")
	@ResponseBody
	public List<PendingApprovalDTO> getPendingApprovals(@RequestParam("empId") String empId) {
	    return approvalService.getPendingApprovals(empId);
	}

	// 상세 팝업 HTML 반환 (AJAX용)
	@GetMapping(value = "/detail", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getApprovalDetail(@RequestParam("documentId") String documentId) {
	    ApprovalDetailDTO detail = approvalService.getApprovalDetail(documentId);

	    StringBuilder html = new StringBuilder();
	    html.append("<h3>").append(detail.getDocumentTitle() != null ? detail.getDocumentTitle() : "-").append("</h3>");

	    if ("LEAVE".equals(detail.getReferenceTableName())) {
	        html.append("<p><strong>휴가 구분:</strong> ")
	            .append(detail.getLeaveStatus() != null ? detail.getLeaveStatus() : "-")
	            .append("</p>");

	        html.append("<p><strong>휴가 기간:</strong> ")
	            .append(detail.getLeaveStartDate() != null ? detail.getLeaveStartDate() : "-")
	            .append(" ~ ")
	            .append(detail.getLeaveEndDate() != null ? detail.getLeaveEndDate() : "-")
	            .append(" (")
	            .append(detail.getLeaveDays()).append("일)</p>");
	    } else if ("BUSINESS".equals(detail.getReferenceTableName())) {
	        html.append("<p><strong>출장지:</strong> ")
	            .append(detail.getTripLocation() != null ? detail.getTripLocation() : "-")
	            .append("</p>");
	        html.append("<p><strong>출장 목적:</strong> ")
	            .append(detail.getBusinessTripPurpose() != null ? detail.getBusinessTripPurpose() : "-")
	            .append("</p>");
	        html.append("<p><strong>출장 기간:</strong> ")
	            .append(detail.getBusinessTripStart() != null ? detail.getBusinessTripStart() : "-")
	            .append(" ~ ")
	            .append(detail.getBusinessTripEnd() != null ? detail.getBusinessTripEnd() : "-")
	            .append(" (")
	            .append(detail.getTripDays()).append("일)</p>");
	    }

	    html.append("<p><strong>코멘트:</strong> ")
	        .append(detail.getComment() != null ? detail.getComment() : "-")
	        .append("</p>");

	    // 첨부파일 출력
	    if (detail.getFiles() != null && !detail.getFiles().isEmpty()) {
	        html.append("<p><strong>첨부파일:</strong></p><ul>");
	        System.out.println("📂 파일 리스트 크기: " + detail.getFiles().size());
	        for (FileDTO file : detail.getFiles()) {
	        	System.out.println("📄 파일 이름: " + (file != null ? file.getFileName() : "null 객체"));
	            if (file != null) {
	            	html.append("<li><a href='/approval/download?fileId=")
	                .append(file.getFileId())
	                .append("'>")
	                .append(file.getFileName() != null ? file.getFileName() : "(이름 없음)")
	                .append("</a></li>");
	            }
	        }
	        html.append("</ul>");
	    }

	    // 결재선 출력
	    if (detail.getApprovers() != null && !detail.getApprovers().isEmpty()) {
	        html.append("<p><strong>결재선:</strong><br>");
	        for (int i = 0; i < detail.getApprovers().size(); i++) {
	            ApprovalLineWithStatusDTO approver = detail.getApprovers().get(i);
	            html.append((i + 1)).append(". ")
	                .append(approver.getName()).append(" (")
	                .append(approver.getDept()).append(" / ")
	                .append(approver.getPosition()).append(") ")
	                .append("(").append(approver.getStatus()).append(")");

	            if (i < detail.getApprovers().size() - 1) html.append(" → ");
	        }
	        html.append("</p>");
	    }
	    return html.toString();
	}
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("fileId") String fileId) {
	    FileDTO fileDTO = approvalService.getFileById(fileId);
	    if (fileDTO == null || fileDTO.getFilePath() == null) {
	        return ResponseEntity.notFound().build();
	    }
	    String uploadFolderPath = servletContext.getRealPath("/resources/upload/approval");
	    String fileNameOnly = fileDTO.getFilePath().substring(fileDTO.getFilePath().lastIndexOf("/") + 1);
	    String realPath = uploadFolderPath + File.separator + fileNameOnly;

	    File file = new File(realPath);
	    System.out.println("📁 다운로드 실제 경로: " + realPath);

	    if (!file.exists()) {
	        System.out.println("❌ 파일 없음: " + realPath);
	        return ResponseEntity.notFound().build();
	    }

	    Resource resource = new FileSystemResource(file);
	    String fileName = fileDTO.getFileName();
	    
	    // 한글 인코딩
	    String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
	        .header(HttpHeaders.CONTENT_TYPE, fileDTO.getFileType())
	        .body(resource);
	}
	
	@PostMapping("/approve")
	@ResponseBody
	public void approve(@RequestBody ApprovalAppDTO dto, HttpSession session) {
	    dto.setApprovalStatus("승인");

	    ApprovalSearchDTO loginUser = (ApprovalSearchDTO) session.getAttribute("loginUser");
	    if (loginUser != null) {
	        dto.setRegister(loginUser.getEmpId());
	        dto.setModifier(loginUser.getEmpId());
	    }

	    approvalService.saveApprovalResult(dto); // INSERT into APPROVAL_APP
	    approvalService.activateNextApprover(dto.getApprovalDocumentId(), dto.getApprovalOrder() + 1); // 다음 결재자 활성화
	}
	
	@PostMapping("/reject")
	@ResponseBody
	public void reject(@RequestBody ApprovalAppDTO dto, HttpSession session) {
	    dto.setApprovalStatus("반려");

	    ApprovalSearchDTO loginUser = (ApprovalSearchDTO) session.getAttribute("loginUser");
	    if (loginUser != null) {
	        dto.setRegister(loginUser.getEmpId());
	        dto.setModifier(loginUser.getEmpId());
	    }

	    approvalService.saveApprovalResult(dto); // INSERT into APPROVAL_APP
	}

}