package com.itwill.employee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itwill.employee.domain.ResignationVO;
import com.itwill.employee.persistence.ResignationDAO;

@Service
public class ResignationServiceImpl implements ResignationService {

    @Autowired
    private ResignationDAO resignationDAO;

    @Override
    public void requestResignation(ResignationVO vo) {
        resignationDAO.insertResignation(vo);
    }

    @Override
    public List<ResignationVO> getAllResignations() {
        return resignationDAO.getAllResignations();
    }

    @Override
    public ResignationVO getResignationDetail(int resignId) {
        return resignationDAO.getResignationById(resignId);
    }

    @Override
    public void updateStatus(int resignId, String status, String approver) {
        resignationDAO.updateStatus(resignId, status, approver);
    }
}