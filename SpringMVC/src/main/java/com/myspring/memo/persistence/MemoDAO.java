package com.myspring.memo.persistence;

import java.util.List;

import com.myspring.memo.domain.MemoVO;

public interface MemoDAO {
	int createMemo(MemoVO memo);
	List<MemoVO> listMemo(int start, int end);
	int deleteMemo(int idx);
	
	MemoVO selectMemoByIdx(int idx);
	int updateMemo(MemoVO memo);
	
	int getTotalCount();

}
