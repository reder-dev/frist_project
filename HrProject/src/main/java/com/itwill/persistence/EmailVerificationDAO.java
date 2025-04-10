package com.itwill.persistence;

import com.itiwll.domain.EmailVerificationVO;

public interface EmailVerificationDAO {
	// emp_id로 인증 정보 조회
    public EmailVerificationVO selectByEmpId(String empId);

    // 인증 정보 업데이트 (verified = true)
    public void update(EmailVerificationVO verification);

    // 인증 코드 만료 시간 체크
    public boolean isVerificationExpired(EmailVerificationVO verification);
}
