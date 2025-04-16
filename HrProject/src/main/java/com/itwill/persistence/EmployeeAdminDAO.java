package com.itwill.persistence;

import java.util.List;

import com.itwill.domain.EmployeeVO;

public interface EmployeeAdminDAO {
	List<EmployeeVO> getAllEmployees();		// ��� ���� ��ȸ
    EmployeeVO getEmployeeById(String empId);	// Ư�� ���� ��ȸ
    void updateEmployee(EmployeeVO employee);	// ���� ���� ������Ʈ
	
    
}

