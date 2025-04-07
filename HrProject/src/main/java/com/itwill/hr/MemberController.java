package com.itwill.hr;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itiwll.domain.MemberVO;
import com.itwill.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService mService;
	
	// http://localhost:8088/web/MemberJoin.me (x)
	// http://localhost:8088/web/member/MemberJoin.me (o)
	// http://localhost:8088/web/member/join (o)
	// http://localhost:8088/member/join (o)
	 /*// 회원가입 - 정보입력(GET)
	
	 * @RequestMapping(value = "/join",method = RequestMethod.GET) public void
	 * memberJoinGET() { logger.info("/member/join -> memberJoinGET() 호출");
	 * 
	 * //연결된 JSP 뷰페이지로 이동 logger.info(" /WEB-INF/views/member/join.jsp 페이지 이동  "); }
	 * // http://localhost:8088/web/member/MemberJoinAction.me (o) //
	 * http://localhost:8088/web/member/join (o) // 회원가입 - 정보처리(POST)
	 * 
	 * @RequestMapping(value = "/join",method = RequestMethod.POST) public String
	 * memberJoinPOST(MemberVO vo) { logger.info("memberJoinPOST() 호출");
	 * 
	 * //폼태그 전달정보 저장 logger.info(" vo : "+vo);
	 * 
	 * //전달받은 정보를 처리 => 디비에 정보를 저장 //MemberDAO mdao = new MemberDAOImpl();(X)
	 * //mdao.insertMember(vo); (x) // 서비스 호출 (DAO 대신 호출) mService.memberJoin(vo);
	 * 
	 * logger.info(" 회원가입 성공! ");
	 * 
	 * // 로그인 페이지로 이동 return "redirect:/member/login"; }
	 */
	
	// 로그인
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String memberLoginGET() {
		logger.info(" /login -> memberLoginGET()호출");
		
		logger.info(" /login.jsp 페이지로 이동");
		return "/login";
	}
	
	// HTTP 상태 405 – 허용되지 않는 메소드
	// Request method 'POST' not supported
	// => GET방식의 주소는 있지만 POST방식의 주소 호출이 없는경우 에러
	
	//  ~~ /member/login 
	//  [ body ]
	//  userid = "....."
	//  userpw = "....."
	
	// 로그인 POST
	@PostMapping(value = "/login")
	public String memberLoginPOST( /*@ModelAttribute*/ MemberVO vo,HttpSession session, RedirectAttributes rttr
									 /* @ModelAttribute("userid") String userid,
									 * @RequestParam("userpw") String userpw
									 */) {
		logger.info("  memberLoginPOST() 호출 ");
		// 한글처리 인코딩 => 생략(web.xml-필터사용)
		// 전달된 정보 저장(id,pw)
		//logger.info(" userid : "+userid );
		//logger.info(" userpw : "+userpw );
		logger.info(" 로그인 vo : "+vo);
		
		// 서비스에 로그인체크 동작을 호출해서 확인
		MemberVO resultVO = mService.memberLoginCheck(vo);
		logger.info(" 결과 : {}",resultVO);
		
		// 로그인 실패
		if(resultVO == null) {
			logger.info(" 로그인 실패! ");
			return "redirect:/login";
		}

		// 세션 영역에 로그인 성공한 사용자의 아이디를 저장
		session.setAttribute("id",resultVO.getEmp_id());
		
		if ("1234".equals(vo.getEmp_pw())) {
            session.setAttribute("loginUser", vo.getEmp_id()); // 세션에 사용자 정보 저장
            return "redirect:/member/userinfo"; // 특정 페이지로 이동
        } else {
            rttr.addFlashAttribute("message", "정상 로그인 되었습니다.");
            return "redirect:/member/main"; // 메인 페이지로 이동
        }
		
	}
	
	@RequestMapping(value = "/find",method = RequestMethod.GET)
	public String findMemberInfoGET() {
		logger.info(" /find -> findMemberInfoGET()호출");
		
		logger.info(" /find.jsp 페이지로 이동");
		return "/member/find";
	}
	
	
	@RequestMapping(value = "/find/email",method = RequestMethod.GET)
	public String findMemberEmailGET() {
		logger.info(" /findEmail -> findMemberEmailGET()호출");
		
		logger.info(" /findEmail.jsp 페이지로 이동");
		return "/member/findEmail";
	}
	
	@PostMapping(value = "/find/email")
	public String findMemberEmailPOST(MemberVO vo) {
		
		return "/login";
	}
	
	@RequestMapping(value = "/find/phone",method = RequestMethod.GET)
	public String findMemberPhoneGET() {
		logger.info(" /findEmail -> findMemberPhoneGET()호출");
		
		logger.info(" /findEmail.jsp 페이지로 이동");
		return "/member/findPhone";
	}
	
	@PostMapping(value = "/find/phone")
	public String findMemberPhonePOST(MemberVO vo) {
		
		return "/login";
	}
	
	@RequestMapping(value = "/member/main",method = RequestMethod.GET)
	public String MemberMainGET() {
		logger.info(" /member/main -> MemberMainGET()호출");
		
		logger.info(" /member/main.jsp 페이지로 이동");
		return "/member/main";
	}
	
	@PostMapping(value = "/member/main")
	public String memberMainPOST(MemberVO vo) {
	    return "/member/main";
	}
	
	
}
