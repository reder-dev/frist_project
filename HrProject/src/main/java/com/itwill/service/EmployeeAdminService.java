package com.itwill.service;

import java.util.List;
import com.itwill.domain.EmployeeVO;

public interface EmployeeAdminService {
    
	//��ü���� ���� ��� ��ȸ
	List<EmployeeVO> getAllEmployees();
    
	//Ư������ ���� ��ȸ
	EmployeeVO getEmployeeById(String empId);
    
    //Ư������ ���� ����
	void updateEmployee(EmployeeVO employee);
    
}