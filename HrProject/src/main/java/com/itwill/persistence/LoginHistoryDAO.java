package com.itwill.persistence;

import com.itwill.domain.LoginHistoryVO;

public interface LoginHistoryDAO {
	
	// 로그인 로그 기록
    public LoginHistoryVO insertLoginHistory(LoginHistoryVO history);
    
    // 로그인 실패 기록
    public int countRecentFailedLogins(String emp_id);

	public boolean isAccountLocked(String emp_id);
    
}