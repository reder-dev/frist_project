package com.itwill.service;

import com.itiwll.domain.LoginHistoryVO;

public interface LoginHistoryService {

	// 로그인 로그 기록 동작
	public LoginHistoryVO insertLoginHistory(LoginHistoryVO history);
	
	// 로그인 실패 횟수 기록 동작
	public int countRecentFailedLogins(String emp_id);
	
}
