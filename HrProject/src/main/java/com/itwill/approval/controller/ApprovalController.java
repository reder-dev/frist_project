package com.itwill.approval.controller;

import com.itwill.approval.dto.*;
import com.itwill.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
			@ModelAttribute ApprovalApplyDTO dto,
			@RequestParam("attachmentFiles") List<MultipartFile> files,  // ✅ 파일 받기
			HttpServletRequest request
	) {
		String category = dto.getReferenceTableName(); // LEAVE or BUSINESS
		String referenceId = category + "_" + System.currentTimeMillis();

		// 참조 ID 생성 및 설정
		dto.setReferenceId(referenceId);

		// 첨부파일 개수 DTO에 저장
		dto.setAttachmentCount(files != null ? files.size() : 0); // ✅ 첨부파일 수 반영

		// 결재 문서 저장 (문서 + 파일 함께)
		approvalService.saveApprovalRequest(dto, files); // ✅ Service 계층에 파일 전달

		// LEAVE / BUSINESS 테이블 insert는 결재 승인 시 처리할 예정

		return "redirect:/approval/apply"; // 추후 결재 상세 화면 등으로 변경 가능
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
	 * 템플릿 이름 중복 여부를 확인하는 API
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