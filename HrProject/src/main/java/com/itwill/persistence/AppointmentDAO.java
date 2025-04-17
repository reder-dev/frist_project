package com.itwill.persistence;

import java.util.List;
import com.itwill.domain.AppointmentVO;

public interface AppointmentDAO {
    List<AppointmentVO> getAppointmentsByEmpId(String empId);
}
