package com.itwill.persistence;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.itwill.domain.EmployeeVO;

@Repository
public class EmployeeAdminDAOImpl implements EmployeeAdminDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.itwill.persistence.EmployeeAdminDAO";

    @Override
    public List<EmployeeVO> getAllEmployees() {
        return sqlSession.selectList(NAMESPACE + ".getAllEmployees");
    }

    @Override
    public EmployeeVO getEmployeeById(String empId) {
        return sqlSession.selectOne(NAMESPACE + ".getEmployeeById", empId);
    }

    @Override
    public void updateEmployee(EmployeeVO employee) {
        sqlSession.update(NAMESPACE + ".updateEmployee", employee);
    }
}