package com.itwill.approval.dto;

import lombok.Data;

@Data
public class ApprovalLineWithStatusDTO {
    private int order;           // 결재 순서 (1, 2, 3...)
    private String name;         // 결재자 이름
    private String dept;         // 부서명
    private String position;     // 직급명
    private String status;       // 결재 상태 ('대기', '승인', '반려')
}