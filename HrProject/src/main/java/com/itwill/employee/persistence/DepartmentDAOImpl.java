package com.itwill.employee.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.employee.domain.DepartmentVO;
import com.itwill.employee.domain.EmployeeVO;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.itwill.employee.persistence.DepartmentDAO";

    @Override
    public List<DepartmentVO> getAllDepartments() {
        return sqlSession.selectList(NAMESPACE + ".getAllDepartments");
    }

    @Override
    public boolean addDepartment(DepartmentVO department) {
        return sqlSession.insert(NAMESPACE + ".addDepartment", department) == 1;
    }

    @Override
    public boolean updateDepartment(DepartmentVO department) {
        return sqlSession.update(NAMESPACE + ".updateDepartment", department) == 1;
    }

    @Override
    public boolean deleteDepartment(String deptId) {
        return sqlSession.delete(NAMESPACE + ".deleteDepartment", deptId) == 1;
    }

    @Override
    public List<EmployeeVO> getEmployeesByDeptId(String deptId) {
        return sqlSession.selectList("com.itwill.employee.persistence.DepartmentDAO.getEmployeesByDeptId", deptId);
    }

    
}
