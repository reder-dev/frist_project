package com.itwill.approval.dto;

import lombok.Data;

@Data
public class ApprovalProcessDTO {
    private String approvalLineId;
    private String approvalDocumentId;
    private String approverId;
    private String approvalStatus;  // "승인" or "반려"
    private String comment;
}