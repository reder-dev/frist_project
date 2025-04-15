package com.itwill.salary.dto;

import lombok.Data;

@Data
public class SalaryEmployeeDTO {
    private String empId;     // 사번
    private String empName;   // 이름 (선택)
    private String posId;     // 직책 ID (선택)
    private String deptName;    // 부서명 필드
}