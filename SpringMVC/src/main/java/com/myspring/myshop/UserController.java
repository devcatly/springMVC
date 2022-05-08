package com.myspring.myshop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.myspring.domain.MemberVO;
import com.myspring.domain.OrderVO;
import com.myspring.service.OrderService;

@Controller
public class UserController {
	
	@Inject
	private OrderService orderService;
	
	@GetMapping("/user/mypage")
	public String showMyPage(HttpSession ses) {
		MemberVO loginUser=(MemberVO) ses.getAttribute("loginUser");
		
		List<OrderVO> myOrderList =this.orderService.getUserOrderList(loginUser.getIdx());
		
		ses.setAttribute("myOrderList", myOrderList);
		return "/shop/myPage";
	}

}






