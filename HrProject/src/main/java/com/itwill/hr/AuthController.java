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

			System.out.println("👉 member.getEmp_email(): " + member.getEmp_email());

			System.out.println("empId: " + empId);
			System.out.println("inputEmail: " + inputEmail);
			System.out.println("DB email: " + member.getEmp_email());

			System.out.println("👉 member: " + member); // 전체 객체 로그

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

//		    response.setResult("SUCCESS");
//		    response.setMessage("성공");
//		    Map<String ,Object> map12 = new HashMap<>();
//		    map12.put("song1", "1");
//		    map12.put("song2", "2");
//		    map12.put("song3", "3");
//		    map12.put("song4", "4");
//		    response.setResult(map12);

		// System.out.println("Request Content-Type: " + request.getContentType());
	}

	/*
	 * // 인증코드 확인
	 * 
	 * @PostMapping("/verifyCode")
	 * 
	 * @ResponseBody public ResponseAPI verifyCode(@RequestBody Map<String, String>
	 * data, HttpSession session) { String inputCode = data.get("code"); String
	 * sessionCode = (String) session.getAttribute("authCode"); String newPassword =
	 * data.get("newPassword"); // 사용자가 입력한 새 비밀번호 String empId = (String)
	 * session.getAttribute("empId");
	 * 
	 * ResponseAPI response = new ResponseAPI(); Map<String, Object> resultMap = new
	 * HashMap<>();
	 * 
	 * if (sessionCode == null || empId == null) { resultMap.put("status", "FAIL");
	 * resultMap.put("message", "인증 요청이 없습니다."); response.setResult(resultMap);
	 * return response; }
	 * 
	 * if (!inputCode.equals(sessionCode)) { resultMap.put("status", "FAIL");
	 * resultMap.put("message", "인증코드가 일치하지 않습니다."); response.setResult(resultMap);
	 * return response; }
	 * 
	 * // 비밀번호 유효성 검사 String pwPattern =
	 * "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,16}$";
	 * if (newPassword == null || !newPassword.matches(pwPattern)) {
	 * resultMap.put("status", "FAIL"); resultMap.put("message",
	 * "비밀번호는 8~16자의 영문, 숫자, 특수문자를 모두 포함해야 합니다."); response.setResult(resultMap);
	 * return response; }
	 * 
	 * // 비밀번호 업데이트 mService.updatePassword(empId, newPassword);
	 * 
	 * // 세션 정리 session.removeAttribute("authCode");
	 * session.removeAttribute("empId");
	 * 
	 * resultMap.put("status", "SUCCESS"); resultMap.put("message",
	 * "비밀번호가 성공적으로 변경되었습니다."); response.setResult(resultMap); return response; }
	 */

	@PostMapping("/verifyCode")
	@ResponseBody
	public ResponseAPI verifyCode(@RequestBody Map<String, String> data, HttpSession session) {
		String inputCode = data.get("code");
		String sessionCode = (String) session.getAttribute("authCode");

		ResponseAPI response = new ResponseAPI();
		Map<String, Object> resultMap = new HashMap<>();

		if (sessionCode == null) {
			resultMap.put("status", "FAIL");
			resultMap.put("message", "인증 요청이 없습니다.");
			response.setResult(resultMap);
			return response;
		}

		if (!inputCode.equals(sessionCode)) {
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
