package com.itwill.employee.service;

import java.util.List;
import com.itwill.employee.domain.ResignationVO;

public interface ResignationService {
    void requestResignation(ResignationVO vo);
    List<ResignationVO> getAllResignations();
    ResignationVO getResignationDetail(int resignId);
    void updateStatus(int resignId, String status, String approver);
}
