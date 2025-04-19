package com.itwill.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.employee.domain.DepartmentVO;
import com.itwill.employee.domain.EmployeeVO;
import com.itwill.employee.persistence.DepartmentDAO;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public List<DepartmentVO> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public boolean addDepartment(DepartmentVO department) {
        return departmentDAO.addDepartment(department);
    }

    @Override
    public boolean updateDepartment(DepartmentVO department) {
        return departmentDAO.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(String deptId) {
        return departmentDAO.deleteDepartment(deptId);
    }

    @Override
    public List<EmployeeVO> getEmployeesByDeptId(String deptId) {
        return departmentDAO.getEmployeesByDeptId(deptId);
    }
}
