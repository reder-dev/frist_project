package com.itwill.approval.service;

import com.itwill.approval.dto.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ApprovalService {
	void saveApprovalRequest(ApprovalApplyDTO dto, List<MultipartFile> files);

	List<ApprovalSearchDTO> searchApprovers(String name);

	// 결재선 템플릿 저장
	void saveApprovalLineTemplate(ApprovalLineTemplateDTO templateDTO, List<ApprovalLineDetailDTO> detailList);

	// 휴가 저장
	void saveLeave(LeaveDTO leaveDTO);

	// 출장 저장
	void saveBusiness(BusinessDTO businessDTO);

	// 결재선 템플릿 목록 조회
	List<ApprovalLineTemplateListDTO> getTemplatesByOwner(String ownerId);

	// 결재선 템플릿 상세 결재자 목록 조회
	List<ApprovalLineDetailDTO> getTemplateDetails(String templateId);
	
	// 템플릿 이름이 이미 존재하는지 여부 반환 (true = 중복)
	boolean isTemplateNameDuplicate(String name);

}