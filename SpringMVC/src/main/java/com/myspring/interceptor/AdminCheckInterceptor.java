package com.myspring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.myspring.domain.MemberVO;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession ses=req.getSession();
		MemberVO user=(MemberVO)ses.getAttribute("loginUser");
		if(user!=null) {
			if(user.getUserid().equals("admin")) return true;
			//�����ڰ� �´ٸ� true��ȯ
			else {
				String loc=req.getContextPath()+"/index";
				req.setAttribute("msg", "�����ڸ� �̿� �����մϴ�");
				req.setAttribute("loc", loc);
				
				RequestDispatcher disp=req.getRequestDispatcher("/WEB-INF/views/memo/msg.jsp");
				disp.forward(req, response);
				return false;
			}
		}
		
		return false;
	}
	
	

}