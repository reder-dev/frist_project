package com.itwill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("hhsky1111@gmail.com");
        mailSender.setPassword("ucexcmgwtirpyytb");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);

        return mailSender;
    }
}