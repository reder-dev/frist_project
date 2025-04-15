package com.itwill.approval.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.approval.dto.*;

@Repository
public class ApprovalDAO {

    private static final String NAMESPACE = "com.itwill.approval.mapper.ApprovalMapper.";

    public int countApprovalByPrefix(SqlSession session, String prefix) {
        return session.selectOne(NAMESPACE + "countApprovalByPrefix", prefix);
    }

    public void insertApprovalRequest(SqlSession session, ApprovalApplyDTO dto) {
        session.insert(NAMESPACE + "insertApprovalRequest", dto);
    }

    public List<ApprovalSearchDTO> searchApprovers(SqlSession session, String keyword) {
        return session.selectList(NAMESPACE + "searchApprovers", keyword);
    }

    public void insertTemplate(SqlSession session, ApprovalLineTemplateDTO templateDTO) {
        session.insert(NAMESPACE + "insertTemplate", templateDTO);
    }

    public void insertTemplateDetail(SqlSession session, ApprovalLineDetailDTO detailDTO) {
        session.insert(NAMESPACE + "insertTemplateDetail", detailDTO);
    }

    public void insertLeaveReq(SqlSession session, LeaveReqDTO leave) {
        session.insert(NAMESPACE + "insertLeaveReq", leave);
    }

    public void insertBusinessReq(SqlSession session, BusinessReqDTO business) {
        session.insert(NAMESPACE + "insertBusinessReq", business);
    }

    public List<ApprovalLineTemplateListDTO> selectTemplatesByOwner(SqlSession session, String ownerId) {
        return session.selectList(NAMESPACE + "selectTemplatesByOwner", ownerId);
    }

    public List<ApprovalLineDetailDTO> selectTemplateDetails(SqlSession session, String templateId) {
        return session.selectList(NAMESPACE + "selectTemplateDetails", templateId);
    }

    public int countTemplateName(SqlSession session, String templateName) {
        return session.selectOne(NAMESPACE + "countTemplateName", templateName);
    }

    public List<ApprovalLineTemplateListDTO> selectTemplatesWithDetails(SqlSession session, String ownerId) {
        return session.selectList(NAMESPACE + "selectTemplatesWithDetails", ownerId);
    }

    public int insertApprovalLine(SqlSession session, ApprovalLineDTO lineDTO) {
        return session.insert(NAMESPACE + "insertApprovalLine", lineDTO);
    }

    public String selectPositionByEmpId(SqlSession session, String empId) {
        return session.selectOne(NAMESPACE + "selectPositionByEmpId", empId);
    }

    public List<PendingApprovalDTO> selectPendingApprovals(SqlSession session, String approverId) {
        return session.selectList(NAMESPACE + "selectPendingApprovals", approverId);
    }

    public List<ApprovalAppDTO> selectApprovalResults(SqlSession session, String approvalDocumentId) {
        return session.selectList(NAMESPACE + "selectApprovalResults", approvalDocumentId);
    }

    public ApprovalSearchDTO selectEmployeeInfo(SqlSession session, String empId) {
        return session.selectOne(NAMESPACE + "selectEmployeeInfo", empId);
    }

    public List<FileDTO> selectFilesByReferenceId(SqlSession session, String referenceId) {
        return session.selectList(NAMESPACE + "selectFilesByReferenceId", referenceId);
    }

    public List<ApprovalLineDTO> selectApprovalLines(SqlSession session, String approvalDocumentId) {
        return session.selectList(NAMESPACE + "selectApprovalLines", approvalDocumentId);
    }

    public ApprovalApplyDTO selectApprovalDocument(SqlSession session, String approvalDocumentId) {
        return session.selectOne(NAMESPACE + "selectApprovalDocument", approvalDocumentId);
    }

    public LeaveReqDTO selectLeaveReqByDocId(SqlSession session, String approvalDocumentId) {
        return session.selectOne(NAMESPACE + "selectLeaveReqByDocId", approvalDocumentId);
    }

    public BusinessReqDTO selectBusinessReqByDocId(SqlSession session, String approvalDocumentId) {
        return session.selectOne(NAMESPACE + "selectBusinessReqByDocId", approvalDocumentId);
    }

    public FileDTO selectFileById(SqlSession session, String fileId) {
        return session.selectOne(NAMESPACE + "selectFileById", fileId);
    }

    public void insertApprovalResult(SqlSession session, ApprovalAppDTO dto) {
        session.insert(NAMESPACE + "insertApprovalResult", dto);
    }

    public int selectApprovalOrder(SqlSession session, String documentId, String approverId) {
        Map<String, Object> param = new HashMap<>();
        param.put("documentId", documentId);
        param.put("approverId", approverId);
        return session.selectOne(NAMESPACE + "selectApprovalOrder", param);
    }

    public void updateLineStatus(SqlSession session, String approvalLineId, String status) {
        Map<String, Object> param = new HashMap<>();
        param.put("approvalLineId", approvalLineId);
        param.put("status", status);
        session.update(NAMESPACE + "updateLineStatus", param);
    }

    public void activateNextApprover(SqlSession session, String documentId, int nextOrder) {
        Map<String, Object> param = new HashMap<>();
        param.put("documentId", documentId);
        param.put("nextOrder", nextOrder);
        session.update(NAMESPACE + "activateNextApprover", param);
    }

    public List<ApprovalLineDTO> selectApprovalLinesByDocId(SqlSession session, String approvalDocumentId) {
        return session.selectList(NAMESPACE + "selectApprovalLinesByDocId", approvalDocumentId);
    }

    public List<String> selectApprovalStatuses(SqlSession session, String approvalDocumentId) {
        return session.selectList(NAMESPACE + "selectApprovalStatuses", approvalDocumentId);
    }

    public void updateLeaveApprovalConfirmed(SqlSession session, String leaveId) {
        session.update(NAMESPACE + "updateLeaveApprovalConfirmed", leaveId);
    }

    public void updateBusinessApprovalConfirmed(SqlSession session, String tripId) {
        session.update(NAMESPACE + "updateBusinessApprovalConfirmed", tripId);
    }

    public int countApprovalLinesByDocId(SqlSession session, String approvalDocumentId) {
        return session.selectOne(NAMESPACE + "countApprovalLinesByDocId", approvalDocumentId);
    }

    public int countApprovedLinesByDocId(SqlSession session, String approvalDocumentId) {
        return session.selectOne(NAMESPACE + "countApprovedLinesByDocId", approvalDocumentId);
    }

    public String findApprovalLineId(SqlSession session, String documentId, String approverId) {
        Map<String, Object> param = new HashMap<>();
        param.put("documentId", documentId);
        param.put("approverId", approverId);
        return session.selectOne(NAMESPACE + "findApprovalLineId", param);
    }

    public List<ApprovalAppDTO> selectApprovalComments(SqlSession session, String documentId) {
        return session.selectList(NAMESPACE + "selectApprovalComments", documentId);
    }

    public List<PendingApprovalDTO> selectMyDocuments(SqlSession session, String requesterId) {
        return session.selectList(NAMESPACE + "selectMyDocuments", requesterId);
    }

    public List<PendingApprovalDTO> selectRequestedApprovals(SqlSession session, String requesterId) {
        return session.selectList(NAMESPACE + "selectRequestedApprovals", requesterId);
    }
}
