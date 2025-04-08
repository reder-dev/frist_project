package com.itwill.persistence;

import java.util.List;

import com.itwill.domain.EmployeeVO;

public interface EmployeeAdminDAO {
    List<EmployeeVO> findAll();       // 모든 직원 조회
    EmployeeVO findById(Long id);     // 특정 직원 조회
    void update(EmployeeVO employee); // 직원 정보 업데이트
}

