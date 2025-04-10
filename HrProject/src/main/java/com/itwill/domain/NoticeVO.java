package com.itwill.domain;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class NoticeVO {
    private String not_id;                 // 공지번호
    private String not_ti;              // 공지제목
    private String not_cn;              // 공지내용
    private String not_file;            // 첨부파일 ID
    
    private String emp_id;              // 작성자 사원번호
    
    private Timestamp not_wd;           // 작성일
    private Timestamp not_registdate;   // 등록일자
    private String not_register;        // 등록자

    private Timestamp not_modifydate;   // 수정일자
    private String not_modifier;        // 수정자
}
