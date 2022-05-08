package com.myspring.myshop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myspring.domain.CartVO;
import com.myspring.domain.MemberVO;
import com.myspring.service.ShopService;

@Controller
@RequestMapping("/user")
public class CartController {
	
	private static final Logger logger=LoggerFactory.getLogger(CartController.class);
	
	@Inject
	private ShopService shopService;
	
	@PostMapping("/cartAdd")
	public String addCart(
			HttpSession session,
			@RequestParam(defaultValue="0") int pnum,
			@RequestParam(defaultValue="0") int oqty) {
		logger.info("pnum={}, oqty={}", pnum, oqty);
		//로그인한 회원을 세션에서 꺼내오자.
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		
		
		CartVO cartVo=new CartVO();
		cartVo.setPnum_fk(pnum);
		cartVo.setOqty(oqty);
		cartVo.setIdx_fk(idx_fk);
		
		shopService.addCart(cartVo);
		//장바구니에 상품 추가
		
		
		//장바구니 목록 가져오기
		//List<CartVO> cartArr= shopService.selectCartView(idx_fk);
		//session.setAttribute("cart", cartArr);
		//여기서 forward이동하면 브라우저 새로고침시 계속 상품이 추가된다.
		//이 경우 forward이동하면 안된다.
		//return "shop/cartList";
				//"WEB-INF/views/shop/cartList.jsp"
		
		return "redirect:cartList";//컨트롤러와 매핑된 이름
	}
	
	@GetMapping("/cartList")
	public String showCart(Model m, HttpSession session) {
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		
		List<CartVO> cartArr=shopService.selectCartView(idx_fk);
		//특정 회원의 장바구니 총액 가져오기
		CartVO cartVO=shopService.getCartTotal(idx_fk);
		
		m.addAttribute("cart", cartArr);
		m.addAttribute("cartTotal", cartVO);
		return "shop/cartList";
	}
	
	
	@GetMapping("/cartDel")
	public String cartDelete(Model m, @RequestParam(defaultValue="0")int cartNum) {
		if(cartNum==0) {
			return "redirect:cartList";
		}
		int n=shopService.delCart(cartNum);
		return "redirect:cartList";
	}
	
	@PostMapping("/cartEdit")
	public String cartEdit(@ModelAttribute("cvo") CartVO cvo) {
		logger.info("cvo={}", cvo);//cartNum, oqty넘어옴
		
		shopService.editCart(cvo);
		
		return "redirect:cartList";
	}
	
	/**수량을 음수로 입력했을 때 예외 처리*/
	@ExceptionHandler(NumberFormatException.class)
	public String exceptionHandler(Exception ex) {
		return "common/errorAlert";
	}
	

}




