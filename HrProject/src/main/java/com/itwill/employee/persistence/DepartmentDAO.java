package com.itwill.employee.persistence;

import java.util.List;

import com.itwill.employee.domain.DepartmentVO;

public interface DepartmentDAO {
    List<DepartmentVO> getAllDepartments();
}
