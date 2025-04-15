package com.itwill.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwill.domain.LoginHistoryVO;
import com.itwill.persistence.LoginHistoryDAO;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

	
	@Inject
	private LoginHistoryDAO LHdao;
	
	@Override
	public LoginHistoryVO insertLoginHistory(LoginHistoryVO history) {
		LHdao.insertLoginHistory(history); // DB에 insert
	    return history; // auto_increment id나 시간 포함돼 있겠지
	}
	
	@Override
	public int countRecentFailedLogins(String emp_id) {
	    return LHdao.countRecentFailedLogins(emp_id);
	}
	
	@Override
	public boolean isAccountLocked(String emp_id) {
		return LHdao.isAccountLocked(emp_id);
	}
	
}
