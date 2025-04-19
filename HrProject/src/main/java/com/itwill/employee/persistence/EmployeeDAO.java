package com.itwill.employee.persistence;

import java.util.Date;
import java.util.List;

import com.itwill.employee.domain.EmployeeVO;

public interface EmployeeDAO {
	List<EmployeeVO> getAllEmployees();		// ��� ���� ��ȸ
    EmployeeVO getEmployeeById(String empId);	// Ư�� ���� ��ȸ
    void updateEmployee(EmployeeVO employee);	// ���� ���� ������Ʈ
    void updateResignationDate(String empId, Date empQd);
    void deleteEmployee(String empId);
    
    
}

