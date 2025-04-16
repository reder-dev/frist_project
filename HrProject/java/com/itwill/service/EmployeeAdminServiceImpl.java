package com.itwill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.itwill.domain.EmployeeVO;
import com.itwill.persistence.EmployeeAdminDAO;

@Service
public class EmployeeAdminServiceImpl implements EmployeeAdminService {

    @Autowired
    private EmployeeAdminDAO employeeDAO;

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
        employeeDAO.updateEmployee(employee);
    }
}