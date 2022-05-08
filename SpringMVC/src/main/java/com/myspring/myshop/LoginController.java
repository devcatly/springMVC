package com.myspring.myshop;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.myspring.domain.MemberVO;
import com.myspring.domain.NotMemberException;
import com.myspring.service.UserService;

@Controller
public class LoginController {
	
	private static Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	private UserService userService;
	
	@PostMapping("/login")
	public String loginEnd(HttpSession session, 
			HttpServletResponse res,
			@ModelAttribute("user") MemberVO user) throws NotMemberException {
		logger.info("user={}", user);
		if(user.getUserid().isEmpty()||user.getPwd().isEmpty()) {
			return "redirect:index";
		}
		MemberVO loginUser=userService.loginCheck(user);
		if(loginUser!=null) {
			//로그인 인증을 받았다면 세션에 저장
			session.setAttribute("loginUser", loginUser);
			Cookie ck=new Cookie("uid", loginUser.getUserid());
			if(user.isSaveId()) {
				ck.setMaxAge(7*24*60*60);//7일간 유효
			}else {
				ck.setMaxAge(0);//쿠키 삭제
			}
			ck.setPath("/");
			res.addCookie(ck);			
		}
		return "redirect:index";
	}//---------------------------
	
	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
	
	@ExceptionHandler(NotMemberException.class)
	public String exceptionHandler(Exception ex) {
		
		return "common/errorAlert";
	}//--------------------------------
	

}
