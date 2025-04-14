package com.itwill.attendance.leave;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String reason;

    @Column(nullable = false)
    private String status;  // 신청됨, 승인, 반려

    public Leave() {}

    public Leave(String employeeId, LocalDate startDate, LocalDate endDate, String reason, String status) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    // Getter / Setter
    public Long getId() { return id; }

    public String getEmployeeId() { return employeeId; }

    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
