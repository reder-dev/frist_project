package com.itwill.employee.service;

import java.util.List;

import com.itwill.employee.domain.AppointmentVO;

public interface AppointmentService {
    List<AppointmentVO> getAppointmentsByEmpId(String empId);
}

