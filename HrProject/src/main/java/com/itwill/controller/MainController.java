package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "main/dashboard";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout() {
        // 로그아웃 처리 로직
        return "redirect:/login";
    }
}