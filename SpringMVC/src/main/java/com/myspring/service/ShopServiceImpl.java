package com.myspring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.domain.CartVO;
import com.myspring.domain.ProductVO;
import com.myspring.mapper.CartMapper;
import com.myspring.persistence.ProductDAO;

@Service("shopServiceImpl")
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ProductDAO prodDao;
	
	@Inject
	private CartMapper cartMapper;

	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		
		return prodDao.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(int cg_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO selectByPnum(int pnum) {
		return prodDao.selectByPnum(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		//[1] ��ǰ��ȣ�� ȸ����ȣ�� cart���̺� �ִ� ��ǰ���� ��������
		int count=cartMapper.selectCartByPnum(cartVo);
		System.out.println("count="+count);
		
		int n=0;
		if(count>0) {
			//[2] �ش��ǰ�� �̹� ��ٱ��Ͽ� �ִٸ� ������ �߰�
			n=cartMapper.updateCartOqty(cartVo);
		}else {
			//[3] �ش� ��ǰ�� ��ٱ��Ͽ� ���� ��ǰ�̶�� insert
			n=cartMapper.addCart(cartVo);
		}
		
		
		return n;
	}

	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		//������ ���� �ٸ��� ���� ó���غ���.
		int qty=cartVo.getOqty();
		if(qty==0) {//������ 0�̸� ���� ó��
			return this.delCart(cartVo.getCartNum());
		}else if(qty<0) {//����
			throw new NumberFormatException("������ �������� �ȵ˴ϴ�");
		}else {//������ ���� ���� ó��
			return this.cartMapper.editCart(cartVo);
		}
		
	}

	@Override
	public List<CartVO> selectCartView(int idx_fk) {
		return this.cartMapper.selectCartView(idx_fk);
	}

	@Override
	public int delCart(int cartNum) {

	int n=this.cartMapper.delCart(cartNum);
	System.out.println(n);
	return n;
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCartCountByIdx(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartVO getCartTotal(int midx_fk) {
		return this.cartMapper.getCartTotal(midx_fk);
	}

	@Override
	public void delCartByOrder(int midx_fk, int pnum) {
		Map<String, Integer> map=new HashMap<>();
		map.put("idx_fk", midx_fk);
		map.put("pnum_fk", pnum);		
		this.cartMapper.delCartByOrder(map);
	}

}
