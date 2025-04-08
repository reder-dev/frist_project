package com.itwill.hr;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itiwll.domain.MemberVO;
import com.itwill.service.EmailService;
import com.itwill.service.MemberService;
import com.itwill.util.ResponseAPI;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Inject
	private MemberService mService;

	@Inject
    private EmailService emailService;

	// 인증코드 전송
		@PostMapping(value = "/sendCode")
		@ResponseBody
		public ResponseAPI sendVerificationCode(@RequestBody Map<String, String> data, HttpSession session) {
		    String empId = data.get("emp_id");
		    String inputEmail = data.get("emp_email");
		    
		    ResponseAPI response = new ResponseAPI();
//		    response.setResult("SUCCESS");
//		    response.setMessage("성공");
		    Map<String ,Object> map1 = new HashMap<>();
		    map1.put("song1", "1");
		    map1.put("song2", "2");
		    map1.put("song3", "3");
		    map1.put("song4", "4");
		    response.setResult(map1);
		    
		    
		    //System.out.println("Request Content-Type: " + request.getContentType());

		    
		    // DB에서 emp_id로 이메일 조회
		    
		    MemberVO member = mService.getMemberById(empId);
	       
		    
	        
	        return response;
		}
		
		// 인증코드 확인
		@PostMapping("/verifyCode")
		@ResponseBody
		public String verifyCode(@RequestBody Map<String, String> data, HttpSession session) {
		    String inputCode = data.get("code");
		    String sessionCode = (String) session.getAttribute("authCode");

		    if (sessionCode == null) return "인증 요청이 없습니다.";
		    if (!inputCode.equals(sessionCode)) return "인증코드가 일치하지 않습니다.";

		    return "인증 성공!";
		}
	
	
}
