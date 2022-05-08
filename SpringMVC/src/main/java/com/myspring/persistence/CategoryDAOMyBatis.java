package com.myspring.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.myspring.domain.CategoryVO;

@Repository
public class CategoryDAOMyBatis implements CategoryDAO {
	
	private final String NS="com.myspring.persistence.CategoryMapper";
	//datasource-context.xml에 빈 등록 되어 있음
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate ses;

	@Override
	public List<CategoryVO> categoryAll() {

		return ses.selectList(NS+".categoryAll");
	}

	@Override
	public int insertCategory(CategoryVO cvo) {
		return ses.insert(NS+".insertCategory", cvo);
	}

	@Override
	public int deleteCategory(int cg_num) {
		return ses.delete(NS+".deleteCategory", cg_num);
	}

}
