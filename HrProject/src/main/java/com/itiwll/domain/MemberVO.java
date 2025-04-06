package com.itiwll.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	
	//@Getter
	private String emp_id;
	private String emp_pw;
	private String username;
	private String useremail;
	private Timestamp regdate;
	private Timestamp updatedate;
	
	// alt shift s + r
	// alt shift s + s
	
}
