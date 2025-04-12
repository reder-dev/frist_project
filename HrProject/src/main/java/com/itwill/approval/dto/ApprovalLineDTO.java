package com.itwill.approval.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApprovalLineDTO {
    private String approvalLineId;         // 결재선 ID (문서ID + 순번)
    private String approvalDocumentId;     // 결재 문서 ID
    private int approvalOrder;             // 결재 순서
    private String approverId;             // 결재자 사번
    private String approverRole;           // 결재자의 직책 ← 이게 필요해!
    private LocalDateTime regDate;         // 등록일
    private String register;               // 등록자
    private String approvalStatus; // "대기", "승인", "반려"
}