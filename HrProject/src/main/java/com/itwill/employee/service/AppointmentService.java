package com.itwill.employee.service;

import java.util.List;

import com.itwill.employee.domain.AppointmentVO;

public interface AppointmentService {
    
    // 사용자: 개인 발령 조회
    List<AppointmentVO> getAppointmentsByEmpId(String empId);

    // 관리자: 전체 발령 조회
    List<AppointmentVO> getAllAppointments();

    // 관리자: 단일 발령 상세 조회
    AppointmentVO getAppointmentById(int appointmentId);

    // 관리자: 발령 등록
    void registerAppointment(AppointmentVO appointment);
}
