package com.myspring.persistence;

import java.util.List;

import com.myspring.domain.ProductVO;

public interface ProductDAO {
	
	public int productInsert(ProductVO item);
	
	public List<ProductVO> productList();
	
	List<ProductVO> selectByPspec(String pspec);

	ProductVO selectByPnum(Integer pnum);

	public List<ProductVO> getProducts(int cg_num);

}
