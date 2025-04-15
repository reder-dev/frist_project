package com.itwill.util;

import com.itwill.attendance.record.Attendance;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.util.stream.Stream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfExporter {

    public static byte[] exportAttendanceToPdf(List<Attendance> records) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Paragraph title = new Paragraph("출결 기록", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 3, 2, 2, 2});

        Stream.of("날짜", "사원ID", "출근", "퇴근", "근무형태")
                .forEach(header -> {
                    PdfPCell cell = new PdfPCell(new Phrase(header));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(cell);
                });

        for (Attendance a : records) {
            table.addCell(a.getDate().toString());
            table.addCell(a.getEmployeeId());
            table.addCell(a.getCheckIn() != null ? a.getCheckIn().toString() : "");
            table.addCell(a.getCheckOut() != null ? a.getCheckOut().toString() : "");
            table.addCell(a.getWorkType());
        }

        document.add(table);
        document.close();
        return out.toByteArray();
    }
}
