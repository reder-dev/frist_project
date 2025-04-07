package com.itwill.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeAdminDAOImpl implements EmployeeAdminDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), id);
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, position = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getPosition(), employee.getEmail(), employee.getId());
    }
}
