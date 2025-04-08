package com.itwill.serivce;

import java.util.List;
import com.itwill.domain.EmployeeVO;

public interface EmployeeAdminService {
    
	//전체직원 정보 목록 조회
	List<EmployeeVO> findAll();
    
	//특정직원 정보 조회
    EmployeeVO findById(Long id);
    
    //특정직원 정보 수정
    void update(EmployeeVO employee);
    
}