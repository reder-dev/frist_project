package com.itwill.approval.controller;

import com.itwill.approval.dto.*;
import com.itwill.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {

	private final ApprovalService approvalService;

	// 결재 신청 폼 이동
	@GetMapping("/apply")
	public String showApprovalForm() {
		return "approval/approval-apply";
	}

	// 결재 신청 처리 (첨부파일 다중 업로드 추가됨)
	@PostMapping("/apply")
	public String submitApprovalRequest(
	    HttpServletRequest request,
	    @RequestParam("attachmentFiles") List<MultipartFile> files
	) {
	    // DTO 직접 생성 + 수동 세팅
	    ApprovalApplyDTO dto = new ApprovalApplyDTO();

	    String empId = request.getParameter("requester");
	    String documentTitle = request.getParameter("documentTitle");
	    String referenceTableName = request.getParameter("referenceTableName");

	    // 날짜 → ID 생성용
	    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	    String typeCode = "04"; // 기본: 출장
	    String referenceId = "business_trip_id";
	    String leaveStatus = request.getParameter("leaveStatus");

	    if ("LEAVE".equals(referenceTableName)) {
	        referenceId = "leave_id";
	        if ("반차".equals(leaveStatus)) typeCode = "01";
	        else if ("연차".equals(leaveStatus)) typeCode = "02";
	        else if ("병가".equals(leaveStatus)) typeCode = "03";
	        else typeCode = "00";
	    }

	    // 생성 ID
	    String approvalId = empId + today + typeCode;

	    // DTO 채우기
	    dto.setApprovalDocumentId(approvalId);
	    dto.setDocumentTitle(documentTitle);
	    dto.setReferenceTableName(referenceTableName);
	    dto.setReferenceId(referenceId);
	    dto.setRequester(empId);
	    dto.setRegister(empId);
	    dto.setAttachmentCount(files != null ? files.size() : 0);

	    System.out.println("📌 최종 DTO 확인: " + dto);

	    // 저장 실행
	    approvalService.saveApprovalRequest(dto, files);

	    return "redirect:/approval/apply";
	}

	// 결재자 검색 (AJAX)
	@GetMapping("/search-approvers")
	@ResponseBody
	public List<ApprovalSearchDTO> searchApprovers(@RequestParam("keyword") String keyword) {
		return approvalService.searchApprovers(keyword);
	}

	// 결재선 템플릿 저장
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

	/**
	 * 템플릿 + 상세 결재자 정보까지 포함된 목록 반환
	 */
	@GetMapping("/template/list-full")
	@ResponseBody
	public List<ApprovalLineTemplateListDTO> getTemplatesWithDetails(@RequestParam("ownerId") String ownerId) {
		return approvalService.getTemplatesWithDetails(ownerId);
	}

	/**
	 * 템플릿 이름 중복 여부를 확인하는 API
	 * 
	 * @param name 사용자가 입력한 템플릿 이름
	 * @return true: 중복 있음, false: 중복 없음
	 */
	@GetMapping("/template/check-name")
	@ResponseBody
	public boolean checkTemplateName(@RequestParam("name") String name) {
		return approvalService.isTemplateNameDuplicate(name);
	}

	// 로그인 사용자 ID로 결재선 목록 조회
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
}