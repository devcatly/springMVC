package com.myspring.mapper;

import java.util.List;
import java.util.Map;

import com.myspring.domain.CartVO;
//영속성 계층에 인터페이스(XXXMapper)만 선언해서 구성해 놓으면
// 인터페이스를 상속받는 클래스를 스프링이 자동으로 만들어준다.==> Proxy객체
// 그러려면 설정을 해야 한다. datasource-context.xml에 설정해야 한다.
public interface CartMapper {
	
	int selectCartByPnum(CartVO cartVo);

	int updateCartOqty(CartVO cartVo);

	int addCart(CartVO cartVo);

	List<CartVO> selectCartView(int midx);

	CartVO getCartTotal(int midx_fk);

	int delCart(int cartNum);

	int editCart(CartVO cartVo);

	void delCartByOrder(Map<String, Integer> map);

}
