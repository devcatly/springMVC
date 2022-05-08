package com.myspring.memo.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.myspring.memo.domain.MemoVO;
//@Repository : 영속성 계층에는 @Repository을 붙인다.
@Repository(value = "memoDAOMyBatis")
public class MemoDAOMyBatis implements MemoDAO {

	private final String NS="com.myspring.memo.persistence.MemoMapper";
	
	@Resource(name="sqlSessionTemplate") //by Name으로 주입함. 리소스 이름이 sqlSessionTemplate인 빈을 찾아서 주입한다
	private SqlSessionTemplate session;
	//spring mybatis는 auto commit
	@Override
	public int createMemo(MemoVO memo) {
		int n=session.insert(NS+".createMemo", memo);
		return n;
	}

	@Override
	public List<MemoVO> listMemo(int start, int end) {
		
		Map<String, String> map=new HashMap<>();
		map.put("start", String.valueOf(start));
		map.put("end", String.valueOf(end));

		return session.selectList(NS+".listMemo", map);
	}

	@Override
	public int deleteMemo(int idx) {

		return session.delete(NS+".deleteMemo", idx);
	}

	@Override
	public int getTotalCount() {
		return session.selectOne(NS+".getTotalCount");
	}
	@Override
	public MemoVO selectMemoByIdx(int idx) {
		return session.selectOne(NS+".selectMemoByIdx", idx);
	}

	@Override
	public int updateMemo(MemoVO memo) {
		return session.update(NS+".updateMemo", memo);
	}

}
