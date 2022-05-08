package com.myspring.mapper;

import java.util.List;

import com.myspring.domain.OrderVO;
import com.myspring.domain.ProductVO;

public interface OrderMapper {
	// 주문 개요정보를 insert하는 메소드
	int orderDescInsert(OrderVO ovo);

	// 주문 상품정보 insert
	int orderProductInsert(ProductVO prod);

	// 수령자 정보 insert
	int receiverInsert(OrderVO ovo);

	List<OrderVO> getOrderDesc(String onum);

	List<OrderVO> getUserOrderList(int midx_fk);

}
