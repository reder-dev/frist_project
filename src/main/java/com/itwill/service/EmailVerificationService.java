package com.itwill.service;

import com.itwill.domain.EmailVerificationVO;

public interface EmailVerificationService {
	
	// emp_id로 인증 정보 조회
    public EmailVerificationVO getVerificationByEmpId(String empId);

    // 인증 상태 업데이트 (verified = true)
    public void updateVerification(EmailVerificationVO verification);

    // 인증 코드 만료 확인
    public boolean isVerificationExpired(EmailVerificationVO verification);
    
    // 인증 데이터 db저장
    public void saveVerification(EmailVerificationVO verification);
	
}
