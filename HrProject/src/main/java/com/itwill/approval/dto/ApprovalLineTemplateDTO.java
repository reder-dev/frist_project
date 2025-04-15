package com.itwill.approval.dto;

import lombok.Data;
import java.util.List;

@Data
public class ApprovalLineTemplateDTO {

    private String templateId;       // 템플릿 ID (PK)
    private String templateName;     // 템플릿 이름 (사용자가 지정)
    private String ownerId;          // 템플릿을 저장한 사원 ID

    // 템플릿에 포함된 결재자 목록
    private List<ApprovalLineDetailDTO> details;
}