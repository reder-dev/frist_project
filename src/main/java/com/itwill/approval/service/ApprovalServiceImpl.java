package com.itwill.approval.service;

import com.itwill.approval.dao.ApprovalDAO;
import com.itwill.approval.dao.FileDAO;
import com.itwill.approval.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDAO approvalDAO;
    private final FileDAO fileDAO;
    private final SqlSession sqlSession;
    private final ServletContext servletContext;

    @Override
    @Transactional
    public void saveApprovalRequest(ApprovalApplyDTO dto, List<MultipartFile> files, LeaveReqDTO leaveReq,
                                     BusinessReqDTO businessReq) {
        String docId = dto.getApprovalDocumentId();
        dto.setReferenceId(docId);

        if ("LEAVE".equals(dto.getReferenceTableName())) {
            leaveReq.setLeaveId(docId);
            leaveReq.setEmpId(dto.getRequester());
            leaveReq.setApprovalConfirmed(false);
            int leaveDays = (int) (leaveReq.getLeaveEndDate().toEpochDay() - leaveReq.getLeaveStartDate().toEpochDay()) + 1;
            leaveReq.setLeaveDays(leaveDays);
            approvalDAO.insertLeaveReq(sqlSession, leaveReq);

        } else if ("BUSINESS".equals(dto.getReferenceTableName())) {
            businessReq.setBusinessTripId(docId);
            businessReq.setEmpId(dto.getRequester());
            businessReq.setApprovalConfirmed(false);
            approvalDAO.insertBusinessReq(sqlSession, businessReq);
        }

        approvalDAO.insertApprovalRequest(sqlSession, dto);

        String[] approverIds = dto.getApproverList().split(",");
        for (int i = 0; i < approverIds.length; i++) {
            String approverId = approverIds[i];
            String approverRole = approvalDAO.selectPositionByEmpId(sqlSession, approverId);

            ApprovalLineDTO line = new ApprovalLineDTO();
            line.setApprovalLineId(docId + "_" + (i + 1));
            line.setApprovalDocumentId(docId);
            line.setApprovalOrder(i + 1);
            line.setApproverId(approverId);
            line.setApproverRole(approverRole);
            line.setRegDate(LocalDateTime.now());
            line.setRegister(dto.getRequester());

            approvalDAO.insertApprovalLine(sqlSession, line);
        }

        String uploadFolderPath = servletContext.getRealPath("/resources/upload/approval");
        File folder = new File(uploadFolderPath);
        if (!folder.exists()) folder.mkdirs();

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

                    fileDAO.insertFile(sqlSession, fileDTO);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("파일 저장 중 오류 발생", e);
                }
            }
        }
    }

    @Override
    public List<ApprovalSearchDTO> searchApprovers(String name) {
        return approvalDAO.searchApprovers(sqlSession, name);
    }

    @Override
    @Transactional
    public void saveApprovalLineTemplate(ApprovalLineTemplateDTO templateDTO, List<ApprovalLineDetailDTO> detailList) {
        approvalDAO.insertTemplate(sqlSession, templateDTO);
        for (ApprovalLineDetailDTO detail : detailList) {
            detail.setTemplateId(templateDTO.getTemplateId());
            approvalDAO.insertTemplateDetail(sqlSession, detail);
        }
    }

    @Override
    public List<ApprovalLineTemplateListDTO> getTemplatesByOwner(String ownerId) {
        return approvalDAO.selectTemplatesByOwner(sqlSession, ownerId);
    }

    @Override
    public List<ApprovalLineDetailDTO> getTemplateDetails(String templateId) {
        return approvalDAO.selectTemplateDetails(sqlSession, templateId);
    }

    @Override
    public boolean isTemplateNameDuplicate(String name) {
        return approvalDAO.countTemplateName(sqlSession, name) > 0;
    }

    @Override
    public List<ApprovalLineTemplateListDTO> getTemplatesWithDetails(String ownerId) {
        return approvalDAO.selectTemplatesWithDetails(sqlSession, ownerId);
    }

    @Override
    public void insertApprovalLine(ApprovalLineDTO dto) {
        if (dto.getApproverRole() == null) {
            String role = approvalDAO.selectPositionByEmpId(sqlSession, dto.getApproverId());
            dto.setApproverRole(role);
        }
        String lineId = dto.getApprovalDocumentId() + "_" + dto.getApprovalOrder();
        dto.setApprovalLineId(lineId);
        approvalDAO.insertApprovalLine(sqlSession, dto);
    }

    @Override
    public List<PendingApprovalDTO> getPendingApprovals(String approverId) {
        return approvalDAO.selectPendingApprovals(sqlSession, approverId);
    }

    @Override
    public ApprovalDetailDTO getApprovalDetail(String documentId) {
        ApprovalApplyDTO doc = approvalDAO.selectApprovalDocument(sqlSession, documentId);
        if (doc == null) throw new IllegalArgumentException("문서 없음: " + documentId);

        ApprovalDetailDTO dto = new ApprovalDetailDTO();
        dto.setDocumentTitle(doc.getDocumentTitle());
        dto.setReferenceTableName(doc.getReferenceTableName());
        dto.setComment(doc.getRequestComment() != null ? doc.getRequestComment() : "-");
        dto.setRequestDate(doc.getRequestDate());

        if ("LEAVE".equals(doc.getReferenceTableName())) {
            LeaveReqDTO leave = approvalDAO.selectLeaveReqByDocId(sqlSession, documentId);
            if (leave != null) {
                dto.setLeaveStatus(leave.getLeaveStatus());
                dto.setLeaveStartDate(leave.getLeaveStartDate());
                dto.setLeaveEndDate(leave.getLeaveEndDate());
                int days = (int) (leave.getLeaveEndDate().toEpochDay() - leave.getLeaveStartDate().toEpochDay()) + 1;
                dto.setLeaveDays(days);
            }
        } else if ("BUSINESS".equals(doc.getReferenceTableName())) {
            BusinessReqDTO biz = approvalDAO.selectBusinessReqByDocId(sqlSession, documentId);
            if (biz != null) {
                dto.setTripLocation(biz.getTripLocation());
                dto.setBusinessTripPurpose(biz.getBusinessTripPurpose());
                dto.setBusinessTripStart(biz.getBusinessTripStart());
                dto.setBusinessTripEnd(biz.getBusinessTripEnd());
                int days = (int) (biz.getBusinessTripEnd().toEpochDay() - biz.getBusinessTripStart().toEpochDay()) + 1;
                dto.setTripDays(days);
            }
        }

        dto.setFiles(approvalDAO.selectFilesByReferenceId(sqlSession, documentId));
        List<ApprovalAppDTO> results = approvalDAO.selectApprovalResults(sqlSession, documentId);
        dto.setApproverComments(results);

        Map<String, String> statusMap = new HashMap<>();
        for (ApprovalAppDTO result : results) {
            if (result.getApproverId() != null) {
                statusMap.put(result.getApproverId(), result.getApprovalStatus());
            }
        }

        List<ApprovalLineDTO> lines = approvalDAO.selectApprovalLines(sqlSession, documentId);
        List<ApprovalLineWithStatusDTO> approverList = new ArrayList<>();
        boolean hasRejectedBefore = false;

        for (ApprovalLineDTO line : lines) {
            String approverId = line.getApproverId();
            String status = line.getApprovalStatus();
            if (status == null) status = "대기";
            if (hasRejectedBefore && !("승인".equals(status) || "반려".equals(status))) {
                status = "취소";
            }
            if ("반려".equals(status)) {
                hasRejectedBefore = true;
            }
            ApprovalSearchDTO emp = approvalDAO.selectEmployeeInfo(sqlSession, approverId);
            ApprovalLineWithStatusDTO approver = new ApprovalLineWithStatusDTO();
            approver.setOrder(line.getApprovalOrder());
            approver.setName(emp.getEmpName());
            approver.setDept(emp.getDepName());
            approver.setPosition(emp.getPosId());
            approver.setStatus(status);
            approverList.add(approver);
        }
        dto.setApprovers(approverList);
        return dto;
    }

    @Override
    public FileDTO getFileById(String fileId) {
        return approvalDAO.selectFileById(sqlSession, fileId);
    }

    @Override
    @Transactional
    public void saveApprovalResult(ApprovalAppDTO dto) {
        int order = approvalDAO.selectApprovalOrder(sqlSession, dto.getApprovalDocumentId(), dto.getApproverId());
        dto.setApprovalOrder(order);

        String approvalLineId = approvalDAO.findApprovalLineId(sqlSession, dto.getApprovalDocumentId(), dto.getApproverId());
        dto.setApprovalLineId(approvalLineId);

        approvalDAO.insertApprovalResult(sqlSession, dto);
        approvalDAO.updateLineStatus(sqlSession, dto.getApprovalLineId(), dto.getApprovalStatus());
        approvalDAO.activateNextApprover(sqlSession, dto.getApprovalDocumentId(), dto.getApprovalOrder() + 1);
        processFinalApprovalIfNeeded(dto.getApprovalDocumentId());
    }

    @Override
    public void activateNextApprover(String documentId, int nextOrder) {
        approvalDAO.activateNextApprover(sqlSession, documentId, nextOrder);
    }

    @Override
    public void processFinalApprovalIfNeeded(String approvalDocumentId) {
        List<ApprovalLineDTO> lines = approvalDAO.selectApprovalLinesByDocId(sqlSession, approvalDocumentId);
        boolean allApproved = lines.stream().allMatch(line -> "승인".equals(line.getApprovalStatus()));
        if (!allApproved) return;

        ApprovalApplyDTO doc = approvalDAO.selectApprovalDocument(sqlSession, approvalDocumentId);
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
        approvalDAO.updateLeaveApprovalConfirmed(sqlSession, leaveId);
    }

    @Override
    public void confirmBusinessApproval(String tripId) {
        approvalDAO.updateBusinessApprovalConfirmed(sqlSession, tripId);
    }

    @Override
    public void updateLineStatus(String lineId, String status) {
        approvalDAO.updateLineStatus(sqlSession, lineId, status);
    }

    @Override
    public int countApprovalByPrefix(String prefix) {
        return approvalDAO.countApprovalByPrefix(sqlSession, prefix);
    }

    @Override
    public List<PendingApprovalDTO> getMyRequestedDocuments(String empId) {
        return approvalDAO.selectRequestedApprovals(sqlSession, empId);
    }
}
