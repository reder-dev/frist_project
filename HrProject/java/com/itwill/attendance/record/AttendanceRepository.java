package com.itwill.attendance.record;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    //특정 사원의 날짜별 근태 조회 (출퇴근 처리용)
    Attendance findByEmployeeIdAndDate(String employeeId, LocalDate date);

    //특정 사원의 전체 근태 이력
    List<Attendance> findByEmployeeId(String employeeId);

    //특정 날짜의 전체 근태 기록 (관리자용)
    List<Attendance> findByDate(LocalDate date);

    //지각한 사원들 조회 (관리자 - 지각자 리스트용)
    List<Attendance> findByIsLateTrue();

    //지각 승인 상태로 필터링 (예: 대기중인 승인 목록 등)
    List<Attendance> findByApprovalStatus(String approvalStatus);

    //특정 사원의 지각 기록만 보기
    List<Attendance> findByEmployeeIdAndIsLateTrue(String employeeId);

    //특정 날짜 + 승인 대기 상태 조합
    List<Attendance> findByDateAndApprovalStatus(LocalDate date, String approvalStatus);

    Optional<Attendance> findById(Long attendanceId);
}
