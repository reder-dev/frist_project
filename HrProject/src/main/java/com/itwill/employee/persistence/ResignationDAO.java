package com.itwill.employee.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.itwill.employee.domain.ResignationVO;

public interface ResignationDAO {
    void insertResignation(ResignationVO vo);
    List<ResignationVO> getAllResignations();
    ResignationVO getResignationById(int resignId);
    void approveResignation(@Param("resignId") int resignId, @Param("approver") String approver);
    void rejectResignation(@Param("resignId") int resignId, @Param("approver") String approver);
    void updateStatus(@Param("resignId") int resignId, @Param("status") String status, @Param("approver") String approver);
}
