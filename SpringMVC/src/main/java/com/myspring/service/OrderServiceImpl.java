package com.myspring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.domain.OrderVO;
import com.myspring.domain.ProductVO;
import com.myspring.mapper.OrderMapper;
import com.myspring.mapper.UserMapper;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Inject
	private OrderMapper orderMapper;
	
	@Inject
	private UserMapper userMapper;

	@Override
	public String orderInsert(OrderVO ovo) {
		// 1. 주문번호 생성 (알파벳대문자3개 +년월일시분초)
		String onum= makeOnum();
		ovo.setOnum(onum);
		//2. 주문개요정보 insert => orderDesc테이블
		int n1=orderMapper.orderDescInsert(ovo);
		System.out.println("n1="+n1);
		
		//3. 주문상품정보 insert => (1 or 여러개)=> orderProduct테이블(pk=> onum+pnum_fk)
		List<ProductVO> orderList=ovo.getOrderList();
		if(orderList!=null) {
			for(ProductVO prod:orderList) {
				prod.setOnum(onum);
				int n2=this.orderMapper.orderProductInsert(prod);
				System.out.println("n2="+n2);
			}
		}
		
		//4. 수령자 정보 insert => receiver테이블
		int n3=this.orderMapper.receiverInsert(ovo);
		System.out.println("n3="+n3);
		
		//5. 사용마일리지(omileage)가 있으면 회원 마일리지 점수에 차감 처리
		if(ovo.getOmileage()>0) {
			userMapper.updateMileageDown(ovo);
		}
		
		//6. 결제방식이 카드 결제라면 => 회원의 마일리지(mileage) 점수에 구매포인트(ototalPoint)를 적립
		if(ovo.getOpayWay().equals("200")) {
			userMapper.updateMileageUp(ovo);
		}
		
		return onum;
	}

	private String makeOnum() {
		char ch1=(char)(Math.random()*26+'A');
		char ch2=(char)(Math.random()*26+'A');
		char ch3=(char)(Math.random()*26+'A');
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=sdf.format(d);
		
		String onum=String.valueOf(ch1)+ch2+ch3+time; //RQU202202091143
		//char + char => int promotion
		//System.out.println(ch1+"/ "+onum);
		return onum;
	}

	@Override
	public List<OrderVO> getOrderDesc(String onum) {
		return orderMapper.getOrderDesc(onum);
	}

	@Override
	public List<OrderVO> getUserOrderList(int idx_fk) {
		return orderMapper.getUserOrderList(idx_fk);
	}

}
