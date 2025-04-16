package com.itwill.service;

import java.util.List;

import com.itwill.domain.MemberVO;

public interface MemberService {

//	// 회원가입 처리동작
//	public void memberJoin(MemberVO vo);
	
	// 회원정보 로그인 체크동작
	public MemberVO memberLoginCheck(MemberVO loginVO);
	
	// 회원정보 조회
	public MemberVO memberInfo(String userid);
	
//	// 회원정보 수정
//	public int memberUpdate(MemberVO updateVO);
	
	// 회원정보 삭제
	public int memberDelete(MemberVO deleteVO);
	
	// 회원정보 목록조회
	public List<MemberVO> memberList();
	
	// 사원번호로 회원 조회 (이메일 확인용)	
	public MemberVO getMemberById(String empId);
	
	// 비밀번호만 수정하는 메서드 추가
	public int updatePassword(String empId, String newPassword);

	
	
}
