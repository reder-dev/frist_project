package com.itwill.attendance.util;

import com.itwill.attendance.record.Attendance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelExporter {

    public static byte[] exportAttendanceToExcel(List<Attendance> records) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Attendance");

        // 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("사원번호");
        headerRow.createCell(1).setCellValue("날짜");
        headerRow.createCell(2).setCellValue("근무상태");

        // 데이터 작성
        int rowNum = 1;
        for (Attendance record : records) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(record.getEmployeeId());
            row.createCell(1).setCellValue(record.getDate().toString()); // ✅ 여기 수정!
            row.createCell(2).setCellValue(record.getWorkType());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        return bos.toByteArray();
    }
}
