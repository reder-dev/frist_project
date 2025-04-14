package com.itwill.approval.service;

import com.itwill.approval.dto.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ApprovalService {
	void saveApprovalRequest(
			ApprovalApplyDTO dto, 
			List<MultipartFile> files, 
			LeaveReqDTO leaveReq,
		    BusinessReqDTO businessReq);

	List<ApprovalSearchDTO> searchApprovers(String name);

	// 결재선 템플릿 저장
	void saveApprovalLineTemplate(ApprovalLineTemplateDTO templateDTO, List<ApprovalLineDetailDTO> detailList);

	// 결재선 템플릿 목록 조회
	List<ApprovalLineTemplateListDTO> getTemplatesByOwner(String ownerId);

	// 결재선 템플릿 상세 결재자 목록 조회
	List<ApprovalLineDetailDTO> getTemplateDetails(String templateId);

	// 템플릿 이름이 이미 존재하는지 여부 반환 (true = 중복)
	boolean isTemplateNameDuplicate(String name);

	// 소유자 ID로 템플릿과 상세 결재자 목록을 함께 조회
	List<ApprovalLineTemplateListDTO> getTemplatesWithDetails(String ownerId);

	// APPROVAL_LINE 테이블에 insert
	void insertApprovalLine(ApprovalLineDTO lineDTO);
	
	///////////////// 관리자 //////////////////
	// 결재 대기 목록 조회
    List<PendingApprovalDTO> getPendingApprovals(String approverId);
	
    // 팝업 내용
    ApprovalDetailDTO getApprovalDetail(String documentId);
    
    FileDTO getFileById(String fileId);
    
    // 1. 결재 결과 저장 (APPROVAL_APP에 insert)
    void saveApprovalResult(ApprovalAppDTO dto);
    
    // 2. 각 결재자 처리 상태
    void updateLineStatus(String lineId, String status);
    
    // 3. 다음 결재자 활성화 (APPROVAL_LINE 상태 '대기중')
    void activateNextApprover(String documentId, int nextOrder);

    // 4. 최종 결재 완료 시 LEAVE or BUSINESS 확정 처리
    void processFinalApprovalIfNeeded(String approvalDocumentId);

    // 5. LEAVE 확정 처리
    void confirmLeaveApproval(String leaveId);

    // 6. BUSINESS 확정 처리
    void confirmBusinessApproval(String tripId);
    
    int countApprovalByPrefix(String prefix);
    
    List<PendingApprovalDTO> getMyRequestedDocuments(String empId);
    
}