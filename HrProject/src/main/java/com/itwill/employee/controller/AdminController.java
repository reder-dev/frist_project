package com.itwill.employee.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.employee.domain.AppointmentVO;
import com.itwill.employee.domain.DepartmentVO;
import com.itwill.employee.domain.EmployeeVO;
import com.itwill.employee.domain.NoticeVO;
import com.itwill.employee.domain.ResignationVO;
import com.itwill.employee.service.AppointmentService;
import com.itwill.employee.service.DepartmentService;
import com.itwill.employee.service.EmployeeService;
import com.itwill.employee.service.NoticeService;
import com.itwill.employee.service.ResignationService;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private EmployeeService employeeService;
	
	@Autowired
    private DepartmentService departmentService;
	
	@Autowired
    private AppointmentService appointmentService;
	
	
	@Autowired
    private ResignationService resignationService;

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
    
    // 인사관리 - 인사리스트
    @GetMapping("/employee/list")
    public String employeeList(Model model) {
        List<EmployeeVO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "admin/employee/list"; 
    }
    
    // 인사 정보 상세
    @GetMapping("/employee/detail/{empId}")
    public String employeeDetail(@PathVariable("empId") String empId, Model model) {
        EmployeeVO employee = employeeService.getEmployeeById(empId);
        model.addAttribute("employee", employee);
        return "admin/employee/detail";
    }
    
    // 인사관리 - 수정
    @GetMapping("/employee/edit/{empId}")
    public String employeeEditForm(@PathVariable("empId") String empId, Model model) {
        EmployeeVO employee = employeeService.getEmployeeById(empId);
        model.addAttribute("employee", employee);
        return "admin/employee/edit"; 
    }
    
    @PostMapping("/employee/edit")
    public String employeeEditSubmit(@ModelAttribute EmployeeVO employee) {
        employee.setEmpModifier("admin");
        employee.setEmpModifydate(new Timestamp(System.currentTimeMillis())); 
        employeeService.updateEmployee(employee);
        return "redirect:/admin/employee/list";
    }
    
    // 인사관리 - 삭제
    @PostMapping("/employee/delete")
    @ResponseBody
    public String deleteEmployee(@RequestParam("empId") String empId) {
        try {
            employeeService.deleteEmployee(empId); // deleteEmployee 메서드 필요
            return "OK";
        } catch (Exception e) {
            return "FAIL";
        }
    }
    
    
 // 발령 조회
    @GetMapping("/appointment/list")
    public String appointmentList(Model model) {
        List<AppointmentVO> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "admin/appointment/list";
    }
    
    // 발령 상세
    @GetMapping("/appointment/detail/{appointmentId}")
    public String appointmentDetail(@PathVariable("appointmentId") int appointmentId, Model model) {
        AppointmentVO appointment = appointmentService.getAppointmentById(appointmentId);
        model.addAttribute("appointment", appointment);
        return "admin/appointment/detail";
    }
    
    // 발령 등록 폼
    @GetMapping("/appointment/register")
    public String appointmentRegisterForm(Model model) {
        List<EmployeeVO> employees = employeeService.getAllEmployees();
        List<DepartmentVO> departments = departmentService.getAllDepartments();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        return "admin/appointment/register";
    }
    
    // 발령 등록 처리
    @PostMapping("/appointment/register")
    public String appointmentRegister(@ModelAttribute AppointmentVO appointment, HttpSession session) {
        String empId = (String) session.getAttribute("empId");
        appointment.setAppRegister(empId);
        appointmentService.registerAppointment(appointment);
        return "redirect:/admin/appointment/list";
    }
    
    // 조직도
    @GetMapping("/organization/chart")
    public String organizationChart(Model model) {
        List<DepartmentVO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "admin/organization/chart";
    }
    
    // 부서별 사원 목록 조회 (AJAX 요청용)
    @GetMapping("/organization/department-members")
    public String departmentMembers(@RequestParam("deptId") String deptId, Model model) {
    	List<EmployeeVO> employees = departmentService.getEmployeesByDeptId(deptId);
    	model.addAttribute("employees", employees);
    	return "admin/organization/department-members";
    }

    
    // 부서 관리 페이지
    @GetMapping("/organization/departments")
    public String departmentManagement(Model model) {
        List<DepartmentVO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "admin/organization/departments";
    }
    
    // 부서 추가 처리
    @PostMapping("/organization/department/add")
    @ResponseBody
    public boolean addDepartment(@ModelAttribute DepartmentVO department) {
        return departmentService.addDepartment(department);
    }
    
    // 부서 수정 처리
    @PostMapping("/organization/department/update")
    @ResponseBody
    public boolean updateDepartment(@ModelAttribute DepartmentVO department) {
        return departmentService.updateDepartment(department);
    }
    
    // 부서 삭제 처리
    @PostMapping("/organization/department/delete")
    @ResponseBody
    public boolean deleteDepartment(@RequestParam("deptId") String deptId) {
        return departmentService.deleteDepartment(deptId);
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
    @GetMapping("/notice/detail/{notId}")
    public String noticeDetail(@PathVariable("notId") int notId, Model model) {
        noticeService.increaseViewCount(notId);
        NoticeVO notice = noticeService.getNotice(notId);
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
    	vo.setNotRegister("admin");  // ��: ���ǿ��� ���� ����
        noticeService.insertNotice(vo);
        return "redirect:/admin/notice/manage";
    }
    
    // 공지사항 수정
    @GetMapping("/notice/edit")
    public String editForm(@RequestParam(value = "notId", required = false) String notIdStr, Model model) {
        try {
            int notId = Integer.parseInt(notIdStr);
            NoticeVO notice = noticeService.getNotice(notId);
            model.addAttribute("notice", notice);
            return "admin/notice/update";
        } catch (Exception e) {
            return "redirect:/admin/notice/manage";  // 또는 에러 페이지
        }
    }

    @PostMapping("/notice/edit")
    public String edit(@ModelAttribute NoticeVO vo) {
        vo.setNotModifier("admin");
        noticeService.updateNotice(vo);
        return "redirect:/admin/notice/manage";
    }


    // 공지사항 삭제
    @PostMapping("/notice/delete")
    public String delete(@RequestParam("notId") int notId) {
        noticeService.deleteNotice(notId);
        return "redirect:/admin/notice/manage";
    }
    


 // 관리자 퇴사 신청 이력 조회
    @GetMapping("/resignation/manage")
    public String manageResignation(Model model) {
        List<ResignationVO> resignList = resignationService.getAllResignations();
        model.addAttribute("resignList", resignList);
        return "admin/resignation/manage";
    }

    // 관리자 퇴사 신청 상세
    @GetMapping("/resignation/detail/{resignId}")
    public String resignationDetail(@PathVariable("resignId") int resignId, Model model) {
    	ResignationVO vo = resignationService.getResignationDetail(resignId);
        EmployeeVO employee = employeeService.getEmployeeById(vo.getEmpId());
        model.addAttribute("resign", vo);
        model.addAttribute("employee", employee);
        return "admin/resignation/detail";
    }

    // 관리자 퇴사 신청 상태 변경 (승인/반려)
    @PostMapping("/resignation/update")
    @ResponseBody
    public String updateResignationStatus(@RequestParam("resignId") int resignId,
                                          @RequestParam("status") String status,
                                          HttpSession session) {
        String approver = (String) session.getAttribute("empId");
        if (approver == null) approver = "admin";

        try {
            resignationService.updateStatus(resignId, status, approver);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace(); // 로그 확인용
            return "FAIL";
        }
    }

    
    
    
    
}