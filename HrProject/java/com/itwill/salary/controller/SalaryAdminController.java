package com.itwill.salary.controller;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.itwill.salary.dto.SalaryDetailDTO;
import com.itwill.salary.dto.SalaryEmployeeDTO;
import com.itwill.salary.service.SalaryService;

@Controller
@RequestMapping("/salary/admin")
@RequiredArgsConstructor
public class SalaryAdminController {

    private final SalaryService salaryService;

    @GetMapping("")
    public String showAdminPage() {
        return "salary/salary-admin-search";
    }

    @GetMapping("/search-employee")
    @ResponseBody
    public List<SalaryEmployeeDTO> searchEmployee(@RequestParam String keyword) {
        return salaryService.searchEmployee(keyword);
    }

    @GetMapping("/calculate")
    @ResponseBody
    public SalaryDetailDTO calculateSalary(
            @RequestParam String empId,
            @RequestParam String salMonth) {
        return salaryService.calculateSalary(empId, salMonth);
    }

    @PostMapping(value = "/confirm", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> confirmSalary(@RequestBody SalaryDetailDTO dto) {
        try {
            salaryService.confirmSalary(dto);
            return ResponseEntity.ok("success");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}