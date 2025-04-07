package com.itwill.persistence;

import java.util.List;

public interface EmployeeAdminDAO {
    List<Employee> findAll();       // 모든 직원 조회
    Employee findById(Long id);     // 특정 직원 조회
    void update(Employee employee); // 직원 정보 업데이트
}

