package com.itwill.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom("hhsky1111@gmail.com"); // 네 이메일 주소로 설정

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}