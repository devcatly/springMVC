package com.myspring.service;

import java.util.List;

import com.myspring.domain.BoardVO;
import com.myspring.domain.PagingVO;

public interface BoardService {
	public int insertBoard(BoardVO board);
	public int getTotalCount();
	
	public List<BoardVO> listBoard(int start, int end);
	
	public List<BoardVO> listBoard(PagingVO paging);
	
	public int updateReadnum(String idx);
	public BoardVO viewBoard(String idx);
	public String selectPwd(String idx);
	public int deleteBoard(String idx);
	public int editBoard(BoardVO board);
	//�亯�� �Խ��� ����--------
	BoardVO selectRefLevSunbun(String idx);
	int rewriteBoard(BoardVO board);
	int updateSunbun(BoardVO parent);

	//�� �˻� ����------------------
	public List<BoardVO> findListBoard(PagingVO paging);	
	public int getFindTotalCount(PagingVO paging);
	
	int getFindTotalCount(String findType, String findString);
	List<BoardVO> findListBoard(int start, int end, String findType,
							String findString);
	

}
