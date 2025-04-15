package com.itwill.approval.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BusinessReqDTO {
    private String businessTripId;
    private String empId;
    private String tripLocation;
    private String businessTripPurpose;
    private LocalDate businessTripStart;
    private LocalDate businessTripEnd;
    private boolean approvalConfirmed = false;
}