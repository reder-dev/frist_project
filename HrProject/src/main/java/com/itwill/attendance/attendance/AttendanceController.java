package com.itwill.attendance.attendance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //사용자의 출근 버튼 클릭 시 출근 처리 
    @PostMapping("/check-in")
    public Attendance checkIn(@RequestParam String employeeId) {
        return attendanceService.checkIn(employeeId);
    }

    //사용자가 퇴근 버튼 클릭 시 퇴근 처리(퇴근 시간 저장)
    @PostMapping("/check-out")
    public Attendance checkOut(@RequestParam String employeeId) {
        return attendanceService.checkOut(employeeId);
    }

    //오늘 내 출결 조회
    @GetMapping("/today")
    public Attendance getToday(@RequestParam String employeeId) {
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
    public List<Attendance> getHistory(@RequestParam String employeeId) {
        return attendanceService.getAttendanceHistory(employeeId);
    }
}
