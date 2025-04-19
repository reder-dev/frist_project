package com.itwill.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.employee.domain.AppointmentVO;
import com.itwill.employee.persistence.AppointmentDAO;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Override
    public List<AppointmentVO> getAppointmentsByEmpId(String empId) {
        return appointmentDAO.getAppointmentsByEmpId(empId);
    }

    // 관리자: 전체 조회
    public List<AppointmentVO> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    // 관리자: 단일 발령 조회
    public AppointmentVO getAppointmentById(int appointmentId) {
        return appointmentDAO.getAppointmentById(appointmentId);
    }

    // 관리자: 발령 등록
    public void registerAppointment(AppointmentVO appointment) {
        appointmentDAO.registerAppointment(appointment);
    }
}
