package com.myspring.service;

import java.util.List;

import com.myspring.domain.CategoryVO;
import com.myspring.domain.OrderVO;
import com.myspring.domain.ProductVO;
import com.myspring.domain.SumVO;

public interface AdminService {

	/** 카테고리 목록 가져오기 */
	List<CategoryVO> getCategoryList();
	public int categoryAdd(CategoryVO cvo);
	public int categoryDelete(int cg_num);
	

	/** [관리자 모드]- 상품 정보 등록하기 */
	public int productInsert(ProductVO prod);
	public List<ProductVO> productList();

	// [관리자 모드]=>배송상태, 지불상태를 관리하는 메소드
	void manageOrder(String onum, String colName, String colVal);
	// [관리자 모드]=>월별 주문 목록 가져오기
	List<OrderVO> getOrderListByMonth(String month);

	// [관리자 모드]=>연도별 매출 통계 구하기
	List<SumVO> getSumByYear();
	// [관리자 모드]=>해당 년도 월별 매출통계 구하기
	List<SumVO> getSumByMonth(String month);
	List<ProductVO> getProducts(int cg_num);

}
