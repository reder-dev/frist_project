package com.itwill.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.domain.DepartmentVO;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    private static final String NAMESPACE = "com.itwill.mappers.DepartmentMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<DepartmentVO> getAllDepartments() {
        return sqlSession.selectList(NAMESPACE + ".getAllDepartments");
    }
}
