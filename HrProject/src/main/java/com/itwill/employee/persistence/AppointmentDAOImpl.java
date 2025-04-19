package com.itwill.employee.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.employee.domain.AppointmentVO;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.itwill.employee.persistence.AppointmentDAO";

    @Override
    public List<AppointmentVO> getAllAppointments() {
        return sqlSession.selectList(NAMESPACE + ".getAllAppointments");
    }

    @Override
    public AppointmentVO getAppointmentById(int appointmentId) {
        return sqlSession.selectOne(NAMESPACE + ".getAppointmentById", appointmentId);
    }

    @Override
    public void registerAppointment(AppointmentVO appointment) {
        sqlSession.insert(NAMESPACE + ".registerAppointment", appointment);
    }

    @Override
    public List<AppointmentVO> getAppointmentsByEmpId(String empId) {
        return sqlSession.selectList(NAMESPACE + ".getAppointmentsByEmpId", empId);
    }
}
