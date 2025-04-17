package com.itwill.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.domain.EmployeeVO;
import com.itwill.domain.NoticeVO;
import com.itwill.service.EmployeeService;
import com.itwill.service.NoticeService;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private EmployeeService employeeService;
	

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
    public String employeeList(Model model) {
        List<EmployeeVO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "admin/employee/info"; 
    }
    
 // 인사 정보 상세
    @GetMapping("/employee/detail/{empId}")
    public String employeeDetail(@PathVariable("empId") String empId, Model model) {
        EmployeeVO employee = employeeService.getEmployeeById(empId);
        model.addAttribute("employee", employee);
        return "admin/employee/detail";
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
    
    @Autowired
    private NoticeService noticeService;
    
    // 공지사항 관리
    @GetMapping("/notice/manage")
    public String adminNoticeManage(Model model) {
        List<NoticeVO> noticeList = noticeService.getNoticeList(); // 서비스 메서드 동일하게 재사용 가능
        model.addAttribute("noticeList", noticeList);
        return "admin/notice/manage";
    }
    
    // 공지사항 상세
    @GetMapping("/notice/detail")
    public String noticeDetail(@RequestParam("not_id") int not_id, Model model) {
        noticeService.increaseViewCount(not_id); // 조회수 증가
        NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "admin/notice/detail";
    }
    
    // 공지사항 작성
    @GetMapping("/notice/write")
    public String writeForm() {
    	return "admin/notice/write";
    }
    
    @PostMapping("/notice/write")
    public String write(@ModelAttribute NoticeVO vo) {
        vo.setNot_register("admin");  // ��: ���ǿ��� ���� ����
        noticeService.insertNotice(vo);
        return "redirect:/admin/notice/manage";
    }
    
    // 공지사항 수정
    @GetMapping("/notice/edit")
    public String editForm(@RequestParam("not_id") int not_id, Model model) {
        NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "admin/notice/update";
    }

    @PostMapping("/notice/edit")
    public String edit(@ModelAttribute NoticeVO vo) {
        vo.setNot_modifier("admin"); 
        noticeService.updateNotice(vo);
        return "redirect:/admin/notice/manage";
    }


    // 공지사항 삭제
    @PostMapping("/notice/delete")
    public String delete(@RequestParam("not_id") int not_id) {
        noticeService.deleteNotice(not_id);
        return "redirect:/admin/notice/manage";
    }
    
    
}