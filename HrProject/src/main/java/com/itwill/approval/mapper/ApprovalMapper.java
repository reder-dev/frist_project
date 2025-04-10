package com.itwill.approval.mapper;

import com.itwill.approval.dto.*;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApprovalMapper {

	// 결재 신청 INSERT
	void insertApprovalRequest(ApprovalApplyDTO dto);

	// 결재자 검색
	List<ApprovalSearchDTO> searchApprovers(@Param("keyword") String keyword);

	// 추가: 결재선 템플릿 저장
	void insertTemplate(ApprovalLineTemplateDTO templateDTO);

	// 추가: 결재선 상세 저장 (결재자 한 명씩)
	void insertTemplateDetail(ApprovalLineDetailDTO detailDTO);

	// 휴가 INSERT
	void insertLeave(LeaveDTO leaveDTO);

	// 출장 INSERT
	void insertBusiness(BusinessDTO businessDTO);

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
	
	String selectPositionByEmpId(String empId);

}