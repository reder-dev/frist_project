package com.itwill.attendance.admin;

import com.itwill.attendance.record.Attendance;
import com.itwill.attendance.record.AttendanceService;
import com.itwill.attendance.lateness.Lateness;
import com.itwill.attendance.lateness.LatenessService;
import com.itwill.attendance.leave.Leave;
import com.itwill.attendance.leave.LeaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin")  //REST API 전용 경로 구분
public class AdminApiController {

    private final AttendanceService attendanceService;
    private final LatenessService latenessService;
    private final LeaveService leaveService;

    @Autowired
    public AdminApiController(
        AttendanceService attendanceService,
        LatenessService latenessService,
        LeaveService leaveService
    ) {
        this.attendanceService = attendanceService;
        this.latenessService = latenessService;
        this.leaveService = leaveService;
    }

    // 출결 API
    @GetMapping("/attendance")
    public List<Attendance> getAttendanceByDate(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return attendanceService.getAllAttendanceByDate(date);
    }

    @GetMapping("/attendance/{employeeId}")
    public List<Attendance> getAttendanceByEmployee(@PathVariable String employeeId) {
        return attendanceService.getAttendanceHistory(employeeId);
    }

    // 지각 API
    @GetMapping("/lateness")
    public List<Lateness> getAllLateness() {
        return latenessService.getAllLateness();
    }

    // 연차 API
    @GetMapping("/leave")
    public List<Leave> getAllLeave() {
        return leaveService.getAllLeaves();
    }

    @PostMapping("/leave/status/{id}")
    public Leave changeLeaveStatus(@PathVariable Long id, @RequestParam String status) {
        return leaveService.updateStatus(id, status);
    }
}
