package com.itwill.util;

import com.itwill.attendance.record.Attendance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelExporter {

    public static byte[] exportAttendanceToExcel(List<Attendance> records) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Attendance");

        // Header
        Row header = sheet.createRow(0);
        String[] columns = {"날짜", "사원ID", "출근", "퇴근", "근무형태"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Data
        int rowNum = 1;
        for (Attendance a : records) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(a.getDate().toString());
            row.createCell(1).setCellValue(a.getEmployeeId());
            row.createCell(2).setCellValue(a.getCheckIn() != null ? a.getCheckIn().toString() : "");
            row.createCell(3).setCellValue(a.getCheckOut() != null ? a.getCheckOut().toString() : "");
            row.createCell(4).setCellValue(a.getWorkType());
        }

        // Byte array return
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }
}
