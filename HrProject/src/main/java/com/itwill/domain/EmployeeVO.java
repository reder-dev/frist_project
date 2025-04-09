package com.itwill.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)

public class EmployeeVO {
	private String emp_id;
	private String emp_name;
	private String emp_pw;
	private String emp_gender;
	private String emp_jm;
	private String emp_phone;
	private String emp_email;
	private String emp_address;
	private Date emp_jd;
	private Date emp_qd;
	private String dep_id;
	private String dep_name;
	private String pos_id;
	private String rank_id;
	private Timestamp emp_registdate;
	private String emp_regoster;
	private Timestamp emp_modifydate;
	private String emp_modifier;
	private String emp_pht;
	private String emp_cn;
	private String app_id;
	

}
