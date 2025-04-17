package com.itwill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.domain.AppointmentVO;
import com.itwill.persistence.AppointmentDAO;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Override
    public List<AppointmentVO> getAppointmentsByEmpId(String empId) {
        return appointmentDAO.getAppointmentsByEmpId(empId);
    }
}
