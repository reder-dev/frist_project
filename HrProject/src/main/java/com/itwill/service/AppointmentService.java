package com.itwill.service;

import java.util.List;
import com.itwill.domain.AppointmentVO;

public interface AppointmentService {
    List<AppointmentVO> getAppointmentsByEmpId(String empId);
}

