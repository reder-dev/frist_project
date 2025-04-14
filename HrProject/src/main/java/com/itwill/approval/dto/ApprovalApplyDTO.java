package com.itwill.approval.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class ApprovalApplyDTO {

    private String approvalDocumentId;     // 결재 문서 ID (PK)
    private String documentTitle;          // 문서 제목
    private String referenceTableName;     // 참조 테이블명 (LEAVE or BUSINESS)
    private String referenceId;            // 참조 문서의 PK
    private String requester;              // 결재 요청자 사원 ID
    private int attachmentCount;           // 첨부파일 수 (0이면 기본값)
    private String register;               // 등록자 (보통은 = requester)
    private String approverList; 		   // "15100002,10100001" 이런 식으로 전달됨
    private String requestComment;
    private LocalDate requestDate;
    
    public String getFormattedRequestDate() {
        return requestDate != null ? requestDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }
}