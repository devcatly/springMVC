package com.myspring.service;

import java.util.List;

import com.myspring.domain.CategoryVO;
import com.myspring.domain.OrderVO;
import com.myspring.domain.ProductVO;
import com.myspring.domain.SumVO;

public interface AdminService {

	/** ī�װ� ��� �������� */
	List<CategoryVO> getCategoryList();
	public int categoryAdd(CategoryVO cvo);
	public int categoryDelete(int cg_num);
	

	/** [������ ���]- ��ǰ ���� ����ϱ� */
	public int productInsert(ProductVO prod);
	public List<ProductVO> productList();

	// [������ ���]=>��ۻ���, ���һ��¸� �����ϴ� �޼ҵ�
	void manageOrder(String onum, String colName, String colVal);
	// [������ ���]=>���� �ֹ� ��� ��������
	List<OrderVO> getOrderListByMonth(String month);

	// [������ ���]=>������ ���� ��� ���ϱ�
	List<SumVO> getSumByYear();
	// [������ ���]=>�ش� �⵵ ���� ������� ���ϱ�
	List<SumVO> getSumByMonth(String month);
	List<ProductVO> getProducts(int cg_num);

}
