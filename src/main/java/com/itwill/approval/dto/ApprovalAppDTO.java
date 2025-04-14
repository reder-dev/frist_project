package com.itwill.approval.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApprovalAppDTO {
    private String approvalLineId;      // 결재 ID (approvalDocId + approverId)
    private String approvalDocumentId;   // 결재 문서 ID (FK)
    private String approvalId; 
    private String approverId;           // 결재자 사번
    private int approvalOrder;           // 결재 순서
    private String approvalStatus;       // '대기' / '승인' / '반려'
    private LocalDateTime approvalDate;  // 결재 처리일자
    private String comment;              // 결재 코멘트
    private String register;             // 등록자
    private LocalDateTime regDate;       // 등록일
    private String modifier;             // 수정자
    private LocalDateTime modDate;       // 수정일
    private String empName; // 결재자 이름
}