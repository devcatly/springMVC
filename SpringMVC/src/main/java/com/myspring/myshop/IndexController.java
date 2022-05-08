package com.myspring.myshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	private static final Logger logger 
	= LoggerFactory.getLogger(IndexController.class);
	//�α� ���� �� ���
	
	@RequestMapping("/index")
	public void index() {
		//������� ��ȯ���� ������(void) @RequestMapping�� �����  /index=> ���̸��� �ȴ�.
		
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
