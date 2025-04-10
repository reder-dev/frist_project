package com.itwill.hr;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itiwll.domain.LoginHistoryVO;
import com.itiwll.domain.MemberVO;
import com.itwill.persistence.LoginHistoryDAO;
import com.itwill.service.EmailService;
import com.itwill.service.LoginHistoryService;
import com.itwill.service.MemberService;
import com.itwill.util.ResponseAPI;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService mService;
	
	@Inject
	private LoginHistoryService lService;
	
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
	public String memberLoginPOST( /*@ModelAttribute*/ MemberVO vo,HttpSession session, RedirectAttributes rttr, HttpServletRequest request
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
		String clientIp = request.getRemoteAddr();
		logger.info(" 결과 : {}",resultVO);
		
		
		// IPv6 로컬 주소일 경우 IPv4로 변환
		if ("0:0:0:0:0:0:0:1".equals(clientIp)) {
		    clientIp = "127.0.0.1";
		}
		
		// 이력 객체 생성
	    LoginHistoryVO history = new LoginHistoryVO();
	    history.setEmp_id(vo.getEmp_id());
	    history.setLogin_ip(clientIp);
		
		// 로그인 실패
		if(resultVO == null) {
			logger.info(" 로그인 실패! ");
			
			// 실패 시 기록
	        history.setLogin_status("INACTIVE");
	        history.setLogin_result("FAIL");
	        lService.insertLoginHistory(history);
	        
	        // 최근 실패 횟수 확인
	        int failCount = lService.countRecentFailedLogins(vo.getEmp_id());
	        logger.info("최근 실패 횟수: {}", failCount);
			
	        
	        if (failCount >= 5) {
	            logger.info("계정 잠금 처리!");
	            
	            // 상태를 LOCKED로 기록
	            history.setLogin_status("LOCKED"); // 👈 요거 추가
	            history.setLogin_result("FAIL");   // 실패지만 잠금임
	            lService.insertLoginHistory(history); // 다시 기록!
	            
	            
	            rttr.addFlashAttribute("message", "계정이 잠금되었습니다. 관리자에게 문의하세요.");
	        } else {
	            rttr.addFlashAttribute("message", "로그인 실패! (" + failCount + "회 실패)[5회 실패시 잠금]");
	        }
			
		    return "redirect:/member/login";
		}
		
		
		// 로그인 성공 시 기록
	    history.setLogin_status("ACTIVE");
	    history.setLogin_result("SUCCESS");
	    lService.insertLoginHistory(history);
	    

		// 세션 영역에 로그인 성공한 사용자의 아이디를 저장
		session.setAttribute("id",resultVO.getEmp_id());
		
		if ("1234".equals(resultVO.getEmp_pw())) {
            session.setAttribute("loginUser", resultVO.getEmp_id()); // 세션에 사용자 정보 저장
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
	
	/*
	 * @RequestMapping(value = "/find/phone",method = RequestMethod.GET) public
	 * String findMemberPhoneGET() {
	 * logger.info(" /findEmail -> findMemberPhoneGET()호출");
	 * 
	 * logger.info(" /findEmail.jsp 페이지로 이동"); return "/member/findPhone"; }
	 * 
	 * @PostMapping(value = "/find/phone") public String
	 * findMemberPhonePOST(MemberVO vo) {
	 * 
	 * return "/login"; }
	 */
	
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public String MemberMainGET() {
		logger.info(" /member/main -> MemberMainGET()호출");
		
		logger.info(" /member/main.jsp 페이지로 이동");
		return "/member/main";
	}
	
	@PostMapping(value = "/main")
	public String memberMainPOST(MemberVO vo) {
	    return "/member/main";
	}
	
	@RequestMapping(value = "/userinfo",method = RequestMethod.GET)
	public String MemberUserinfoGET() {
		logger.info(" /member/main -> MemberUserinfoGET()호출");
		
		logger.info(" /member/userinfo.jsp 페이지로 이동");
		return "/member/userinfo";
	}
	
	@PostMapping(value = "/userinfo")
	public String memberUserinfoPOST(MemberVO vo) {
	    return "/member/userinfo";
	}
	
	
	
	
}
