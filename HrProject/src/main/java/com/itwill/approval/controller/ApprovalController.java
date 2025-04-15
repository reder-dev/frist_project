package com.itwill.approval.controller;

import com.itwill.approval.dto.*;
import com.itwill.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {

	private final ApprovalService approvalService;

	// 결재 신청 폼 페이지로 이동
	@GetMapping("/apply")
	public String showApprovalForm() {
		return "approval/approval-apply";
	}

	// 결재 신청 처리
	@PostMapping("/apply")
	public String submitApprovalRequest(
	    HttpServletRequest request,
	    @RequestParam("attachmentFiles") List<MultipartFile> files
	) {
	    // 기본 결재 신청 DTO 세팅
	    ApprovalApplyDTO dto = new ApprovalApplyDTO();

	    // 코멘트 저장
	    dto.setRequestComment(request.getParameter("comment"));

	    String empId = request.getParameter("requester");
	    String documentTitle = request.getParameter("documentTitle");
	    String referenceTableName = request.getParameter("referenceTableName");
	    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	    // 공통 변수
	    String typeCode = "";
	    String approvalId = "";
	    LeaveReqDTO leave = null;
	    BusinessReqDTO business = null;

	    // 휴가일 경우
	    if ("LEAVE".equals(referenceTableName)) {
	        String leaveStatus = request.getParameter("leaveStatus");

	        if ("반차".equals(leaveStatus)) typeCode = "01";
	        else if ("연차".equals(leaveStatus)) typeCode = "02";
	        else if ("병가".equals(leaveStatus)) typeCode = "03";
	        else typeCode = "00";

	        String prefix = empId + today + typeCode;
	        int count = approvalService.countApprovalByPrefix(prefix);
	        approvalId = prefix + String.format("%03d", count + 1);

	        // 휴가 신청 DTO 세팅
	        leave = new LeaveReqDTO();
	        leave.setLeaveId(approvalId);  // 반드시 세팅!
	        leave.setEmpId(empId);         // 사번도 필요
	        leave.setLeaveStatus(leaveStatus);
	        leave.setLeaveStartDate(LocalDate.parse(request.getParameter("leaveStartDate")));
	        leave.setLeaveEndDate(LocalDate.parse(request.getParameter("leaveEndDate")));
	        int days = (int) (leave.getLeaveEndDate().toEpochDay() - leave.getLeaveStartDate().toEpochDay()) + 1;
	        leave.setLeaveDays(days);
	    }

	    // 출장일 경우
	    else if ("BUSINESS".equals(referenceTableName)) {
	        typeCode = "04";

	        String prefix = empId + today + typeCode;
	        int count = approvalService.countApprovalByPrefix(prefix);
	        approvalId = prefix + String.format("%03d", count + 1);

	        // 출장 신청 DTO 세팅
	        business = new BusinessReqDTO();
	        business.setBusinessTripId(approvalId); // 반드시 세팅!
	        business.setEmpId(empId);               // 사번도 필요
	        business.setTripLocation(request.getParameter("businessLocation"));
	        business.setBusinessTripPurpose(request.getParameter("businessPurpose"));
	        business.setBusinessTripStart(LocalDate.parse(request.getParameter("businessStartDate")));
	        business.setBusinessTripEnd(LocalDate.parse(request.getParameter("businessEndDate")));
	    }

	    // 공통 결재 정보 세팅
	    dto.setApprovalDocumentId(approvalId);     // 최종 문서 ID
	    dto.setDocumentTitle(documentTitle);
	    dto.setReferenceTableName(referenceTableName);
	    dto.setReferenceId(approvalId);            // 반드시 실제 문서 PK로 넣어야 함!
	    dto.setRequester(empId);
	    dto.setRegister(empId);
	    dto.setAttachmentCount(files != null ? files.size() : 0);
	    dto.setApproverList(request.getParameter("approverList"));

	    System.out.println("📌 최종 DTO 확인: " + dto);

	    // 결재 신청 저장 처리
	    approvalService.saveApprovalRequest(dto, files, leave, business);

	    return "redirect:/approval/apply";
	}

	// 결재자 이름 검색 (AJAX)
	@GetMapping("/search-approvers")
	@ResponseBody
	public List<ApprovalSearchDTO> searchApprovers(@RequestParam("keyword") String keyword) {
		return approvalService.searchApprovers(keyword);
	}

	// 결재선 템플릿 저장 처리
	@PostMapping("/template/save")
	@ResponseBody
	public String saveApprovalTemplate(@RequestBody ApprovalTemplateRequestDTO requestDTO) {
		ApprovalLineTemplateDTO templateDTO = new ApprovalLineTemplateDTO();
		templateDTO.setTemplateId(requestDTO.getTemplateId());
		templateDTO.setTemplateName(requestDTO.getTemplateName());
		templateDTO.setOwnerId(requestDTO.getOwnerId());

		approvalService.saveApprovalLineTemplate(templateDTO, requestDTO.getDetailList());

		return "success";
	}

	// 결재선 템플릿 + 상세 결재자 정보 조회
	@GetMapping("/template/list-full")
	@ResponseBody
	public List<ApprovalLineTemplateListDTO> getTemplatesWithDetails(@RequestParam("ownerId") String ownerId) {
		return approvalService.getTemplatesWithDetails(ownerId);
	}

	// 템플릿 이름 중복 체크
	@GetMapping("/template/check-name")
	@ResponseBody
	public boolean checkTemplateName(@RequestParam("name") String name) {
		return approvalService.isTemplateNameDuplicate(name);
	}

	// 로그인 사용자의 템플릿 목록 조회
	@GetMapping("/template/list")
	@ResponseBody
	public List<ApprovalLineTemplateListDTO> getTemplatesByOwner(@RequestParam("ownerId") String ownerId) {
		return approvalService.getTemplatesByOwner(ownerId);
	}

	// 템플릿 ID로 결재자 목록 조회
	@GetMapping("/template/detail")
	@ResponseBody
	public List<ApprovalLineDetailDTO> getTemplateDetails(@RequestParam("templateId") String templateId) {
		return approvalService.getTemplateDetails(templateId);
	}
	
	@GetMapping("/my-documents")
	@ResponseBody
	public List<PendingApprovalDTO> getMyDocuments(HttpSession session) {
	    ApprovalSearchDTO loginUser = (ApprovalSearchDTO) session.getAttribute("loginUser");
	    if (loginUser == null) return Collections.emptyList();
	    return approvalService.getMyRequestedDocuments(loginUser.getEmpId());
	}
}