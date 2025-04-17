package com.itwill.service;

import java.util.Date;
import java.util.List;
import com.itwill.domain.EmployeeVO;

public interface EmployeeService {
    
	//��ü���� ���� ��� ��ȸ
	List<EmployeeVO> getAllEmployees();
    
	//Ư������ ���� ��ȸ
	EmployeeVO getEmployeeById(String empId);
    
    //Ư������ ���� ����
	void updateEmployee(EmployeeVO employee);
	
	void updateResignationDate(String empId, Date empQd);
    
}