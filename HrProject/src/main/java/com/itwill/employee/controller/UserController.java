package com.itwill.employee.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.employee.domain.AppointmentVO;
import com.itwill.employee.domain.DepartmentVO;
import com.itwill.employee.domain.EmployeeVO;
import com.itwill.employee.domain.NoticeVO;
import com.itwill.employee.service.AppointmentService;
import com.itwill.employee.service.DepartmentService;
import com.itwill.employee.service.EmployeeService;
import com.itwill.employee.service.NoticeService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private EmployeeService employeeService;
	
	@Autowired
    private NoticeService noticeService;
	
	@Autowired
    private AppointmentService appointmentService;
	
	@Autowired
	private DepartmentService departmentService;
	

	@GetMapping("/main")
	public String userMain(HttpSession session, Model model) {
	    //String empId = (String) session.getAttribute("empId");
	    String testEmpId = "10100002";
	    //EmployeeVO employee = employeeService.getEmployeeById(empId);
	    EmployeeVO employee = employeeService.getEmployeeById(testEmpId);
	    
	    System.out.println("조회된 사원: " + employee);
	    System.out.println("employee: " + employee);
	    System.out.println("입사일: " + employee.getEmpJd());
	  
	    
	    
	    // 입사일로부터 근무일 계산 (emp_jd 필드 활용)
	    if (employee.getEmpJd() != null) {
	        LocalDate joinDate = employee.getEmpJd().toLocalDate();
	        LocalDate today = LocalDate.now();
	        long workDays = ChronoUnit.DAYS.between(joinDate, today);
	        model.addAttribute("workDays", workDays);
	    } else {
	        model.addAttribute("workDays", "-");
	    }

	    model.addAttribute("employee", employee);

	    return "user/main";
	}

    
    // 인사관리 - 인사조회
    @GetMapping("/employee/info")
    public String employeeInfo(HttpSession session, Model model) {
        // String empId = (String) session.getAttribute("empId"); 로그인 하면 쓸거임
        String testEmpId = "10100002";
        // EmployeeVO employee = employeeService.getEmployeeById(empId); 로그인 하면 쓸거임
        EmployeeVO employee = employeeService.getEmployeeById(testEmpId);
        model.addAttribute("employee", employee);
        return "user/employee/info";
    }
    
    // 정보 수정 페이지
    @GetMapping("/employee/edit")
    public String editEmployeeForm(HttpSession session, Model model) {
        // String empId = (String) session.getAttribute("empId"); 로그인 하면 쓸거임
        String testEmpId = "10100002";
        // EmployeeVO employee = employeeService.getEmployeeById(empId); 로그인 하면 쓸거임
        EmployeeVO employee = employeeService.getEmployeeById(testEmpId);
        model.addAttribute("employee", employee);
        return "user/employee/edit";
    }
    
    // 퇴사 신청 페이지
    @GetMapping("/employee/resignation")
    public String resignationForm(HttpSession session, Model model) {
        // String empId = (String) session.getAttribute("empId"); 로그인 하면 쓸거임
        String testEmpId = "10100002";
        // EmployeeVO employee = employeeService.getEmployeeById(empId); 로그인하면 쓸거임
        EmployeeVO employee = employeeService.getEmployeeById(testEmpId);
        model.addAttribute("employee", employee);
        return "user/employee/resignation";
    }
    
    @PostMapping("/employee/resignation")
    public String processResignation(
            @RequestParam("emp_qd") @DateTimeFormat(pattern = "yyyy-MM-dd") Date empQd,
            HttpSession session, RedirectAttributes redirectAttributes) {

        String empId = (String) session.getAttribute("empId");
        employeeService.updateResignationDate(empId, empQd);
        redirectAttributes.addFlashAttribute("msg", "퇴사 신청이 완료되었습니다.");

        return "redirect:/user/employee/info";
    }
    
    
    
    // 발령 조회
    @GetMapping("/employee/appointment")
    public String appointmentInfo(HttpSession session, Model model) {
        // String empId = (String) session.getAttribute("empId");
        String testEmpId = "10100002";
        // List<AppointmentVO> appointments = appointmentService.getAppointmentsByEmpId(empId);
        List<AppointmentVO> appointments = appointmentService.getAppointmentsByEmpId(testEmpId);
        model.addAttribute("appointments", appointments);
        return "user/employee/appointment";
    }
    
    @GetMapping("/employee/organization")
    public String organizationChart(Model model) {
        List<DepartmentVO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "user/employee/organization";
    }
    
    
    
    
    // 공지사항 목록
    @GetMapping("/notice/list")
    public String noticeList(Model model) {
        // 실제로는 서비스를 통해 공지사항 목록을 가져와 모델에 추가
    	List<NoticeVO> noticeList = noticeService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "user/notice/list";
    }
    
    // 공지사항 상세
    @GetMapping("/notice/detail")
    public String noticeDetail(@RequestParam("not_id") int not_id, Model model) {
        noticeService.increaseViewCount(not_id); // 조회수 증가
        NoticeVO notice = noticeService.getNotice(not_id);
        model.addAttribute("notice", notice);
        return "user/notice/detail";
    }
    
    // 첨부파일 다운로드
    @GetMapping("/notice/download")
    public void downloadFile(@RequestParam("fileId") String fileId) {
        // 실제로는 파일 다운로드 로직 구현
    }
}