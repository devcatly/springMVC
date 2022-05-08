package com.myspring.myshop;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myspring.domain.ProductVO;
import com.myspring.service.ShopService;

@Controller
public class ProductController {
	
	private static final Logger logger
		=LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	private ShopService shopService;
	
	
	//pspec(HIT,NEW, BEST) 별로 상품목록 가져오기
	@GetMapping("/prodPspec")
	public String productByPspec(Model m, 
			@RequestParam(name="pspec", defaultValue="HIT") String pspec) {
		logger.info("pspec={}", pspec);
		
		List<ProductVO> plist=shopService.selectByPspec(pspec);
		
		logger.info("plist={}", plist);
		
		m.addAttribute("plist",plist);
		m.addAttribute("pspec",pspec);
		
		return "shop/mallHit";
		//"/WEB-INF/views/shop/mallHit.jsp"
	}
	
	@GetMapping("/prodDetail")
	public String productDetail(Model m, @RequestParam(defaultValue="0") int pnum) {
		logger.info("pnum={}", pnum);
		if(pnum==0) {
			//유효성 체크
			return "redirect:index";
		}
		//상품번호로 상품 가져오기
		ProductVO prod=shopService.selectByPnum(pnum);
		logger.info("prod={}", prod);
		
		//모델에 저장하기 => key값 =>"item"
		m.addAttribute("item", prod);
		
		return "shop/prodDetail";
	}
	

}
