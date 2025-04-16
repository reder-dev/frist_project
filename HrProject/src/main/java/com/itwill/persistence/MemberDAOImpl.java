package com.itwill.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwill.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private static final Logger logger 
	  = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// 의존성 주입  =>  약한결합
	@Inject
	private SqlSession sqlSession;
	
	// mapper에 접근하는 주소(위치)
	private static final String NAMESPACE 
	       = "com.itwillbs.mapper.MemberMapper."; 
	
	
	@Override
	public String getServerTime() {
		
		// 디비 연결
		//SqlSession sqlSession = sqlFactory.openSession();
		
		// 필요한 SQL구문 실행
		//sqlSession.selectOne("mapper에 설정된 SQL구문");
	    String time 
	      = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper"+".getTime");
		
	    logger.debug("@@@@@@@@SQL 실행성공!@@@@@@@@@@");
	    logger.info("@@@@@@@@SQL 실행성공!@@@@@@@@@@");
	    System.out.println(" SQL 실행 성공! ");
	    System.out.println(time);
		
		return time;
	}


	@Override
	public void insertMember(MemberVO vo) {
		logger.info(" insertMember(MemberVO vo) 호출 ");
		//1.2. 디비연결 => 생략 (SqlSession 객체 주입)
		//3. SQL구문 => mapper.xml 작성 
		//   & pstmt객체 => 생략 (SqlSession 객체 주입)
		//3-1.  ???? => 생략 (Mybatis설정) 
		//4. SQL 실행 => 변경 (SqlSession 객체 주입)
		//sqlSession.insert(statement);
		// -> sql구문만 가지고 실행
		//sqlSession.insert(statement, parameter);
		// -> sql구문 + 전달인자를 사용 실행
		//               "com.itwillbs.mapper.MemberMapper.insertMember"
		sqlSession.insert(NAMESPACE + "insertMember", vo);
	}


	@Override
	public MemberVO loginMember(MemberVO loginVO) {
		logger.info(" loginMember(MemberVO loginVO) 호출 ");
		
		//mapper-sql구문 호출(실행)
		//  DTO dto = new DTO();
		//  dto.setId(rs.getString("id"));  
		// => 코드 생략 / Mybatis가 대신수행해줌
		MemberVO resultVO
		    = sqlSession.selectOne(NAMESPACE + "loginCheck",loginVO);
		
		logger.info(" resultVO :"+resultVO);
		
		return resultVO;
	}


	@Override
	public MemberVO loginMember(String id, String pw) {
		logger.info(" loginMember(String id, String pw) 호출 ");
		
		// * 가정) 전달받은 매개변수의 값이 하나의 객체(DTO/VO)에 
		//         저장이 불가능한 경우 (SQL구문 - join)
		// * 컬렉션(Map) - 데이터를 Key:Value 쌍으로 저장
//		Map<String, String> paramMap = new HashMap<String, String>();
//		//paramMap.put("SQL구문에서 매핑된 이름", 값);
//		paramMap.put("userid", id);
//		paramMap.put("userpw", pw);
//		
//		 sqlSession.selectOne(NAMESPACE + "loginCheck",paramMap);
		
		MemberVO vo = new MemberVO();
		vo.setEmp_id(id);
		vo.setEmp_pw(pw);
		
		//sqlSession.selectOne(NAMESPACE + "loginCheck",id,pw);
		// => 매개변수를 2개만 사용가능(전달정보는 1개만 사용가능)
		// *  mapper에 1개이상의 정보를 전달해야하는 경우는
		//    반드시 객체에 담아서 전달해야함!
		MemberVO resultVO =
		  sqlSession.selectOne(NAMESPACE + "loginCheck",vo);
		
		return resultVO;
	}


	@Override
	public MemberVO getMemberInfo(String userid) {
		
		logger.info("getMemberInfo(String userid) 호출 ");
		
		// SQL 실행
		MemberVO resultVO =
		sqlSession.selectOne(NAMESPACE + "getMemberInfo",userid);
		logger.info(" SQL-내정보 보기 실행완료! ");
		
		//수정필요시) 
		//resultVO.setUserid( resultVO.getUserid() +"@@@@" );
		
		return resultVO;
		// => DB에서 생성된 데이터를 (수정가능성 있음) 전달
		
		//return sqlSession.selectOne(NAMESPACE + "getMemberInfo",userid);
		// => DB에서 생성된 데이터를 (바로/그대로) 전달
	}


//	@Override
//	public int updateMember(MemberVO updateVO) {
//		logger.info(" updateMember(MemberVO updateVO) 호출  ");
//		
//		int result = 
//		  sqlSession.update(NAMESPACE + "updateMember",updateVO);
//		
//		return result;
//	}


	@Override
	public int deleteMember(MemberVO deleteVO) {
		logger.info(" deleteMember(MemberVO deleteVO) 호출 ");
		
		return sqlSession.delete(NAMESPACE + "deleteMember",deleteVO);
	}


	@Override
	public List<MemberVO> getMemberList() {
		logger.info(" getMemberList() 호출 ");
		
		// select 구문의 결과를 List형태로 만들어주는 메서드
		// => (rs -> DTO -> List)작업을 한번에 처리
		List<MemberVO> memberList =
		  sqlSession.selectList(NAMESPACE + "getMemberList");
		
		return memberList;
	}
	
	@Override
	public MemberVO selectByEmpId(String empId) {
		MemberVO member = sqlSession.selectOne(NAMESPACE + "selectByEmpId", empId);
		return member;
	}
	
	@Override
	public int updatePassword(String empId, String newPassword) {
		return sqlSession.update(NAMESPACE + "updatePassword", 
		        Map.of("empId", empId, "newPassword", newPassword));
	}
	
	
	

}
