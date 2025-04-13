package com.itwill.approval.attendance.admin;

import com.itwill.approval.attendance.attendance.Attendance;
import com.itwill.approval.attendance.attendance.AttendanceService;
import com.itwill.approval.attendance.lateness.Lateness;
import com.itwill.approval.attendance.lateness.LatenessService;
import com.itwill.approval.attendance.leave.Leave;
import com.itwill.approval.attendance.leave.LeaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AttendanceService attendanceService;
    private final LatenessService latenessService;
    private final LeaveService leaveService;

    @Autowired
    public AdminController(
        AttendanceService attendanceService,
        LatenessService latenessService,
        LeaveService leaveService
    ) {
        this.attendanceService = attendanceService;
        this.latenessService = latenessService;
        this.leaveService = leaveService;
    }

    // 모든 사원의 특정 날짜 출결 조회
    @GetMapping("/attendance")
    public List<Attendance> getAttendanceByDate(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return attendanceService.getAllAttendanceByDate(date);
    }

    // 특정 사원의 전체 출결 이력 조회
    @GetMapping("/attendance/{employeeId}")
    public List<Attendance> getAttendanceByEmployee(@PathVariable String employeeId) {
        return attendanceService.getAttendanceHistory(employeeId);
    }

    // 모든 지각 기록 조회
    @GetMapping("/lateness")
    public List<Lateness> getAllLateness() {
        return latenessService.getAllLateness();
    }

    // 모든 연차 신청 내역 조회
    @GetMapping("/leave")
    public List<Leave> getAllLeave() {
        return leaveService.getAllLeaves();
    }

    // 연차 상태 변경 (승인/반려)
    @PostMapping("/leave/status/{id}")
    public Leave changeLeaveStatus(@PathVariable Long id, @RequestParam String status) {
        return leaveService.updateStatus(id, status);
    }
}
