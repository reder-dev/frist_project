package com.itwill.salary.controller;

import com.itwill.salary.dto.SalaryDetailDTO;
import com.itwill.salary.dto.SalaryEmployeeDTO;
import com.itwill.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    // JSP 페이지 열기
    @GetMapping("")
    public String showSalaryPage() {
        return "salary/salary-detail"; // /WEB-INF/views/salary/salary-detail.jsp
    }

    // 급여 명세서 조회 (SELECT ONLY)
    @GetMapping("/view")
    @ResponseBody
    public SalaryDetailDTO viewSalary(
            @RequestParam("salMonth") String salMonth,
            HttpSession session) {

        SalaryEmployeeDTO loginUser = (SalaryEmployeeDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("로그인 정보 없음");
        }

        return salaryService.getSalaryDetail(loginUser.getEmpId(), salMonth);
    }

    // 엑셀 다운로드
    @GetMapping("/download/excel")
    public void downloadSalaryExcel(@RequestParam("salMonth") String salMonth,
                                     HttpServletResponse response,
                                     HttpSession session) throws IOException {

        SalaryEmployeeDTO loginUser = (SalaryEmployeeDTO) session.getAttribute("loginUser");
        if (loginUser == null) throw new RuntimeException("로그인 필요");

        String empId = loginUser.getEmpId();
        SalaryDetailDTO dto = salaryService.getSalaryDetail(empId, salMonth);

        // 파일명 한글 인코딩
        String filename = URLEncoder.encode("급여명세서_" + salMonth + ".xlsx", StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + filename);

        salaryService.writeSalaryExcel(dto, response.getOutputStream());
    }
    @GetMapping("/download/pdf")
    public void downloadSalaryPdf(@RequestParam("salMonth") String salMonth,
                                  HttpServletResponse response,
                                  HttpSession session) throws IOException {

        SalaryEmployeeDTO loginUser = (SalaryEmployeeDTO) session.getAttribute("loginUser");
        if (loginUser == null) throw new RuntimeException("로그인 필요");

        String empId = loginUser.getEmpId();
        SalaryDetailDTO dto = salaryService.getSalaryDetail(empId, salMonth);

        String filename = URLEncoder.encode("급여명세서_" + salMonth + ".pdf", StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + filename);

        salaryService.writeSalaryPdf(dto, response.getOutputStream());
    }
}