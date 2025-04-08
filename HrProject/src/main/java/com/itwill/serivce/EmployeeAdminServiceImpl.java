package com.itwill.serivce;

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
    public List<EmployeeVO> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public EmployeeVO findById(Long id) {
        return employeeDAO.findById(id);
    }

    @Override
    public void update(EmployeeVO employee) {
        employeeDAO.update(employee);
    }
}