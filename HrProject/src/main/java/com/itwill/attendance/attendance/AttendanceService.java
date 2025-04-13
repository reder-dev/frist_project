package com.itwill.attendance.attendance;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //출근 기록
    @PostMapping("/check-in")
    public Attendance checkIn(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.checkIn(employeeId);
    }

    //퇴근 기록
    @PostMapping("/check-out")
    public Attendance checkOut(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.checkOut(employeeId);
    }

    //오늘 내 근태 보기
    @GetMapping("/today")
    public Attendance getToday(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getTodayAttendance(employeeId);
    }

    //날짜별 전체 출결 조회 (관리자용)
    @GetMapping("/date")
    public List<Attendance> getByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return attendanceService.getAllAttendanceByDate(date);
    }

    //내 출결 이력 조회
    @GetMapping("/history")
    public List<Attendance> getHistory(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getAttendanceHistory(employeeId);
    }

    //특정 사원의 출결 이력 조회 (관리자용)
    @GetMapping("/history/{employeeId}")
    public List<Attendance> getHistoryById(@PathVariable String employeeId) {
        return attendanceService.getAttendanceHistory(employeeId);
    }
}
