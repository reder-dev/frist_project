package com.itwill.attendance;  
import java.time.LocalDate;
import java.time.LocalTime;

import com.itwill.attendance.attendance.AttendanceDTO;

public class AttendanceTest {
    public static void main(String[] args) {
        AttendanceDTO dto = new AttendanceDTO(
            1L, "EMP123", LocalDate.now(),
            LocalTime.of(9, 0), null, "일반"
        );

        System.out.println("근무 상태: " + dto.getStatus());  // ➜ "출근"
    }
}
