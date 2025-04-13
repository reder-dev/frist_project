package com.itwill.attendance.attendance;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //출근
    @PostMapping("/check-in")
    public Attendance checkIn(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.checkIn(employeeId);
    }

    //퇴근
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

    //내 근태 이력 (전체)
    @GetMapping("/history")
    public List<Attendance> getHistory(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getAttendanceHistory(employeeId);
    }

    //날짜별 전체 출결 (관리자용)
    @GetMapping("/date")
    public List<Attendance> getByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return attendanceService.getAllAttendanceByDate(date);
    }

    //지각 현황 (기간별)
    @GetMapping("/lateness")
    public List<Attendance> getLatenessRecords(
            HttpSession session,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getLatenessRecords(employeeId, startDate, endDate);
    }

    //근무 시간/일수 조회
    @GetMapping("/work-summary")
    public String getWorkSummary(
            HttpSession session,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getWorkSummary(employeeId, startDate, endDate);
    }

    //근태 유형별 조회
    @GetMapping("/by-type")
    public List<Attendance> getByWorkType(
            HttpSession session,
            @RequestParam String workType) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getByWorkType(employeeId, workType);
    }

    //관리자: 사원별 전체 출결 (사원 ID로)
    @GetMapping("/admin/employee")
    public List<Attendance> getByEmployeeId(@RequestParam String employeeId) {
        return attendanceService.getAttendanceHistory(employeeId);
    }

    //관리자: 부서별 출결 (stub)
    @GetMapping("/admin/department")
    public List<Attendance> getByDepartment(@RequestParam String departmentName) {
        return attendanceService.getByDepartment(departmentName); // 구현 필요
    }

    //관리자: 지각 사유 승인 처리
    @PostMapping("/admin/approve")
    public String approveReason(
            @RequestParam Long attendanceId,
            @RequestParam String status) {
        return attendanceService.updateApprovalStatus(attendanceId, status);
    }

    //Excel 다운로드 (내 이력)
    @GetMapping("/download/excel")
    public String downloadExcel(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.exportExcel(employeeId);
    }

    //PDF 다운로드 (지각사유 등)
    @GetMapping("/download/pdf")
    public String downloadPdf(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.exportPdf(employeeId);
    }
}
