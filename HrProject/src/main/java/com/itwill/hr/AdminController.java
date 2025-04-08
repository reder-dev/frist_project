package com.itwill.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.itwill.domain.EmployeeVO;
import com.itwill.serivce.EmployeeAdminService;


@Controller
@RequestMapping("/admin/employees")  // 기본 경로 설정
public class AdminController {

    @Autowired
    private EmployeeAdminService employeeService;

    // 전체 직원 목록 조회
    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "admin/employeeList"; // JSP 페이지로 이동
    }

    // 특정 직원 상세 조회
    @GetMapping("/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        EmployeeVO employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "admin/employees"; // JSP 페이지로 이동
    }

    // 특정 직원 수정 페이지 이동
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        EmployeeVO employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "admin/editemployee"; // JSP 페이지로 이동
    }

    // 특정 직원 정보 업데이트
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute EmployeeVO employee) {
        employeeService.update(employee);
        return "redirect:/admin/employees/list"; // 수정 후 목록 페이지로 이동
    }
}