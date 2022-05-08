package com.myspring.myshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	private static final Logger logger 
	= LoggerFactory.getLogger(IndexController.class);
	//로그 남길 때 사용
	
	@RequestMapping("/index")
	public void index() {
		//뷰네임을 반환하지 않으면(void) @RequestMapping에 등록한  /index=> 뷰이름이 된다.
		
		//"/WEB-INF/views/index.jsp"
	}
	
	@RequestMapping("/top")
	public void showTop() {
		
	}
	
	@RequestMapping("/foot")
	public void showFoot() {
		
	}
	@RequestMapping("/carousel")
	public void showCarousel() {
		
	}

}
