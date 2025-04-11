package com.itwill.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.LoginHistoryVO;

@Repository
public class LoginHistoryDAOImpl implements LoginHistoryDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String namespace = "com.itwillbs.mapper.LoginHistoryMapper"; // XML 안 바꿨으면 그대로

    @Override
    public LoginHistoryVO insertLoginHistory(LoginHistoryVO history) {
        sqlSession.insert(namespace + ".insertLoginHistory", history);
        return history;
    }
    
    @Override
    public int countRecentFailedLogins(String emp_id) {
        return sqlSession.selectOne(namespace + ".countRecentFailedLogins", emp_id);
    }
    
    @Override
    public boolean isAccountLocked(String emp_id) {
    	String status = sqlSession.selectOne(namespace + ".checkAccountLocked", emp_id);
        return "LOCKED".equals(status);
    }
    
}