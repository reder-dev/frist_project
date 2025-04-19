package com.itwill.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.itwill.employee.domain.EmployeeVO;
import com.itwill.employee.persistence.EmployeeDAO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<EmployeeVO> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public EmployeeVO getEmployeeById(String emp_id) {
        return employeeDAO.getEmployeeById(emp_id);
    }

    @Override
    public void updateEmployee(EmployeeVO employee) {
    	employee.setEmpModifydate(new Timestamp(System.currentTimeMillis()));
        employeeDAO.updateEmployee(employee);
    }
    
    @Override
    public void updateResignationDate(String empId, Date empQd) {
        employeeDAO.updateResignationDate(empId, empQd);
    }
    
    @Override
    public void deleteEmployee(String empId) {
        employeeDAO.deleteEmployee(empId);
    }
    
    
}