package com.itwill.approval.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LeaveDTO {
    private String leaveId;              // 휴가 ID (PK) → "LEAVE_" + timestamp
    private String empId;                // 사원 ID (신청자)
    private String leaveStatus;          // 휴가 구분 (연차 / 반차 / 병가)
    private LocalDate leaveStartDate;    // 휴가 시작일
    private LocalDate leaveEndDate;      // 휴가 종료일
    private boolean leaveUnn = false;    // 휴가 사용 여부 (false: 미사용 상태로 시작)
    private int leaveDays;               // 휴가 일수
}