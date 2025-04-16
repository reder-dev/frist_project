package com.itwill.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	
	//@Getter
	private String emp_id;
	private String emp_pw;
	private String emp_name;
	private String emp_email;
	private Timestamp emp_registdate;
	private Timestamp emp_modifydate;
	
	// alt shift s + r
	// alt shift s + s
	
}
