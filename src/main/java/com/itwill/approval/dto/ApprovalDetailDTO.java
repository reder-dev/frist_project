package com.itwill.approval.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class ApprovalDetailDTO {
    private String documentTitle;        // 문서 제목
    private String referenceTableName;   // LEAVE or BUSINESS
    private String comment;              // 코멘트 (없으면 "-")

    // 휴가일 경우
    private String leaveStatus;
    private LocalDate leaveStartDate;
    private LocalDate leaveEndDate;
    private int leaveDays;

    // 출장일 경우
    private String tripLocation;
    private String businessTripPurpose;
    private LocalDate businessTripStart;
    private LocalDate businessTripEnd;
    private int tripDays;

    private List<FileDTO> files;                            // 첨부파일 목록
    private List<ApprovalLineWithStatusDTO> approvers;      // 결재자 목록 + 상태
    private List<ApprovalAppDTO> approverComments;
    
    private LocalDate requestDate;

    public String getFormattedRequestDate() {
        return requestDate != null ? requestDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }
    
    public String getFormattedLeaveStartDate() {
        return leaveStartDate != null ? leaveStartDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }

    public String getFormattedLeaveEndDate() {
        return leaveEndDate != null ? leaveEndDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }

    public String getFormattedBusinessTripStart() {
        return businessTripStart != null ? businessTripStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }

    public String getFormattedBusinessTripEnd() {
        return businessTripEnd != null ? businessTripEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }

}