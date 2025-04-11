package com.itwill.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.EmailVerificationVO;

@Repository
public class EmailVerificationDAOImpl implements EmailVerificationDAO {

    private final SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.itwillbs.mapper.EmailVerificationMapper";

    public EmailVerificationDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public EmailVerificationVO selectByEmpId(String empId) {
        return sqlSession.selectOne(NAMESPACE + ".selectByEmpId", empId);
    }

    @Override
    public void update(EmailVerificationVO verification) {
        sqlSession.update(NAMESPACE + ".update", verification);
    }

    @Override
    public boolean isVerificationExpired(EmailVerificationVO verification) {
        return verification.getVerexp_at().isBefore(java.time.LocalDateTime.now());
    }

    @Override
    public void insertVerification(EmailVerificationVO verification) {
        sqlSession.insert(NAMESPACE + ".insertVerification", verification);
    }
}
