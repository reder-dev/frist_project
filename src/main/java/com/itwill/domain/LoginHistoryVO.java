package com.itwill.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LoginHistoryVO {
	private String emp_id;
	private String login_ip;
	private String login_status;   // ACTIVE, LOCKED 등
    private String login_result;   // SUCCESS, FAIL
	private Timestamp login_time;
}