package com.itwill.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.domain.EmailVerificationVO;
import com.itwill.domain.MemberVO;
import com.itwill.service.EmailService;
import com.itwill.service.EmailVerificationService;
import com.itwill.service.MemberService;
import com.itwill.util.ResponseAPI;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Inject
	private MemberService mService;

	@Inject
	private EmailService emailService;
	
	@Inject
	private EmailVerificationService emailVService;
	

	// 인증코드 전송
	@PostMapping(value = "/sendCode")
	@ResponseBody
	public ResponseAPI sendVerificationCode(@RequestBody Map<String, String> data, HttpSession session) {
		String empId = data.get("emp_id");
		String inputEmail = data.get("email");

		ResponseAPI response = new ResponseAPI();

		Map<String, Object> resultMap = new HashMap<>();

		try {
			// DB에서 emp_id로 이메일 조회

			MemberVO member = mService.getMemberById(empId);

			if (member == null) {
				resultMap.put("status", "FAIL");
				resultMap.put("message", "사원번호가 존재하지 않습니다.");
				response.setResult(resultMap);
				return response;
			}

						
			if (member.getEmp_email() == null || !inputEmail.equals(member.getEmp_email())) {
				resultMap.put("status", "FAIL");
				resultMap.put("message", "이메일이 일치하지 않습니다.");
				response.setResult(resultMap);
				return response;
			}

			// 인증코드 생성
			String code = String.valueOf((int) (Math.random() * 900000) + 100000);

			// 메일 전송
			emailService.sendEmail(inputEmail, "인증코드", "인증코드는: " + code);
			
			// 인증 정보를 EMAIL_VERIFICATION 테이블에 저장
	        EmailVerificationVO verification = new EmailVerificationVO();
	        verification.setEmp_id(empId);
	        verification.setEmail(inputEmail);
	        verification.setUnlock_code(code);
	        verification.setCreated_at(LocalDateTime.now()); // 인증 요청 시간 저장
	        verification.setVerexp_at(LocalDateTime.now().plusMinutes(5)); // 인증 만료 시간 5분 후
	        verification.setVerified(false);
	        
	        emailVService.saveVerification(verification); // 인증 정보 저장

			// 세션에 저장
			session.setAttribute("authCode", code);
			session.setAttribute("authEmail", inputEmail);
			session.setAttribute("empId", empId);

			resultMap.put("status", "SUCCESS");
			resultMap.put("message", "인증코드가 이메일로 전송되었습니다.");
			response.setResult(resultMap);

			return response;

		} catch (Exception e) {
			e.printStackTrace(); // 서버 로그 확인용
			resultMap.put("message", "서버 에러가 발생했습니다.");
			response.setResult(resultMap);
			return response;
		}


	}

	

	@PostMapping("/verifyCode")
	@ResponseBody
	public ResponseAPI verifyCode(@RequestBody Map<String, String> data, HttpSession session) {
		String empId = (String) session.getAttribute("empId");
	    String inputCode = data.get("code");

		ResponseAPI response = new ResponseAPI();
		Map<String, Object> resultMap = new HashMap<>();

		if (empId == null) {
	        resultMap.put("status", "FAIL");
	        resultMap.put("message", "인증 요청이 없습니다.");
	        response.setResult(resultMap);
	        return response;
	    }

	    // DB에서 인증 정보 조회
	    EmailVerificationVO verification = emailVService.getVerificationByEmpId(empId);
		
	    if (verification == null) {
	        resultMap.put("status", "FAIL");
	        resultMap.put("message", "인증 정보를 찾을 수 없습니다.");
	        response.setResult(resultMap);
	        return response;
	    }
		

	    // 인증 코드 만료 체크
	    if (verification.getVerexp_at().isBefore(LocalDateTime.now())) {
	        resultMap.put("status", "FAIL");
	        resultMap.put("message", "인증번호가 만료되었습니다.");
	        response.setResult(resultMap);
	        return response;
	    }

	    // 인증 코드 비교
	    if (!inputCode.equals(verification.getUnlock_code())) {
	        resultMap.put("status", "FAIL");
	        resultMap.put("message", "인증코드가 일치하지 않습니다.");
	        response.setResult(resultMap);
	        return response;
	    }

		// 인증 성공 시 세션에 인증 상태 저장
	    session.setAttribute("verified", true);
	    resultMap.put("status", "SUCCESS");
	    resultMap.put("message", "인증코드가 확인되었습니다.");
	    response.setResult(resultMap);
	    return response;
	}

	@PostMapping("/resetPassword")
	@ResponseBody
	public ResponseAPI resetPassword(@RequestBody Map<String, String> data, HttpSession session) {
		ResponseAPI response = new ResponseAPI();
		Map<String, Object> resultMap = new HashMap<>();

		Boolean isVerified = (Boolean) session.getAttribute("verified");
		String empId = (String) session.getAttribute("empId");

		if (isVerified == null || !isVerified || empId == null) {
			resultMap.put("status", "FAIL");
			resultMap.put("message", "인증이 완료되지 않았습니다.");
			response.setResult(resultMap);
			return response;
		}
		
		// EMAIL_VERIFICATION 테이블에서 인증 정보 조회
	    EmailVerificationVO verification = emailVService.getVerificationByEmpId(empId);

	    if (verification == null) {
	        resultMap.put("status", "FAIL");
	        resultMap.put("message", "인증 정보를 찾을 수 없습니다.");
	        response.setResult(resultMap);
	        return response;
	    }

	    // 인증 코드 만료 확인
	    if (verification.getVerexp_at().isBefore(LocalDateTime.now())) {
	        resultMap.put("status", "FAIL");
	        resultMap.put("message", "인증번호가 만료되었습니다.");
	        response.setResult(resultMap);
	        return response;
	    }
		

		String newPassword = data.get("newPassword");
		String pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,16}$";

		if (newPassword == null || !newPassword.matches(pwPattern)) {
			resultMap.put("status", "FAIL");
			resultMap.put("message", "비밀번호는 8~16자의 영문, 숫자, 특수문자를 모두 포함해야 합니다.");
			response.setResult(resultMap);
			return response;
		}

		mService.updatePassword(empId, newPassword);

		// 세션 정리
		session.invalidate();

		resultMap.put("status", "SUCCESS");
		resultMap.put("message", "비밀번호가 성공적으로 변경되었습니다.");
		response.setResult(resultMap);
		return response;
	}

}
