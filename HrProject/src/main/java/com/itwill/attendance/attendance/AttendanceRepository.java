package com.itwill.attendance.attendance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    //특정 사원의 날짜별 근태 조회
    Attendance findByEmployeeIdAndDate(String employeeId, LocalDate date);

    //특정 사원의 전체 근태 기록
    List<Attendance> findByEmployeeId(String employeeId);

    //특정 날짜의 전체 출결 기록
    List<Attendance> findByDate(LocalDate date);
}
