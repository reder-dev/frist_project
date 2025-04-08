package com.itwill.mapper;

import com.itiwll.domain.MemberVO;

public interface MemberMapper {
    MemberVO selectByEmpId(String empId); // 이 메서드명은 XML의 id랑 일치해야 함!
}