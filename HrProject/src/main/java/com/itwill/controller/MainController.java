package com.itwill.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 메인페이지 - GET
		@RequestMapping(value = "/main",method = RequestMethod.GET)
		public String mainGET(HttpSession session,
				              @SessionAttribute(name = "id",required = false) String sid  ) {
			logger.info(" mainGET() ");
			
			logger.info(" sid : "+sid);
			
			// 로그인 여부에 따라서 페이지 이동처리
			String id = (String) session.getAttribute("id");
			if(id == null) {
				logger.info(" 로그인 정보가 없음! ");
				return "redirect:/member/login";
			}
			// 연결된 뷰페이지로 이동
			logger.info(" /main.jsp 페이지 이동 ");
			return "/main";
		}
	
	

}
