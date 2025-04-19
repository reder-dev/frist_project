package com.itwill.employee.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResignationVO {
    private int resignId;
    private String empId;
    private Date resignationDate;
    private String resignationType;
    private String resignationReason;
    private String handoverPlan;
    private String contactAfter;
    private boolean agreement;
    private String status;
    private Timestamp requestDate;
    private Timestamp approveDate;
    private String approver;
}
