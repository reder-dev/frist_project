package com.itwill.employee.persistence;

import java.util.List;

import com.itwill.employee.domain.AppointmentVO;

public interface AppointmentDAO {

    // 관리자용 전체 발령 조회
    List<AppointmentVO> getAllAppointments();

    // 관리자용 발령 상세 조회
    AppointmentVO getAppointmentById(int appointmentId);

    // 관리자용 발령 등록
    void registerAppointment(AppointmentVO appointment);

    // 사용자용: 해당 사원의 발령 내역 조회
    List<AppointmentVO> getAppointmentsByEmpId(String empId);
}
