package com.itwill.persistence;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.itwill.domain.EmployeeVO;

@Repository
public class EmployeeAdminDAOImpl implements EmployeeAdminDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmployeeVO> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeVO.class));
    }

    @Override
    public EmployeeVO findById(Long id) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmployeeVO.class), id);
    }

    @Override
    public void update(EmployeeVO employee) {
        String sql = "UPDATE employees SET dep_id = ?, dep_name = ?, pos_id = ?, rank_id = ?, emp_modifydate = ?, emp_modifier = ? WHERE emp_id = ?";
        jdbcTemplate.update(sql, employee.dep_id(), employee.dep_name(), employee.pos_id(),
                employee.rank_id(), employee.emp_modifydate(), employee.emp_modifier(), employee.emp_id());
    }
}