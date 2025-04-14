package com.itwill.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.itwill.domain.EmployeeVO;
import com.itwill.service.EmployeeAdminService;


@Controller
@RequestMapping("/admin")
public class AdminController1 {

	@Autowired
    private EmployeeAdminService employeeService;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<EmployeeVO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "admin/employeeList";
    }

    @GetMapping("/employees/edit/{empId}")
    public String editEmployeeForm(@PathVariable("empId") String empId, Model model) {
        EmployeeVO employee = employeeService.getEmployeeById(empId);
        model.addAttribute("employee", employee);
        
        return "admin/editemployee";
    }

    @PostMapping("/employees/edit")
    public String updateEmployee(@ModelAttribute("employee") EmployeeVO employee) {
    	employee.setEmp_modifydate(new Timestamp(System.currentTimeMillis())); // ���� �ð����� ����
    	employeeService.updateEmployee(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/view/{empId}")
    public String viewEmployeeDetail(@PathVariable("empId") String empId, Model model) {
        EmployeeVO employee = employeeService.getEmployeeById(empId);
        model.addAttribute("employee", employee);
        return "admin/employees";
    }
}