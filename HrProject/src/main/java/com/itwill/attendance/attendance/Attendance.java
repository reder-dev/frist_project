package com.itwill.attendance.attendance;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance") // 실제 DB 테이블 이름
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
    private String workType;  // 예: 일반, 출장, 결근

    //기본 생성자 (JPA 필수)
    public Attendance() {}

    //생성자 (필요시)
    public Attendance(String employeeId, LocalDate date, LocalTime checkIn, LocalTime checkOut, String workType) {
        this.employeeId = employeeId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.workType = workType;
    }

    //Getter & Setter

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

    // 근무 상태 계산 메서드 (DTO와 동일한 로직 포함 가능)
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
}
