package com.itwill.salary.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwill.salary.dto.SalaryDetailDTO;
import com.itwill.salary.dto.SalaryEmployeeDTO;

public interface SalaryService {

    // 급여 명세서 조회 (조회 전용)
    SalaryDetailDTO getSalaryDetail(String empId, String salMonth);
    
    void writeSalaryExcel(SalaryDetailDTO dto, OutputStream out) throws IOException;
    
    void writeSalaryPdf(SalaryDetailDTO dto, OutputStream out) throws IOException;
    
    SalaryDetailDTO calculateSalary(String empId, String salMonth);
    
    void confirmSalary(SalaryDetailDTO dto);
    
    List<SalaryEmployeeDTO> searchEmployee(String keyword);
}