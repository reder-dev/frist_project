package com.itwill.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.domain.AppointmentVO;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

	private static final String NAMESPACE = "com.itwill.persistence.AppointmentDAO";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<AppointmentVO> getAppointmentsByEmpId(String empId) {
        return sqlSession.selectList(NAMESPACE + ".getAppointmentsByEmpId", empId);
    }
}
