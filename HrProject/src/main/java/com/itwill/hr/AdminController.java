package com.itwill.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.persistence.EmployeeAdminDAO;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeAdminDAO employeeAdminDao;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeAdminDao.findAll());
        return "admin/employeeList";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeAdminDao.findById(id);
        model.addAttribute("employee", employee);
        return "admin/editEmployee";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeAdminDao.update(employee);
        return "redirect:/admin/employees";
    }
}
