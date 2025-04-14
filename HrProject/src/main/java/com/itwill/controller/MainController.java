package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "main"; // main.jsp�� �̵�
    }
    
    @GetMapping("/employee")
    public String personnel() {
        return "employee/index";
    }
    
    @GetMapping("/attendance")
    public String attendance() {
        return "attendance/index";
    }
    
    @GetMapping("/salary")
    public String salary() {
        return "salary/index";
    }
    
    @GetMapping("/approval")
    public String approval() {
        return "approval/index";
    }
    
    @GetMapping("/notice")
    public String notice() {
        return "notice/index";
    }
    
    
    
}

