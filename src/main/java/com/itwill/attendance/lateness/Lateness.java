package com.itwill.approval.attendance.lateness;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lateness")
public class Lateness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private LocalDate date;  // 지각한 날짜

    @Column(nullable = false)
    private LocalTime checkInTime;  // 실제 출근 시간

    @Column(nullable = false)
    private int latenessMinutes;  // 몇 분 지각했는지

    @Column(length = 255)
    private String reason;  // 지각 사유 (선택)

    @Column(length = 20)
    private String status;  // 승인 상태: 대기, 승인, 반려 등

    public Lateness() {}

    public Lateness(String employeeId, LocalDate date, LocalTime checkInTime, int latenessMinutes, String reason, String status) {
        this.employeeId = employeeId;
        this.date = date;
        this.checkInTime = checkInTime;
        this.latenessMinutes = latenessMinutes;
        this.reason = reason;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public int getLatenessMinutes() {
        return latenessMinutes;
    }

    public void setLatenessMinutes(int latenessMinutes) {
        this.latenessMinutes = latenessMinutes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 상태 자동 판별 (간단 로직)
    public boolean isPending() {
        return "대기".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return "Lateness{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", date=" + date +
                ", checkInTime=" + checkInTime +
                ", latenessMinutes=" + latenessMinutes +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
