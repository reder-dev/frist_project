package com.itwill.service;

import com.itwill.domain.LoginHistoryVO;

public interface LoginHistoryService {

	// 로그인 로그 기록 동작
	public LoginHistoryVO insertLoginHistory(LoginHistoryVO history);
	
	// 로그인 실패 횟수 기록 동작
	public int countRecentFailedLogins(String emp_id);

	// 로그인 상태 LOCKED인지 확인
	public boolean isAccountLocked(String emp_id);
	
}
