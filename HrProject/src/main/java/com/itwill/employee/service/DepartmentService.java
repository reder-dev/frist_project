package com.itwill.employee.service;

import java.util.List;

import com.itwill.employee.domain.DepartmentVO;
import com.itwill.employee.domain.EmployeeVO;

public interface DepartmentService {
    List<DepartmentVO> getAllDepartments();
    boolean addDepartment(DepartmentVO department);
    boolean updateDepartment(DepartmentVO department);
    boolean deleteDepartment(String deptId);
    List<EmployeeVO> getEmployeesByDeptId(String deptId);

}
