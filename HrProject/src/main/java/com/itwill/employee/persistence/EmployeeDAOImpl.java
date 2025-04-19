package com.itwill.employee.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itwill.employee.domain.EmployeeVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.itwill.employee.persistence.EmployeeDAO";

    @Override
    public List<EmployeeVO> getAllEmployees() {
        return sqlSession.selectList(NAMESPACE + ".getAllEmployees");
    }

    @Override
    public EmployeeVO getEmployeeById(String empId) {
    	
    
    	
    	EmployeeVO emp =sqlSession.selectOne(NAMESPACE + ".getEmployeeById", empId);
    	
    	System.out.println(emp);
        return sqlSession.selectOne(NAMESPACE + ".getEmployeeById", empId);
        
    }

    @Override
    public void updateEmployee(EmployeeVO employee) {
        sqlSession.update(NAMESPACE + ".updateEmployee", employee);
    }
    
    @Override
    public void updateResignationDate(String empId, Date empQd) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empId", empId);
        paramMap.put("empQd", empQd);
        sqlSession.update(NAMESPACE + ".updateResignationDate", paramMap);
    }
    
    @Override
    public void deleteEmployee(String empId) {
        sqlSession.delete("com.itwill.mapper.EmployeeMapper.deleteEmployee", empId);
    }
    
    
}