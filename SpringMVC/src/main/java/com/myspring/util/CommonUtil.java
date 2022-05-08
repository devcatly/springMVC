package com.myspring.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

//@Controller, @Service, @Respository, ...==>@Component을 상속받고 있음
@Component
public class CommonUtil {
	
	public String addMsgLoc(Model m, String msg, String loc) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", loc);
		return "/memo/msg";
	}
	
	public String addMsgBack(Model m, String msg) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", "javascript:history.back()");
		return "/memo/msg";
	}

}
