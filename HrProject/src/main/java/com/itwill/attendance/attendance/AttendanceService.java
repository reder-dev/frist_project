package com.itwill.attendance.attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    // 생성자 주입 방식
    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    //출근 등록
    public Attendance checkIn(String employeeId) {
        LocalDate today = LocalDate.now();

        // 이미 등록된 출근 정보 있는지 확인
        Attendance existing = attendanceRepository.findByEmployeeIdAndDate(employeeId, today);
        if (existing != null) {
            throw new IllegalStateException("이미 출근한 사원입니다.");
        }

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setDate(today);
        attendance.setCheckIn(LocalTime.now());
        attendance.setWorkType("일반"); // 기본값

        return attendanceRepository.save(attendance);
    }

    //퇴근 처리
    public Attendance checkOut(String employeeId) {
        LocalDate today = LocalDate.now();

        Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, today);
        if (attendance == null) {
            throw new IllegalStateException("출근 기록이 없습니다.");
        }

        if (attendance.getCheckOut() != null) {
            throw new IllegalStateException("이미 퇴근한 사원입니다.");
        }

        attendance.setCheckOut(LocalTime.now());
        return attendanceRepository.save(attendance);
    }

    //오늘 내 근태 보기
    public Attendance getTodayAttendance(String employeeId) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, LocalDate.now());
    }

    //전체 출결 조회 (관리자용 등)
    public List<Attendance> getAllAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    //사원의 모든 근태 기록
    public List<Attendance> getAttendanceHistory(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
}
