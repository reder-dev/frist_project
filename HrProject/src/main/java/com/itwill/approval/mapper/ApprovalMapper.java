package com.itwill.approval.mapper;

import com.itwill.approval.dto.*;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApprovalMapper {
	
	int countApprovalByPrefix(@Param("prefix") String prefix);
	
	// 결재 신청 INSERT
	void insertApprovalRequest(ApprovalApplyDTO dto);

	// 결재자 검색
	List<ApprovalSearchDTO> searchApprovers(@Param("keyword") String keyword);

	// 추가: 결재선 템플릿 저장
	void insertTemplate(ApprovalLineTemplateDTO templateDTO);

	// 추가: 결재선 상세 저장 (결재자 한 명씩)
	void insertTemplateDetail(ApprovalLineDetailDTO detailDTO);

	// 휴가 신청 INSERT
	void insertLeaveReq(LeaveReqDTO leave);

	// 출장 신청 INSERT
	void insertBusinessReq(BusinessReqDTO business);

	// 결재선 템플릿 목록 조회 (owner 기준)
	List<ApprovalLineTemplateListDTO> selectTemplatesByOwner(String ownerId);

	// 결재선 상세 조회 (templateId 기준)
	List<ApprovalLineDetailDTO> selectTemplateDetails(String templateId);

	// 템플릿 이름의 존재 여부를 확인하는 메서드 (중복 체크용)
	int countTemplateName(String templateName);

	// 결재선 템플릿과 포함된 결재자 정보를 함께 조회
	List<ApprovalLineTemplateListDTO> selectTemplatesWithDetails(String ownerId);

	// APPROVAL_LINE 테이블에 insert
	int insertApprovalLine(ApprovalLineDTO lineDTO);

	// 사번으로 직책 조회
	String selectPositionByEmpId(String empId);

	// -------------------- 관리자 -------------------- //
	// 결재 대기 목록 조회
	List<PendingApprovalDTO> selectPendingApprovals(String approverId);
	
	List<ApprovalAppDTO> selectApprovalResults(String approvalDocumentId);
	
	ApprovalSearchDTO selectEmployeeInfo(String empId);
	
	// 첨부파일 조회
	List<FileDTO> selectFilesByReferenceId(String referenceId);

	// 결재선 조회
	List<ApprovalLineDTO> selectApprovalLines(String approvalDocumentId);
	
	ApprovalApplyDTO selectApprovalDocument(String approvalDocumentId);
	
	LeaveReqDTO selectLeaveReqByDocId(String approvalDocumentId);

	BusinessReqDTO selectBusinessReqByDocId(String approvalDocumentId);
	
	FileDTO selectFileById(String fileId);
	
	// 결재 결과 저장
	void insertApprovalResult(ApprovalAppDTO dto);

	// 현재 결재선의 순서 조회
	int selectApprovalOrder(@Param("documentId") String documentId, @Param("approverId") String approverId);
	
	// 결재선 상태 업데이트
	void updateLineStatus(@Param("approvalLineId") String lineId, @Param("status") String status);

	// 다음 결재자 활성화
	void activateNextApprover(@Param("documentId") String docId, @Param("nextOrder") int order);
	
	List<ApprovalLineDTO> selectApprovalLinesByDocId(String approvalDocumentId);
	
	// 해당 문서의 모든 결재 상태 목록 조회
	List<String> selectApprovalStatuses(String approvalDocumentId);

	// 휴가 확정 처리
	void updateLeaveApprovalConfirmed(String leaveId);

	// 출장 확정 처리
	void updateBusinessApprovalConfirmed(String tripId);
	
	int countApprovalLinesByDocId(String approvalDocumentId); // 전체 결재자 수

	int countApprovedLinesByDocId(String approvalDocumentId); // 승인된 결재자 수
	
	String findApprovalLineId(@Param("documentId") String documentId, @Param("approverId") String approverId);

}