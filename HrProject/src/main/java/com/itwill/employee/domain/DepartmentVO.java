package com.itwill.employee.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentVO {

    private int depId;                 // 부서번호 (PK)
    private String depName;           // 부서명
    private String depRegister;       // 등록자
    private Timestamp depRegistdate;  // 등록일자
    private String depModifier;       // 수정자
    private Timestamp depModifydate;  // 수정일자

}