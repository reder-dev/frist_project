package com.itwill.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentVO {

    private int dep_id;                // 부서번호 (PK)
    private String dep_name;          // 부서명
    private String dep_register;      // 등록자
    private Timestamp dep_registdate; // 등록일자
    private String dep_modifier;      // 수정자
    private Timestamp dep_modifydate; // 수정일자

}
