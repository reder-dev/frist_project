package com.itwill.service;

import java.util.List;
import com.itwill.domain.EmployeeVO;

public interface EmployeeAdminService {
    
	//전체직원 정보 목록 조회
	List<EmployeeVO> getAllEmployees();
    
	//특정직원 정보 조회
	EmployeeVO getEmployeeById(String empId);
    
    //특정직원 정보 수정
	void updateEmployee(EmployeeVO employee);
    
}