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
}
