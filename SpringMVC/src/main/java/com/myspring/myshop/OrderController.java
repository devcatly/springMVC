package com.myspring.myshop;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myspring.domain.MemberVO;
import com.myspring.domain.OrderVO;
import com.myspring.domain.ProductVO;
import com.myspring.domain.ReceiverVO;
import com.myspring.service.OrderService;
import com.myspring.service.ShopService;
import com.myspring.service.UserService;

@Controller
@RequestMapping("/user")
public class OrderController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private OrderService orderService;

	@Inject
	private ShopService shopService;
	
	@Inject
	private UserService userService;

	@PostMapping("/order")
	public String orderForm(Model m, HttpSession ses, @RequestParam("opnum") int[] opnum,
			@RequestParam("oqty") int[] oqty) {
		logger.info("opnum[0]={}, oqty[0]={}", opnum[0], oqty[0]);
		// 주문한 상품정보를 DB에서 가져와서 세션에 저장하자
		List<ProductVO> orderList = new ArrayList<>();

		int totalBuy = 0, totalBuyPoint = 0;

		if (opnum != null && oqty != null) {
			for (int i = 0; i < opnum.length; i++) {
				int pnum = opnum[i];
				int pqty = oqty[i];
				ProductVO prod = shopService.selectByPnum(pnum);
				// 상품번호로 상품정보 가져오기 .수량(pqty)=>판매자 보유수량=> 주문수량으로 변경
				prod.setPqty(pqty);
				orderList.add(prod);// 주문목록
				totalBuy += prod.getTotalPrice();
				totalBuyPoint += prod.getTotalPoint();
			}
		}
		// 주문자의 마일리지 점수를 가져오자
		MemberVO loginUser = (MemberVO) ses.getAttribute("loginUser");

		MemberVO user = this.userService.findMemberByIdx(loginUser.getIdx());
		int mileage=0;
		if(user!=null) {
			mileage=user.getMileage();
		}
				//loginUser.getMileage();

		ses.setAttribute("orderArr", orderList);
		ses.setAttribute("totalBuy", totalBuy);// 주문총액
		ses.setAttribute("totalBuyPoint", totalBuyPoint);// 주문 총포인트
		ses.setAttribute("mileage", mileage);// 마일리지 점수
		return "/shop/orderSheet";
	}// --------------------------------

	@PostMapping("/orderAdd")
	public String orderInsert(Model m, HttpSession ses, @ModelAttribute("ovo") OrderVO ovo,
			@ModelAttribute("receiver") ReceiverVO receiver) {
		//logger.info("ovo={}", ovo);
		logger.info("receiver={}", receiver);
		// 주문상품 정보==> 세션에서 꺼내야 함
		// 1. 주문한 상품정보 가져오기
		List<ProductVO> orderList=(List<ProductVO>)ses.getAttribute("orderArr");

		// 2. 주문한 상품정보와 수령자 정보를 ovo에 setting=> setter를 이용해서
		ovo.setOrderList(orderList);
		ovo.setReceiver(receiver);
		

		// 3. 결제 방법에 따라서 결제상태값 결정
		//무통장(100) => opayState ='미결제'
		//카드결제(200) => opayState ='결제완료'
		if(ovo.getOpayWay().equals("100")) {
			ovo.setOpayState("미결제");
		}else if(ovo.getOpayWay().equals("200")) {
			ovo.setOpayState("결제완료");
		}
		//3_2. 배송상태 ==>"미배송"
		ovo.setOdeliverState("미배송");

		logger.info("ovo={}", ovo);
		// 4. 주문정보를 DB에 insert한다
		String onum = this.orderService.orderInsert(ovo);

		// 5. 주문번호=> 세션에 저장
		ses.setAttribute("onum", onum);

		// 6. 회원정보를 db에서 다시 가져와서 세션에 저장
		// =>주문하면 mileage(적립금) 변동이 있으므로
		MemberVO user=this.userService.findMemberByIdx(ovo.getIdx_fk());
		if(user!=null) {
			ses.setAttribute("mileage", user.getMileage());
		}
		
		
		// 7. 주문처리가 완료되면 ==> 장바구니 목록에서 주문한 상품정보를 삭제한다.(회원번호, 상품번호)
		if(orderList!=null) {
			for(ProductVO prod:orderList) {
				int pnum=prod.getPnum();
				int idx_fk=ovo.getIdx_fk();
				this.shopService.delCartByOrder(idx_fk, pnum);
				//장바구니에서 주문한 상품 삭제
			}
		}
		
		// 8. 주문 상세 내역 페이지로 이동 (redirect이동해야 함)
		// 새로고침시 이중 주문이 발생할 수 있으므로 forward가 아니라 redirect로 이동

		return "redirect:orderDetail?onum="+onum;
	}//--------------------------------
	
	@GetMapping("/orderDetail")
	public String orderDesc(Model m, HttpSession ses, @RequestParam(defaultValue="") String onum) {
		if(onum.isEmpty()) {
			onum=(String)ses.getAttribute("onum");
			if(onum==null) {
				return "redirect:../index";
			}
		}
		//주문번호로 상세 주문내역 정보 가져오기
		List<OrderVO> orderDesc=this.orderService.getOrderDesc(onum);
		logger.info("orderDesc={}", orderDesc);
		
		m.addAttribute("orderDesc", orderDesc);
		
		return "/shop/orderDesc";
	}

}//////////////////////////////////////////
