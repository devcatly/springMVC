package com.myspring.persistence;

import java.util.List;

import com.myspring.domain.CategoryVO;

public interface CategoryDAO {
	
	public List<CategoryVO> categoryAll();
	public int insertCategory(CategoryVO cvo);
	public int deleteCategory(int cg_num);

}
