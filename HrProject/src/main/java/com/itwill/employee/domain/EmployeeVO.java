package com.itwill.employee.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class EmployeeVO {
	private String empId;
	private String empName;
	private String empPw;
	private String empGender;
	private String empJm;
	private String empPhone;
	private String empEmail;
	private String empAddress;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date empJd;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date empQd;

	private String depId;
	private String depName;
	private String posId;
	private String rankId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp empRegistdate;
	private String empRegister;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp empModifydate;

	private String empModifier;
	private String empPht;
	private String empCn;
	private String appId;
}