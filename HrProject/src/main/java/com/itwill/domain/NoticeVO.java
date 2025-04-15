package com.itwill.domain;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class NoticeVO {
    private int not_id;                 // ������ȣ
    private String not_ti;              // ��������
    private String not_cn;              // ��������
    private String not_file;            // ÷������ ID
    
    private String emp_id;              // �ۼ��� �����ȣ
    
    private Timestamp not_wd;           // �ۼ���
    private Timestamp not_registdate;   // �������
    private String not_register;        // �����

    private Timestamp not_modifydate;   // ��������
    private String not_modifier;        // ������
}
