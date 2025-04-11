package com.itwill.persistence;

import com.itwill.domain.EmailVerificationVO;

public interface EmailVerificationDAO {
	// emp_id로 인증 정보 조회
    public EmailVerificationVO selectByEmpId(String empId);

    // 인증 정보 업데이트 (verified = true)
    public void update(EmailVerificationVO verification);

    // 인증 코드 만료 시간 체크
    public boolean isVerificationExpired(EmailVerificationVO verification);
    
    // 인증 데이터 테이블 저장
    public void insertVerification(EmailVerificationVO verification);
    
}
