package com.itwill.attendance.attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDTO {
    private Long id;
    private String empId;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String workType; // 근무 형태 (예: "출장", "결근", "일반")

    public AttendanceDTO() {}

    public AttendanceDTO(Long id, String empId, LocalDate date, LocalTime checkIn, LocalTime checkOut, String workType) {
        this.id = id;
        this.empId = empId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.workType = workType;
    }

    // Getter/Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    // 자동으로 근무 상태 판단
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
            return "미등록";
        }
    }

    @Override
    public String toString() {
        return "AttendanceDTO{" +
                "id=" + id +
                ", empId='" + empId + '\'' +
                ", date=" + date +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", workType='" + workType + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
       
}
