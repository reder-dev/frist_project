package com.itwill.persistence;

import java.util.List;

import com.itiwll.domain.MemberVO;

public interface MemberDAO {
	
	// 디비서버 시간정보 조회기능
	public String getServerTime();
	
	// 회원가입
	public void insertMember(MemberVO vo);
	
	// 로그인 체크
	public MemberVO loginMember(MemberVO loginVO);
	public MemberVO loginMember(String id,String pw);
	
	// 내정보 보기(정보 조회)
	public MemberVO getMemberInfo(String eid);
	
	// 회원정보 수정
	public int updateMember(MemberVO updateVO);
	
	// 회원정보 삭제
	public int deleteMember(MemberVO deleteVO);
	
	// 관리자 - 회원전체 목록조회
	public List<MemberVO> getMemberList();
}
