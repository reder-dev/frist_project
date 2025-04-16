package com.itwill.persistence;

import java.util.List;

import com.itwill.domain.EmployeeVO;

public interface EmployeeAdminDAO {
	List<EmployeeVO> getAllEmployees();		// 모든 직원 조회
    EmployeeVO getEmployeeById(String empId);	// 특정 직원 조회
    void updateEmployee(EmployeeVO employee);	// 직원 정보 업데이트
	
    
}

