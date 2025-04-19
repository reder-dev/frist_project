package com.itwill.employee.service;

import java.util.Date;
import java.util.List;

import com.itwill.employee.domain.EmployeeVO;

public interface EmployeeService {
    
	//��ü���� ���� ��� ��ȸ
	List<EmployeeVO> getAllEmployees();
    
	//Ư������ ���� ��ȸ
	EmployeeVO getEmployeeById(String empId);
    
    //Ư������ ���� ����
	void updateEmployee(EmployeeVO employee);
	
	void updateResignationDate(String empId, Date empQd);
	
	void deleteEmployee(String empId);
    
}