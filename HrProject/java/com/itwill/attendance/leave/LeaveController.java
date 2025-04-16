package com.itwill.attendance.leave;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // 연차 신청
    @PostMapping("/request")
    public Leave requestLeave(@RequestBody LeaveDTO dto, HttpSession session) {
        dto.setEmployeeId((String) session.getAttribute("employeeId"));
        return leaveService.requestLeave(dto);
    }

    // 내 연차 신청 내역
    @GetMapping("/my")
    public List<Leave> myLeaves(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return leaveService.getLeavesByEmployeeId(employeeId);
    }

    // 전체 연차 신청 내역 (관리자)
    @GetMapping("/all")
    public List<Leave> allLeaves() {
        return leaveService.getAllLeaves();
    }

    // 상태 변경 (관리자)
    @PostMapping("/update-status/{id}")
    public Leave updateStatus(@PathVariable Long id, @RequestParam String status) {
        return leaveService.updateStatus(id, status);
    }
}
