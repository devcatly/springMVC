package com.myspring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.domain.BoardVO;
import com.myspring.domain.PagingVO;
import com.myspring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardMapper bMapper;

	@Override
	public int insertBoard(BoardVO board) {

		return bMapper.insertBoard(board);
	}

	@Override
	public int getTotalCount() {
		return bMapper.getTotalCount();
	}

	@Override
	public List<BoardVO> listBoard(int start, int end) {
		return bMapper.listBoard(null);
	}

	@Override
	public List<BoardVO> listBoard(PagingVO paging) {
		return this.bMapper.listBoard(paging);
	}

	@Override
	public int updateReadnum(String idx) {
		return this.bMapper.updateReadnum(idx);
	}

	@Override
	public BoardVO viewBoard(String idx) {
		return this.bMapper.viewBoard(idx);
	}

	@Override
	public String selectPwd(String idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBoard(String idx) {
		return this.bMapper.deleteBoard(idx);
	}

	@Override
	public int editBoard(BoardVO board) {
		return this.bMapper.editBoard(board);
	}

	@Override
	public BoardVO selectRefLevSunbun(String idx) {
		
		return this.bMapper.selectRefLevSunbun(idx);
	}

	@Override
	public int rewriteBoard(BoardVO board) {
		// [1] 부모글(원글)의 글번호(idx)로 부모글의 refer(글그룹번호), lev, sunbun
		// 을 가져온다 ==> select문
		BoardVO parent = this.selectRefLevSunbun(board.getIdx()+"");
		System.out.println(parent);
		// [2] 기존에 달린 답변글이 있다면 내 답변글을 insert하기 전에
		// 기존 답변글들의 sunbun을 하나씩 증가시키자
		int n = this.bMapper.updateSunbun(parent);

		// [3] 내가쓴 답변글을 insert한다. ===> insert문
		// board ==> 내가쓴 답변글
		// parent==> 부모글의 refer,lev,sunbun
		board.setRefer(parent.getRefer());
		//같은 글 그룹번호
		board.setLev(parent.getLev()+1); //부모lev +1
		board.setSunbun(parent.getSunbun()+1);//부모sunbun +1
		return this.bMapper.rewriteBoard(board);
	}

	@Override
	public int updateSunbun(BoardVO parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> findListBoard(PagingVO paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFindTotalCount(PagingVO paging) {		
		return this.bMapper.getFindTotalCount(paging);
	}

	@Override
	public int getFindTotalCount(String findType, String findString) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> findListBoard(int start, int end, String findType, String findString) {
		// TODO Auto-generated method stub
		return null;
	}

}
