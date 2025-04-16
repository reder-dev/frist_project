package com.itwill.attendance.record;

import com.itwill.attendance.util.ExcelExporter;
import com.itwill.attendance.util.PdfExporter;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
/*import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;*/


@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/check-in")
    public Attendance checkIn(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.checkIn(employeeId);
    }

    @PostMapping("/check-out")
    public Attendance checkOut(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.checkOut(employeeId);
    }

    @GetMapping("/today")
    public Attendance getToday(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getTodayAttendance(employeeId);
    }

    @GetMapping("/history")
    public List<Attendance> getHistory(HttpSession session) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getAttendanceHistory(employeeId);
    }

    @GetMapping("/date")
    public List<Attendance> getByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return attendanceService.getAllAttendanceByDate(date);
    }

    @GetMapping("/lateness")
    public List<Attendance> getLatenessRecords(
            HttpSession session,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getLatenessRecords(employeeId, startDate, endDate);
    }

    @GetMapping("/work-summary")
    public String getWorkSummary(
            HttpSession session,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getWorkSummary(employeeId, startDate, endDate);
    }

    @GetMapping("/by-type")
    public List<Attendance> getByWorkType(HttpSession session, @RequestParam String workType) {
        String employeeId = (String) session.getAttribute("employeeId");
        return attendanceService.getByWorkType(employeeId, workType);
    }

    @GetMapping("/admin/employee")
    public List<Attendance> getByEmployeeId(@RequestParam String employeeId) {
        return attendanceService.getAttendanceHistory(employeeId);
    }

    @GetMapping("/admin/department")
    public List<Attendance> getByDepartment(@RequestParam String departmentName) {
        return attendanceService.getByDepartment(departmentName);
    }

    @PostMapping("/admin/approve")
    public String approveReason(@RequestParam Long attendanceId, @RequestParam String status) {
        return attendanceService.updateApprovalStatus(attendanceId, status);
    }
    
    @GetMapping("/page")
    public String showAttendancePage() {
        return "attendance/attendance"; // /WEB-INF/views/attendance.jsp를 의미함
    }



    //엑셀 다운로드
    @GetMapping("/download/excel")
    public ResponseEntity<byte[]> downloadExcel(HttpSession session) throws Exception {
        String empId = (String) session.getAttribute("employeeId");
        List<Attendance> records = attendanceService.getAttendanceHistory(empId);
        byte[] content = ExcelExporter.exportAttendanceToExcel(records);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attendance.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }

    //PDF 다운로드
    @GetMapping("/download/pdf")
    public ResponseEntity<byte[]> downloadPdf(HttpSession session) throws Exception {
        String empId = (String) session.getAttribute("employeeId");
        List<Attendance> records = attendanceService.getAttendanceHistory(empId);
        byte[] content = PdfExporter.exportAttendanceToPdf(records);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attendance.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(content);
    }
}
