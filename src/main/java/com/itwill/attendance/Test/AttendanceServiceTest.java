package com.itwill.approval.attendance.attendance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendanceServiceTest {

    @Autowired
    private AttendanceService attendanceService;

    @Test
    void testCheckIn() {
        String employeeId = "test001";
        Attendance attendance = attendanceService.checkIn(employeeId);
        assertNotNull(attendance.getCheckIn());
        assertEquals(LocalDate.now(), attendance.getDate());
    }

    @Test
    void testCheckOut() {
        String employeeId = "test001";
        Attendance attendance = attendanceService.checkOut(employeeId);
        assertNotNull(attendance.getCheckOut());
    }

    @Test
    void testGetTodayAttendance() {
        String employeeId = "test001";
        Attendance attendance = attendanceService.getTodayAttendance(employeeId);
        assertNotNull(attendance);
    }
}
