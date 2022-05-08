package com.myspring.myshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
	
	@GetMapping("/bookInfo")
	public String bookInfo(@RequestParam("isbn") String isbn) {
		
		return "/book/bookDetail";
	}

}
