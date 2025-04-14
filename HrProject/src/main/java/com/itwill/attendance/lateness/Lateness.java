package com.itwill.attendance.lateness;

import javax.persistence.*;
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
    private String reason;  // 지각 사유 (사유서 내용)

    @Column(length = 20)
    private String status;  // 대기, 승인, 반려

    public Lateness() {}

    // 사유서를 보낼 때 사용할 생성자
    public Lateness(String employeeId, LocalDate date, LocalTime checkInTime, String reason) {
        this.employeeId = employeeId;
        this.date = date;
        this.checkInTime = checkInTime;
        this.reason = reason;
        this.latenessMinutes = calculateLatenessMinutes(checkInTime);
        this.status = "대기";
    }

    private int calculateLatenessMinutes(LocalTime actualTime) {
        // 기준 출근 시간: 09:00
        LocalTime standardTime = LocalTime.of(9, 0);
        if (actualTime.isAfter(standardTime)) {
            return (int) java.time.Duration.between(standardTime, actualTime).toMinutes();
        }
        return 0;
    }

    // Getters & Setters
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
        this.latenessMinutes = calculateLatenessMinutes(checkInTime);
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
