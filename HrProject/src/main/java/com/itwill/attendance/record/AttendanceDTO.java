package com.itwill.attendance.record;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDTO {

    private Long id;
    private String employeeId;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String workType; // 예: "일반", "출장", "결근"

    private boolean isLate; // 지각 여부
    private String lateReason; // 지각 사유 (사원이 작성)
    private String approvalStatus; // 승인 상태 (대기, 승인, 반려)

    public AttendanceDTO() {}

    public AttendanceDTO(Long id, String employeeId, LocalDate date,
                         LocalTime checkIn, LocalTime checkOut,
                         String workType, boolean isLate,
                         String lateReason, String approvalStatus) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.workType = workType;
        this.isLate = isLate;
        this.lateReason = lateReason;
        this.approvalStatus = approvalStatus;
    }

    // -------------------------
    // Getter & Setter
    // -------------------------
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

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    public String getLateReason() {
        return lateReason;
    }

    public void setLateReason(String lateReason) {
        this.lateReason = lateReason;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    // -------------------------
    // 근무 상태 자동 판단
    // -------------------------
    public String getStatus() {
        if ("출장".equals(workType)) {
            return "출장";
        } else if ("결근".equals(workType)) {
            return "결근";
        } else if (checkIn != null && checkOut == null) {
            return "출근";
        } else if (checkIn != null && checkOut != null) {
            return "퇴근";
        } else {
            return "미출근";
        }
    }

    // -------------------------
    // 디버깅용 toString
    // -------------------------
    @Override
    public String toString() {
        return "AttendanceDTO{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", date=" + date +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", workType='" + workType + '\'' +
                ", status='" + getStatus() + '\'' +
                ", isLate=" + isLate +
                ", lateReason='" + lateReason + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                '}';
    }
}
