package com.itwill.approval.dto;

import lombok.Data;

@Data
public class ApprovalSearchDTO {
    private String empId;     // 사번
    private String empName;   // 이름
    private String depName;   // 부서명
    private String posId;     // 직책
}