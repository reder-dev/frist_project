package com.itwill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.domain.DepartmentVO;
import com.itwill.persistence.DepartmentDAO;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public List<DepartmentVO> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }
}
