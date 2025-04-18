package com.itwill.employee.persistence;

import java.util.List;

import com.itwill.employee.domain.AppointmentVO;

public interface AppointmentDAO {
    List<AppointmentVO> getAppointmentsByEmpId(String empId);
}
