package com.itwill.attendance.lateness;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatenessRepository extends JpaRepository<Lateness, Long> {

    List<Lateness> findByEmployeeId(String employeeId);
    
    Optional<Lateness> findById(Long id);
}
