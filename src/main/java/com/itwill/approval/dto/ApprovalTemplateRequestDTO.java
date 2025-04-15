package com.itwill.approval.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalTemplateRequestDTO {
    private String templateId;
    private String templateName;
    private String ownerId;
    private List<ApprovalLineDetailDTO> detailList;
}