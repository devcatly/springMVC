package com.myspring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.domain.CategoryVO;
import com.myspring.domain.OrderVO;
import com.myspring.domain.ProductVO;
import com.myspring.domain.SumVO;
import com.myspring.persistence.CategoryDAO;
import com.myspring.persistence.ProductDAO;
//서비스 계층=> 비즈니스 계층(biz logic이 들어간다)
@Service(value = "adminServiceImpl")
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private CategoryDAO cateDao;
	
	@Autowired
	private ProductDAO productDao;

	@Override
	public List<CategoryVO> getCategoryList() {		
		return cateDao.categoryAll();
	}

	@Override
	public int categoryAdd(CategoryVO cvo) {
		return cateDao.insertCategory(cvo);
	}

	@Override
	public int categoryDelete(int cg_num) {
		return cateDao.deleteCategory(cg_num);
	}

	@Override
	public int productInsert(ProductVO prod) {
		return this.productDao.productInsert(prod);
	}

	@Override
	public List<ProductVO> productList() {
		return this.productDao.productList();
	}

	@Override
	public void manageOrder(String onum, String colName, String colVal) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderVO> getOrderListByMonth(String month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SumVO> getSumByYear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SumVO> getSumByMonth(String month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getProducts(int cg_num) {
		// TODO Auto-generated method stub
		return this.productDao.getProducts(cg_num);
	}

}
