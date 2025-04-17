package com.itwill.persistence;

import java.util.List;
import com.itwill.domain.DepartmentVO;

public interface DepartmentDAO {
    List<DepartmentVO> getAllDepartments();
}
