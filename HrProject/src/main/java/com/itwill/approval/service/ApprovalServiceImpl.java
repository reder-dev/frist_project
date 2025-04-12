package com.itwill.approval.service;

import com.itwill.approval.dto.*;
import com.itwill.approval.mapper.ApprovalMapper;
import com.itwill.approval.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

import java.io.File;
import java.io.IOException;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
//import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

	private final ApprovalMapper approvalMapper;
	private final FileMapper fileMapper;
	private final ServletContext servletContext;

	@Override
	@Transactional
	public void saveApprovalRequest(ApprovalApplyDTO dto, List<MultipartFile> files, LeaveReqDTO leaveReq,
		BusinessReqDTO businessReq) {
		String docId = dto.getApprovalDocumentId();
		dto.setReferenceId(docId);

		// 1. 참조 테이블 insert (휴가 or 출장)
		if ("LEAVE".equals(dto.getReferenceTableName())) {
			leaveReq.setLeaveId(docId);
			leaveReq.setEmpId(dto.getRequester());
			leaveReq.setApprovalConfirmed(false);
			int leaveDays = (int) (leaveReq.getLeaveEndDate().toEpochDay() - leaveReq.getLeaveStartDate().toEpochDay())
					+ 1;
			leaveReq.setLeaveDays(leaveDays);
			approvalMapper.insertLeaveReq(leaveReq);

		} else if ("BUSINESS".equals(dto.getReferenceTableName())) {
			businessReq.setBusinessTripId(docId);
			businessReq.setEmpId(dto.getRequester());
			businessReq.setApprovalConfirmed(false);
			approvalMapper.insertBusinessReq(businessReq);
		}

		// 2. 결재 문서 저장
		approvalMapper.insertApprovalRequest(dto);

		// 3. 결재선 insert
		String[] approverIds = dto.getApproverList().split(",");
		for (int i = 0; i < approverIds.length; i++) {
			String approverId = approverIds[i];
			String approverRole = approvalMapper.selectPositionByEmpId(approverId);

			ApprovalLineDTO line = new ApprovalLineDTO();
			line.setApprovalLineId(docId + "_" + (i + 1));
			line.setApprovalDocumentId(docId);
			line.setApprovalOrder(i + 1);
			line.setApproverId(approverId);
			line.setApproverRole(approverRole);
			line.setRegDate(LocalDateTime.now());
			line.setRegister(dto.getRequester());

			approvalMapper.insertApprovalLine(line);
		}

		// 4. 첨부파일 저장
		String uploadFolderPath = servletContext.getRealPath("/resources/upload/approval");
		File folder = new File(uploadFolderPath);
		if (!folder.exists())
			folder.mkdirs();

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					String originalName = file.getOriginalFilename();
					String fileId = UUID.randomUUID().toString();
					String saveName = fileId + "_" + originalName;
					String savePath = uploadFolderPath + File.separator + saveName;

					file.transferTo(new File(savePath));

					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileId(fileId);
					fileDTO.setFileName(originalName);
					fileDTO.setFilePath("/resources/upload/approval/" + saveName);
					fileDTO.setFileSize(file.getSize());
					fileDTO.setFileType(file.getContentType());
					fileDTO.setReferenceTableId(docId);
					fileDTO.setReferenceTableName("APPROVAL_REQ");
					fileDTO.setUploadDate(LocalDateTime.now());

					fileMapper.insertFile(fileDTO);
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException("파일 저장 중 오류 발생", e);
				}
			}
		}
	}

	@Override
	public List<ApprovalSearchDTO> searchApprovers(String name) {
		return approvalMapper.searchApprovers(name); // 메서드명 수정
	}

	// 결재선 템플릿 저장 (마스터 + 상세 결재자들)
	@Override
	@Transactional
	public void saveApprovalLineTemplate(ApprovalLineTemplateDTO templateDTO, List<ApprovalLineDetailDTO> detailList) {
		// 1. 템플릿 마스터 저장
		approvalMapper.insertTemplate(templateDTO);

		// 2. 결재자 상세 저장
		for (ApprovalLineDetailDTO detail : detailList) {
			detail.setTemplateId(templateDTO.getTemplateId()); // 외래키 설정
			approvalMapper.insertTemplateDetail(detail);
		}
	}

	@Override
	public List<ApprovalLineTemplateListDTO> getTemplatesByOwner(String ownerId) {
		return approvalMapper.selectTemplatesByOwner(ownerId);
	}

	@Override
	public List<ApprovalLineDetailDTO> getTemplateDetails(String templateId) {
		return approvalMapper.selectTemplateDetails(templateId);
	}

	@Override
	public boolean isTemplateNameDuplicate(String name) {
		// Mapper를 통해 DB에서 중복된 템플릿명이 있는지 확인
		return approvalMapper.countTemplateName(name) > 0;
	}

	// 소유자 ID로 템플릿 + 결재자 상세 목록 조회 서비스 로직
	@Override
	public List<ApprovalLineTemplateListDTO> getTemplatesWithDetails(String ownerId) {
		return approvalMapper.selectTemplatesWithDetails(ownerId);
	}

	@Override
	public void insertApprovalLine(ApprovalLineDTO dto) {
		// approverRole이 없으면 DB에서 조회해서 세팅
		if (dto.getApproverRole() == null) {
			String role = approvalMapper.selectPositionByEmpId(dto.getApproverId());
			dto.setApproverRole(role);
		}

		// approval_line_id 생성
		String lineId = dto.getApprovalDocumentId() + "_" + dto.getApprovalOrder();
		dto.setApprovalLineId(lineId);

		approvalMapper.insertApprovalLine(dto);
	}

	/////////////// 관리자 //////////////////
	@Override
	public List<PendingApprovalDTO> getPendingApprovals(String approverId) {
		return approvalMapper.selectPendingApprovals(approverId);
	}

	@Override
	public ApprovalDetailDTO getApprovalDetail(String documentId) {
	    // 1. 문서 기본 정보 조회
	    ApprovalApplyDTO doc = approvalMapper.selectApprovalDocument(documentId);
	    if (doc == null) throw new IllegalArgumentException("문서 없음: " + documentId);

	    ApprovalDetailDTO dto = new ApprovalDetailDTO();
	    dto.setDocumentTitle(doc.getDocumentTitle());
	    dto.setReferenceTableName(doc.getReferenceTableName());
	    dto.setComment(doc.getRequestComment() != null ? doc.getRequestComment() : "-");

	    // 2. 문서 유형에 따라 상세 정보 매핑
	    if ("LEAVE".equals(doc.getReferenceTableName())) {
	    	LeaveReqDTO leave = approvalMapper.selectLeaveReqByDocId(documentId);
	        if (leave != null) {
	            dto.setLeaveStatus(leave.getLeaveStatus());
	            dto.setLeaveStartDate(leave.getLeaveStartDate());
	            dto.setLeaveEndDate(leave.getLeaveEndDate());
	            int days = (int) (leave.getLeaveEndDate().toEpochDay() - leave.getLeaveStartDate().toEpochDay()) + 1;
	            dto.setLeaveDays(days);
	        }
	    } else if ("BUSINESS".equals(doc.getReferenceTableName())) {
	        BusinessReqDTO biz = approvalMapper.selectBusinessReqByDocId(documentId);
	        if (biz != null) {
	            dto.setTripLocation(biz.getTripLocation());
	            dto.setBusinessTripPurpose(biz.getBusinessTripPurpose());
	            dto.setBusinessTripStart(biz.getBusinessTripStart());
	            dto.setBusinessTripEnd(biz.getBusinessTripEnd());
	            int days = (int) (biz.getBusinessTripEnd().toEpochDay() - biz.getBusinessTripStart().toEpochDay()) + 1;
	            dto.setTripDays(days);
	        }
	    }

	    // 3. 첨부파일 리스트
	    dto.setFiles(approvalMapper.selectFilesByReferenceId(documentId));
	    
	    List<ApprovalAppDTO> results = approvalMapper.selectApprovalResults(documentId);
	    
	    Map<String, String> statusMap = new HashMap<>();
	    for (ApprovalAppDTO result : results) {
	        if (result.getApproverId() != null) {
	            statusMap.put(result.getApproverId(), result.getApprovalStatus());
	        }
	    }
	    
	    // 4. 결재선 정보 + 결재 상태 구성
	    List<ApprovalLineDTO> lines = approvalMapper.selectApprovalLines(documentId);
	    List<ApprovalLineWithStatusDTO> approverList = new ArrayList<>();

	    boolean hasRejectedBefore = false;

	    for (ApprovalLineDTO line : lines) {
	        String approverId = line.getApproverId();
	        String status = line.getApprovalStatus(); // 기본값은 APPROVAL_LINE 기준
	        if (status == null) status = "대기";

	        // 앞사람이 반려한 경우 이후 사람은 "취소"
	        if (hasRejectedBefore && !("승인".equals(status) || "반려".equals(status))) {
	            status = "취소";
	        }

	        // 현재 결재자가 반려했으면 flag 켜기
	        if ("반려".equals(status)) {
	            hasRejectedBefore = true;
	        }
	        ApprovalSearchDTO emp = approvalMapper.selectEmployeeInfo(approverId);
	        ApprovalLineWithStatusDTO approver = new ApprovalLineWithStatusDTO();
	        approver.setOrder(line.getApprovalOrder());
	        approver.setName(emp.getEmpName());
	        approver.setDept(emp.getDepName());
	        approver.setPosition(emp.getPosId());
	        approver.setStatus(status); // 상태 적용

	        approverList.add(approver);
	    }
	    dto.setApprovers(approverList);

	    return dto;
	}
	
	@Override
	public FileDTO getFileById(String fileId) {
	    return approvalMapper.selectFileById(fileId);
	}
	
	@Override
	@Transactional
	public void saveApprovalResult(ApprovalAppDTO dto) {
		// 💬 1. 로그 출력 먼저!
	    System.out.println("💬 UPDATE LINE 상태 = " + dto.getApprovalStatus());
	    System.out.println("💬 UPDATE LINE ID = " + dto.getApprovalDocumentId() + "_" + dto.getApproverId());
		
		// 결재 순서 가져오기
	    int order = approvalMapper.selectApprovalOrder(dto.getApprovalDocumentId(), dto.getApproverId());
	    dto.setApprovalOrder(order);

	    // DB에서 실제 approvalLineId 값을 가져오자
	    String approvalLineId = approvalMapper.findApprovalLineId(dto.getApprovalDocumentId(), dto.getApproverId());
	    dto.setApprovalLineId(approvalLineId);

	    // INSERT into APPROVAL_APP
	    approvalMapper.insertApprovalResult(dto);
	    
	    // APPROVAL_LINE 테이블에도 상태 반영
	    approvalMapper.updateLineStatus(dto.getApprovalLineId(), dto.getApprovalStatus());
	    
	    // 다음 결재자 활성화
	    approvalMapper.activateNextApprover(dto.getApprovalDocumentId(), dto.getApprovalOrder() + 1);
	    
	    // 모든 결재자 승인했는지 확인
	    processFinalApprovalIfNeeded(dto.getApprovalDocumentId());
	    
	    System.out.println("📌 UPDATE 대상 approval_line_id = " + dto.getApprovalLineId());
	    System.out.println("📌 UPDATE 상태값 = " + dto.getApprovalStatus());
	}
	
	@Override
	public void activateNextApprover(String documentId, int nextOrder) {
	    approvalMapper.activateNextApprover(documentId, nextOrder);
	}
	
	@Override
	public void processFinalApprovalIfNeeded(String approvalDocumentId) {
	    List<ApprovalLineDTO> lines = approvalMapper.selectApprovalLinesByDocId(approvalDocumentId);
	    System.out.println("🧪 승인 상태 목록 확인용 ↓");
	    for (ApprovalLineDTO line : lines) {
	        System.out.println("🧪 결재자: " + line.getApproverId() + ", 상태: " + line.getApprovalStatus());
	    }
	    
	    boolean allApproved = lines.stream().allMatch(line -> "승인".equals(line.getApprovalStatus())); // APPROVAL_LINE 기준
	    System.out.println("✅ 최종 승인 여부: " + allApproved);
	    System.out.println("✅ 최종 승인 여부: " + allApproved);
	    if (!allApproved) return;

	    ApprovalApplyDTO doc = approvalMapper.selectApprovalDocument(approvalDocumentId);
	    String tableName = doc.getReferenceTableName();
	    String referenceId = doc.getReferenceId();
	    
	    if ("LEAVE".equals(tableName)) {
	        confirmLeaveApproval(referenceId);
	    } else if ("BUSINESS".equals(tableName)) {
	        confirmBusinessApproval(referenceId);
	    }
	}
	
	@Override
	public void confirmLeaveApproval(String leaveId) {
	    approvalMapper.updateLeaveApprovalConfirmed(leaveId);
	}
	
	@Override
	public void confirmBusinessApproval(String tripId) {
	    approvalMapper.updateBusinessApprovalConfirmed(tripId);
	}
	
	@Override
	public void updateLineStatus(String lineId, String status) {
		approvalMapper.updateLineStatus(lineId, status);
	}
	
	@Override
	public int countApprovalByPrefix(String prefix) {
	    return approvalMapper.countApprovalByPrefix(prefix);
	}
}