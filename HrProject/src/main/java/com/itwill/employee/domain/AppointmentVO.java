package com.itwill.employee.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppointmentVO {
    
    private String empId;              // 사원번호
    private int appId;                 // 발령번호 (PK)
    private String appCa;              // 발령종류
    private String appDt;              // 발령내용
    private Timestamp appRegistdate;   // 발령일자
    private String appRegister;        // 발령자
    private Timestamp appModifydate;   // 발령 수정일자
    private String appModifier;        // 발령 수정자

    // private EmployeeVO employee; // 연관 객체 (옵션)
}
