package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/main")
    public String main() {
        return "user/main";
    }
    
    // 인사관리 - 인사조회
    @GetMapping("/employee/info")
    public String personnelInfo() {
        return "user/personnel/info";
    }
    
    // 인사관리 - 발령조회
    @GetMapping("/employee/appointment")
    public String personnelAppointment() {
        return "user/personnel/appointment";
    }
    
    // 인사관리 - 조직도
    @GetMapping("/employee/organization")
    public String personnelOrganization() {
        return "user/personnel/organization";
    }
    
    // 공지사항 - 목록
    @GetMapping("/notice/list")
    public String noticeList() {
        return "user/notice/list";
    }
    
    // 공지사항 - 상세
    @GetMapping("/notice/detail")
    public String noticeDetail(@RequestParam("id") Long id) {
        // 공지사항 상세 조회 로직
        return "user/notice/detail";
    }
}