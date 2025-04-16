package com.itwill.approval.dto;

import lombok.Data;

@Data
public class ApprovalLineDetailDTO {

    private String templateId;       // 템플릿 ID (템플릿과 연결)
    private int approvalOrder;       // 결재 순서
    private String approverId;       // 결재자 사번
    private String approverRole;     // 결재자 직책
    
    // 표시용 정보
    private String empName;
    private String depName;
    private String posId;
}