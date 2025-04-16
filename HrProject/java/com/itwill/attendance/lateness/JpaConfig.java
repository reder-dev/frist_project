package com.itwill.attendance.lateness;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.itwill.attendance.lateness") // 리포지토리 패키지 지정
public class JpaConfig {
    // JPA 관련 추가 설정을 여기에 작성할 수 있습니다 (예: DataSource, EntityManagerFactory 등)
}