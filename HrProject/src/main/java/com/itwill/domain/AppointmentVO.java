package com.itwill.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppointmentVO {
    
    private String emp_id;              // 사원번호
    private int app_id;                // 발령번호 (PK)
    private String app_ca;             // 발령종류
    private String app_dt;             // 발령내용
    private Timestamp app_registdate;  // 발령일자
    private String app_register;       // 발령자
    private Timestamp app_modifydate;  // 발령 수정일자
    private String app_modifier;       // 발령 수정자

    // 필요 시: 연관 객체(EmployeeVO) 포함도 가능 (옵션)
    // private EmployeeVO employee;
}
