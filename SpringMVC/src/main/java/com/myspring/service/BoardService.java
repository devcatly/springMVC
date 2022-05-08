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
	//답변형 게시판 관련--------
	BoardVO selectRefLevSunbun(String idx);
	int rewriteBoard(BoardVO board);
	int updateSunbun(BoardVO parent);

	//글 검색 관련------------------
	public List<BoardVO> findListBoard(PagingVO paging);	
	public int getFindTotalCount(PagingVO paging);
	
	int getFindTotalCount(String findType, String findString);
	List<BoardVO> findListBoard(int start, int end, String findType,
							String findString);
	

}
