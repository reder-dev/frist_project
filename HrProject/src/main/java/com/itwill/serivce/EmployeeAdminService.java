package com.itwill.serivce;

import org.springframework.stereotype.Service;

@Service
public class EmployeeAdminService {
	private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사원을 찾을 수 없습니다."));
    }

    public void update(Employee employee) {
        employeeRepository.save(employee);
    }


}
