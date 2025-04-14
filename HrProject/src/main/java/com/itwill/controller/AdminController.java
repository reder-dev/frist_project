package com.itwill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/main")
    public String main(HttpSession session) {
        // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        
        
		/*
		 * // 관리자 권한이 없으면 사용자 메인 페이지로 리다이렉트 Employee user = (Employee)
		 * session.getAttribute("user"); if (user == null || !user.getIsAdmin()) {
		 * return "redirect:/user/main"; }
		 */
        
        return "admin/main";
    }
    
    // 인사관리 - 인사조회
    @GetMapping("/employee/info")
    public String personnelInfo() {
        return "admin/employee/info";
    }
    
    // 인사관리 - 발령조회
    @GetMapping("/employee/appointment")
    public String personnelAppointment() {
        return "admin/employee/appointment";
    }
    
    // 인사관리 - 조직도 조회
    @GetMapping("/employee/organization")
    public String personnelOrganization() {
        return "admin/employee/organization";
    }
    
    // 근태관리
    @GetMapping("/attendance/manage")
    public String attendanceManage() {
        return "admin/attendance/manage";
    }
    
    // 급여관리
    @GetMapping("/salary/manage")
    public String salaryManage() {
        return "admin/salary/manage";
    }
    
    // 전자결제
    @GetMapping("/approval/manage")
    public String approvalManage() {
        return "admin/approval/manage";
    }
    
    // 공지사항 관리
    @GetMapping("/notice/manage")
    public String noticeManage() {
        return "admin/notice/manage";
    }
    
    // 공지사항 상세
    @GetMapping("/notice/detail")
    public String noticeDetail(@RequestParam("id") Long id) {
        return "admin/notice/detail";
    }
}