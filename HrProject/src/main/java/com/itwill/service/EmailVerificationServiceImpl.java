package com.itwill.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwill.domain.EmailVerificationVO;
import com.itwill.persistence.EmailVerificationDAO;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    @Inject
    private EmailVerificationDAO emaildao;

    @Override
    public EmailVerificationVO getVerificationByEmpId(String empId) {
        return emaildao.selectByEmpId(empId);
    }

    @Override
    public void updateVerification(EmailVerificationVO verification) {
        emaildao.update(verification);
    }

    @Override
    public boolean isVerificationExpired(EmailVerificationVO verification) {
        return verification.getVerexp_at().isBefore(java.time.LocalDateTime.now());
    }
    
    @Override
    public void saveVerification(EmailVerificationVO verification) {
    	emaildao.insertVerification(verification);
    }
}