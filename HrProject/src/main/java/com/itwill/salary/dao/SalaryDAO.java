package com.itwill.salary.dao;

import com.itwill.salary.dto.AttendanceSummaryDTO;
import com.itwill.salary.dto.SalaryDetailDTO;
import com.itwill.salary.dto.SalaryEmployeeDTO;
import com.itwill.salary.dto.SalaryDetailDTO.SalaryItemDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SalaryDAO {

    private static final String NAMESPACE = "com.itwill.salary.mapper.SalaryMapper.";

    // 급여 요약 조회
    public SalaryDetailDTO selectSalaryById(SqlSession session, String salId) {
        return session.selectOne(NAMESPACE + "selectSalaryById", salId);
    }

    // 급여 상세 항목 조회
    public List<SalaryItemDTO> selectSalaryDetailItems(SqlSession session, String salId) {
        return session.selectList(NAMESPACE + "selectSalaryDetailItems", salId);
    }

    // 직원 검색
    public List<SalaryEmployeeDTO> searchEmployee(SqlSession session, String keyword) {
        return session.selectList(NAMESPACE + "searchEmployee", keyword);
    }

    // 근무 요약 조회
    public AttendanceSummaryDTO selectAttendanceSummary(SqlSession session, String empId, String salMonth) {
        Map<String, Object> param = new HashMap<>();
        param.put("empId", empId);
        param.put("salMonth", salMonth);
        return session.selectOne(NAMESPACE + "selectAttendanceSummary", param);
    }

    // 직책 수당 조회
    public Integer selectPositionAllowance(SqlSession session, String empId) {
        return session.selectOne(NAMESPACE + "selectPositionAllowance", empId);
    }

    // 급여 INSERT
    public void insertEmpSalary(SqlSession session, SalaryDetailDTO dto) {
        session.insert(NAMESPACE + "insertEmpSalary", dto);
    }

    // 급여 상세 항목 INSERT
    public void insertEmpSalaryDetail(SqlSession session, String salId, SalaryItemDTO item) {
        Map<String, Object> param = new HashMap<>();
        param.put("salId", salId);
        param.put("item", item);
        session.insert(NAMESPACE + "insertEmpSalaryDetail", param);
    }

    // 직원 정보 조회
    public SalaryEmployeeDTO findEmployeeInfo(SqlSession session, String empId) {
        return session.selectOne(NAMESPACE + "findEmployeeInfo", empId);
    }

    // 급여 중복 여부 체크
    public int countBySalId(SqlSession session, String salId) {
        return session.selectOne(NAMESPACE + "countBySalId", salId);
    }
}