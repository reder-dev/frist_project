package com.itwill.attendance.lateness;

import java.time.LocalDate;
import java.time.LocalTime;

public class LatenessDTO {
    private Long id;
    private String employeeId;
    private LocalDate date;
    private LocalTime checkInTime; // 실제 출근 시간
    private String reason;         // 지각 사유
    private String status;         // 대기, 승인, 반려

    public LatenessDTO() {}

    public LatenessDTO(Long id, String employeeId, LocalDate date, LocalTime checkInTime, String reason, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.checkInTime = checkInTime;
        this.reason = reason;
        this.status = status;
    }

    // Getter / Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalTime checkInTime) { this.checkInTime = checkInTime; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
