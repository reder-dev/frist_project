package com.itwill.approval.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FileDTO {
    private String fileId;               // UUID
    private String fileName;             // 원본 파일명
    private String filePath;             // 저장 경로
    private long fileSize;               // 크기
    private String fileType;             // MIME 타입
    private String referenceTableId;     // 결재문서 ID 등
    private String referenceTableName;   // 참조 테이블명
    private LocalDateTime uploadDate;    // 업로드 일시
    private String fileDescription;      // 설명 (선택)
}
