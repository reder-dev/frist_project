package com.itwill.approval.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BusinessDTO {
    private String businessTripId;         // 출장 ID (PK) → "BIZ_" + timestamp
    private String empId;                  // 사원 ID (신청자)
    private LocalDate businessTripStart;   // 출장 시작일
    private LocalDate businessTripEnd;     // 출장 종료일
    private String tripLocation;           // 출장지 (※ 현재는 "출장 이름"이라고 입력받은 값 사용)
    private String businessTripPurpose;    // 출장 목적 (※ 코멘트로 입력)
    private String appRegister;            // 등록자 (보통은 신청자 ID와 동일)
}