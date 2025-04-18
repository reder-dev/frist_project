package com.itwill.employee.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.employee.domain.DepartmentVO;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    private static final String NAMESPACE = "com.itwill.employee.persistence.DepartmentDAO";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<DepartmentVO> getAllDepartments() {
        return sqlSession.selectList(NAMESPACE + ".getAllDepartments");
    }
}
