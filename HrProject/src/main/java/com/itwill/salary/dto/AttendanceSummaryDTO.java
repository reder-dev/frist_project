package com.itwill.salary.dto;

import lombok.Data;

@Data
public class AttendanceSummaryDTO {
    private int workDays;
    private int workHours;
    private int nightHours;
    private int holidayHours;
}