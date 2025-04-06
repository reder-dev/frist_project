package com.itwill.service;

import java.util.List;

import com.itiwll.domain.MemberVO;

public interface MemberService {

	// 회원가입 처리동작
	public void memberJoin(MemberVO vo);
	
	// 회원정보 로그인 체크동작
	public MemberVO memberLoginCheck(MemberVO loginVO);
	
	// 회원정보 조회
	public MemberVO memberInfo(String userid);
	
	// 회원정보 수정
	public int memberUpdate(MemberVO updateVO);
	
	// 회원정보 삭제
	public int memberDelete(MemberVO deleteVO);
	
	// 회원정보 목록조회
	public List<MemberVO> memberList();
		
	
	
}
