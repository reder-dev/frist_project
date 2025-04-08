package com.itwill.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)

public class EmployeeVO {
	private String emp_id;
	private String emp_name;
	private String emp_pw;
	private String emp_gender;
	private String emp_jm;
	private String emp_phone;
	private String emp_email;
	private String emp_address;
	private String emp_jd;
	private String emp_qd;
	private String dep_id;
	private String dep_name;
	private String pos_id;
	private String rank_id;
	private String emp_registdate;
	private String emp_regoster;
	private String emp_modifydate;
	private String emp_modifier;
	private String emp_pht;
	private String emp_cn;
	private String app_id;
	

}
