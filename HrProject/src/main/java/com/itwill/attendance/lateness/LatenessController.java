package com.itwill.attendance.lateness;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lateness")
public class LatenessController {

    private final LatenessService latenessService;

    @Autowired
    public LatenessController(LatenessService latenessService) {
        this.latenessService = latenessService;
    }

    // 지각 신청
    @PostMapping("/request")
    public Lateness requestLateness(@RequestBody LatenessDTO latenessDTO, HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        latenessDTO.setEmployeeId(employeeId);
        return latenessService.requestLateness(latenessDTO);
    }

    // 내 지각 신청 내역
    @GetMapping("/my-history")
    public List<Lateness> getMyLatenessHistory(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return latenessService.getLatenessByEmployeeId(employeeId);
    }

    // 전체 지각 목록 조회 (관리자용)
    @GetMapping("/all")
    public List<Lateness> getAllLateness() {
        return latenessService.getAllLateness();
    }

    // 특정 지각 신청 승인
    @PostMapping("/approve/{id}")
    public Lateness approveLateness(@PathVariable Long id) {
        return latenessService.updateStatus(id, "승인");
    }

    // 특정 지각 신청 반려
    @PostMapping("/reject/{id}")
    public Lateness rejectLateness(@PathVariable Long id) {
        return latenessService.updateStatus(id, "반려");
    }
}
