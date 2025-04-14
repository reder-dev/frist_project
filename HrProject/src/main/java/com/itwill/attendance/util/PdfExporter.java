package com.itwill.attendance.util;

import com.itwill.attendance.record.Attendance;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PdfExporter {

    public static byte[] exportAttendanceToPdf(List<Attendance> records) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        // 제목 설정
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Paragraph title = new Paragraph("출퇴근 기록", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        // 표 생성
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 4, 3});

        // 헤더
        Stream.of("사원번호", "날짜", "근무상태")
                .forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setPhrase(new Phrase(headerTitle));
                    table.addCell(header);
                });

        // 데이터 입력
        for (Attendance record : records) {
            table.addCell(record.getEmployeeId());
            table.addCell(record.getDate().toString()); //수정: getWorkDate → getDate
            table.addCell(record.getWorkType());
        }

        // 문서 마무리
        document.add(table);
        document.close();
        return out.toByteArray();
    }
}
