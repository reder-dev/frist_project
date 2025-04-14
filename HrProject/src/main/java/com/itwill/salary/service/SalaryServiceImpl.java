package com.itwill.salary.service;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import com.itwill.salary.dao.SalaryDAO;
import com.itwill.salary.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final ServletContext servletContext;
    private final SqlSession sqlSession;
    private final SalaryDAO salaryDAO;

    @Override
    public SalaryDetailDTO getSalaryDetail(String empId, String salMonth) {
        String salId = empId + salMonth;
        SalaryDetailDTO salary = salaryDAO.selectSalaryById(sqlSession, salId);
        if (salary == null) {
            throw new RuntimeException("해당 월의 급여 명세서가 존재하지 않습니다.");
        }
        List<SalaryDetailDTO.SalaryItemDTO> details = salaryDAO.selectSalaryDetailItems(sqlSession, salId);
        salary.setDetailList(details);
        return salary;
    }

    @Override
    public void writeSalaryExcel(SalaryDetailDTO dto, OutputStream out) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("급여명세서");

        int rowIdx = 0;
        Row titleRow = sheet.createRow(rowIdx++);
        titleRow.createCell(0).setCellValue("급여 명세서");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

        sheet.createRow(rowIdx++).createCell(0).setCellValue("이름: " + dto.getEmpName());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("부서: " + dto.getDeptName());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("지급일: " + dto.getPayDate());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("지급연월: " + dto.getSalMonth());
        rowIdx++;

        Row header = sheet.createRow(rowIdx++);
        header.createCell(0).setCellValue("항목명");
        header.createCell(1).setCellValue("구분");
        header.createCell(2).setCellValue("금액");

        for (SalaryDetailDTO.SalaryItemDTO item : dto.getDetailList()) {
            if (item.getAmount().compareTo(BigDecimal.ZERO) == 0) continue;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(item.getItemName());
            row.createCell(1).setCellValue(item.getItemType().equals("P") ? "지급" : "공제");
            row.createCell(2).setCellValue(item.getAmount().doubleValue());
        }

        rowIdx++;
        sheet.createRow(rowIdx++).createCell(0).setCellValue("총 지급액: " + dto.getTotalPay());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("총 공제액: " + dto.getTotalDeductions());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("실 수령액: " + dto.getNetPay());

        workbook.write(out);
        workbook.close();
    }

    @Override
    public void writeSalaryPdf(SalaryDetailDTO dto, OutputStream out) throws IOException {
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont(
                servletContext.getRealPath("/resources/fonts/Pretendard-Regular.otf"),
                PdfEncodings.IDENTITY_H
        );
        document.setFont(font);

        document.add(new Paragraph("급여 명세서").setBold().setFontSize(16));
        document.add(new Paragraph("이름: " + dto.getEmpName()));
        document.add(new Paragraph("부서: " + dto.getDeptName()));
        document.add(new Paragraph("지급일: " + dto.getPayDate()));
        document.add(new Paragraph("지급연월: " + dto.getSalMonth()));
        document.add(new Paragraph("\n"));

        Table table = new Table(UnitValue.createPercentArray(new float[]{4, 2})).useAllAvailableWidth();
        table.addHeaderCell("항목명");
        table.addHeaderCell("금액(원)");

        for (SalaryDetailDTO.SalaryItemDTO item : dto.getDetailList()) {
            if (item.getAmount() == null || item.getAmount().intValue() == 0) continue;
            String label = (item.getItemType().equals("P") ? "[지급] " : "[공제] ") + item.getItemName();
            table.addCell(label);
            table.addCell(String.format("%,d", item.getAmount().intValue()));
        }

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("총 지급액: " + String.format("%,d", dto.getTotalPay()) + " 원"));
        document.add(new Paragraph("총 공제액: " + String.format("%,d", dto.getTotalDeductions()) + " 원"));
        document.add(new Paragraph("실 수령액: " + String.format("%,d", dto.getNetPay()) + " 원").setBold());
        document.close();
    }

    @Override
    public SalaryDetailDTO calculateSalary(String empId, String salMonth) {
        AttendanceSummaryDTO att = salaryDAO.selectAttendanceSummary(sqlSession, empId, salMonth);
        Integer posAllow = salaryDAO.selectPositionAllowance(sqlSession, empId);
        if (posAllow == null) posAllow = 0;
        SalaryEmployeeDTO empInfo = salaryDAO.findEmployeeInfo(sqlSession, empId);

        int basePay = 2_500_000;
        int nightPay = att.getNightHours() * 15_000;
        int holidayPay = att.getHolidayHours() * 20_000;
        int positionPay = posAllow;
        int grossIncome = basePay + positionPay + nightPay + holidayPay;

        int incomeTax = (int)(grossIncome * 0.035);
        int localTax = (int)(incomeTax * 0.10);
        int pension = (int)((basePay + positionPay) * 0.045);
        int health = (int)((basePay + positionPay) * 0.03545);
        int care = (int)(health * 0.1281);
        int employment = (int)((basePay + positionPay) * 0.009);

        List<SalaryDetailDTO.SalaryItemDTO> items = new ArrayList<>();
        items.add(new SalaryDetailDTO.SalaryItemDTO("BASE", "기본급", "P", BigDecimal.valueOf(basePay)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("POSITION", "직책수당", "P", BigDecimal.valueOf(positionPay)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("NIGHT", "야간수당", "P", BigDecimal.valueOf(nightPay)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("HOLIDAY", "휴일수당", "P", BigDecimal.valueOf(holidayPay)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("INCOME_TAX", "소득세", "D", BigDecimal.valueOf(incomeTax)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("LOCAL_TAX", "지방소득세", "D", BigDecimal.valueOf(localTax)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("PENSION", "국민연금", "D", BigDecimal.valueOf(pension)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("HEALTH", "건강보험", "D", BigDecimal.valueOf(health)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("CARE", "장기요양보험", "D", BigDecimal.valueOf(care)));
        items.add(new SalaryDetailDTO.SalaryItemDTO("EMPLOYMENT", "고용보험", "D", BigDecimal.valueOf(employment)));

        int totalPay = items.stream().filter(i -> i.getItemType().equals("P")).mapToInt(i -> i.getAmount().intValue()).sum();
        int totalDeduct = items.stream().filter(i -> i.getItemType().equals("D")).mapToInt(i -> i.getAmount().intValue()).sum();
        int netPay = totalPay - totalDeduct;

        SalaryDetailDTO dto = new SalaryDetailDTO();
        dto.setEmpId(empId);
        dto.setSalMonth(salMonth);
        dto.setEmpName(empInfo.getEmpName());
        dto.setDeptName(empInfo.getDeptName());
        dto.setTotalPay(totalPay);
        dto.setTotalDeductions(totalDeduct);
        dto.setNetPay(netPay);
        dto.setPayDate(LocalDate.now().toString());
        dto.setDetailList(items);
        return dto;
    }

    @Override
    public void confirmSalary(SalaryDetailDTO dto) {
        String salId = dto.getEmpId() + dto.getSalMonth();
        dto.setSalId(salId);
        int count = salaryDAO.countBySalId(sqlSession, salId);
        if (count > 0) {
            throw new RuntimeException("이미 확정된 급여입니다.");
        }
        salaryDAO.insertEmpSalary(sqlSession, dto);
        for (SalaryDetailDTO.SalaryItemDTO item : dto.getDetailList()) {
            salaryDAO.insertEmpSalaryDetail(sqlSession, salId, item);
        }
    }
    
    @Override
    public List<SalaryEmployeeDTO> searchEmployee(String keyword) {
        return salaryDAO.searchEmployee(sqlSession, keyword);
    }

}