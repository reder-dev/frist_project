package com.itwill.approval.dto;

import lombok.Data;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class PendingApprovalDTO {
    private String approvalDocumentId;  // 결재 문서 ID
    private String documentTitle;       // 문서 제목
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;      // 결재 요청일
    private String requester;           // 결재 요청자 ID
    private String requesterName;       // 요청자 이름 (부서, 직급 포함)
    private int approvalOrder;          // 결재 순서
    private String approvalLineId;      // 결재선 ID
    private String approverId;          // 결재자 사번
    private String approverRole;        // 결재자의 직급
    private String requesterDept;
    private String requesterPosition;
    private String approvalStatus; // 승인 or 반려 or null
}