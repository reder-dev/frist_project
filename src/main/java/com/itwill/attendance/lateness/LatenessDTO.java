package com.itwill.approval.attendance.lateness;

import java.time.LocalDate;
import java.time.LocalTime;

public class LatenessDTO {
    private Long id;
    private String employeeId;
    private LocalDate date;
    private LocalTime expectedTime; // 예정 출근 시간
    private LocalTime actualTime;   // 실제 출근 시간
    private String reason;          // 지각 사유
    private String status;          // 상태: 신청됨, 승인, 반려

    public LatenessDTO() {}

    public LatenessDTO(Long id, String employeeId, LocalDate date, LocalTime expectedTime, LocalTime actualTime, String reason, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.expectedTime = expectedTime;
        this.actualTime = actualTime;
        this.reason = reason;
        this.status = status;
    }

    // Getter/Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getExpectedTime() { return expectedTime; }
    public void setExpectedTime(LocalTime expectedTime) { this.expectedTime = expectedTime; }

    public LocalTime getActualTime() { return actualTime; }
    public void setActualTime(LocalTime actualTime) { this.actualTime = actualTime; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
