package com.itwill.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwill.domain.MemberVO;
import com.itwill.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	
	// DAO동작을 호출
	private static final Logger logger 
	   = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Inject
	private MemberDAO mdao;
	
//	@Override
//	public void memberJoin(MemberVO vo) {
//		logger.info(" 회원가입 실행 -> DAO 메서드 호출 ");
//		mdao.insertMember(vo);
//	}

	@Override
	public MemberVO memberLoginCheck(MemberVO loginVO) {
		logger.info(" memberLoginCheck() 실행 ");
		logger.info(" DAO메서드 호출 ");
		
		
		MemberVO resultVO = mdao.loginMember(loginVO);
		logger.info(" SQL 구문 실행완료! ");
		
		return resultVO;
	}

	@Override
	public MemberVO memberInfo(String userid) {
		logger.info("memberInfo(String userid) 호출 ");
		// DAO동작을 호출	
		
		return mdao.getMemberInfo(userid);
	}

//	@Override
//	public int memberUpdate(MemberVO updateVO) {
//		logger.info(" memberUpdate(MemberVO updateVO) 호출 ");
//		
//		return mdao.updateMember(updateVO);
//	}

	@Override
	public int memberDelete(MemberVO deleteVO) {
		logger.info(" memberDelete(MemberVO deleteVO) 호출 ");
		
		return mdao.deleteMember(deleteVO);
	}

	@Override
	public List<MemberVO> memberList() {
		logger.info(" memberList() 호출 ");
		return mdao.getMemberList();
	}
	
	@Override
	public MemberVO getMemberById(String empId) {
	    return mdao.selectByEmpId(empId);
	}

	@Override
	public int updatePassword(String empId, String newPassword) {
	    return mdao.updatePassword(empId, newPassword); // ✅ 정확하게 분리된 메서드로!
	}

	
	
		
}
