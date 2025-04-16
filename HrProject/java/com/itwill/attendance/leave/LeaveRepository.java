package com.itwill.attendance.leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    // 특정 사원의 연차 신청 내역 조회
    List<Leave> findByEmployeeId(String employeeId);

	Optional<Leave> findById(Long id);
}
