package com.itwill.approval.controller;

import com.itwill.approval.dto.ApprovalDetailDTO;
import com.itwill.approval.dto.ApprovalSearchDTO;
import com.itwill.approval.dto.PendingApprovalDTO;
import com.itwill.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalUserController {

    private final ApprovalService approvalService;

    // 사용자 결재 이력 화면 이동
    @GetMapping("/user")
    public String showUserApprovalPage() {
        return "approval/approval-user"; // /WEB-INF/views/approval/approval-user.jsp
    }

    // 해당 사용자의 결재 신청 목록 반환 (AJAX)
    @GetMapping("/user-documents")
    @ResponseBody
    public List<PendingApprovalDTO> getMyDocuments(HttpSession session) {
        ApprovalSearchDTO loginUser = (ApprovalSearchDTO) session.getAttribute("loginUser");
        if (loginUser == null) return Collections.emptyList();

        return approvalService.getMyRequestedDocuments(loginUser.getEmpId());
    }
    
    @GetMapping("/user-detail")
    @ResponseBody
    public ApprovalDetailDTO getApprovalDetailJson(@RequestParam("documentId") String docId) {
        return approvalService.getApprovalDetail(docId);  // DTO 반환 (JSON 자동 변환됨)
    }
}
