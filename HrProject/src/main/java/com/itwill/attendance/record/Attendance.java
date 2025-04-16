package com.itwill.attendance.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime checkIn;
    private LocalTime checkOut;

    @Column(nullable = false)
    private String workType;  // 예: 일반, 출장, 결근, 외근, 지각, 휴가

    private boolean isLate;
    
    //지각 시간 (분 단위)
    private Integer latenessMinutes;

    //사유서 (지각 또는 결근 사유)
    private String reason;

    //관리자 승인 여부: "승인", "반려", "대기"
    private String approvalStatus;

    // 생성자
    public Attendance() {}

    public Attendance(String employeeId, LocalDate date, LocalTime checkIn, LocalTime checkOut, String workType) {
        this.employeeId = employeeId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.workType = workType;
    }

    // Getter & Setter
    public Long getId() {
        return id;
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

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getLatenessMinutes() {
        return latenessMinutes;
    }

    public void setLatenessMinutes(Integer latenessMinutes) {
        this.latenessMinutes = latenessMinutes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    
    // 상태 반환 로직
    public String getStatus() {
        if ("출장".equals(workType)) {
            return "출장";
        } else if ("외근".equals(workType)) {
            return "외근";
        } else if ("휴가".equals(workType)) {
            return "휴가";
        } else if ("결근".equals(workType)) {
            return "결근";
        } else if (checkIn != null && checkOut == null) {
            return "출근 중";
        } else if (checkIn != null && checkOut != null) {
            return "퇴근 완료";
        } else {
            return "미출근";
        }
    }
}
