package com.itwill.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmailVerificationVO {

	private String emp_id;
    private String email;
    private LocalDateTime verexp_at;
    private boolean verified;
    private String unlock_code;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
