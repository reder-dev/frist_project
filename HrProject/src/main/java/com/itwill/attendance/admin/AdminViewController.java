package com.itwill.attendance.admin;

import com.itwill.attendance.lateness.Lateness;
import com.itwill.attendance.lateness.LatenessService;
import com.itwill.attendance.leave.Leave;
import com.itwill.attendance.leave.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminViewController {

    private final LatenessService latenessService;
    private final LeaveService leaveService;

    @Autowired
    public AdminViewController(LatenessService latenessService, LeaveService leaveService) {
        this.latenessService = latenessService;
        this.leaveService = leaveService;
    }

    //지각 승인 화면
    @GetMapping("/admin/lateness-approval")
    public String showLatenessApproval(Model model) {
        List<Lateness> list = latenessService.getAllLateness();
        model.addAttribute("latenessList", list);
        return "admin-lateness-approval"; // → JSP 경로
    }

    //연차 승인 화면
    @GetMapping("/admin/leave-approval")
    public String showLeaveApproval(Model model) {
        List<Leave> list = leaveService.getAllLeaves();
        model.addAttribute("leaveList", list);
        return "admin-leave-approval";
    }

    //출결 다운로드 화면
    @GetMapping("/admin/download")
    public String showDownloadPage() {
        return "attendance-download";
    }
}
