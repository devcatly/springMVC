package com.myspring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myspring.memo.domain.MemoVO;
import com.myspring.memo.persistence.MemoDAO;

@Controller
public class MemoController {
	
	@Autowired
	private MemoDAO memoDao;
	
	
	@RequestMapping("/memo")//디폴트 요청=> GET방식
	public String memoForm(Model m) {
		m.addAttribute("message","Spring 한줄 메모장");
		
		return "memo/input";
		//"/WEB-INF/views/memo/input.jsp"
	}//--------------------------
	//@ModelAttribute ==> html에서 입력한 값들을 VO에 자동으로 넣어준다. (단 조건. input name과 VO의 property명이 같아야)
	@RequestMapping(value="/memoAdd", method = RequestMethod.POST)
	public String memoWrite(Model m, @ModelAttribute("memo") MemoVO memo) {
		System.out.println("작성자: "+memo.getName()+", 메모내용: "+memo.getMsg());
		if(memo.getName()==null||memo.getName().trim().isEmpty()) {
			return "redirect:memo";
			//redirect: 을 접두어로 붙이면 redirect방식으로 이동한다.
		}
		int n=memoDao.createMemo(memo);
		
		String str=(n>0)?"메모 등록 성공":"등록 실패";
		String loc=(n>0)?"memoList":"javascript:history.back()";
		
		m.addAttribute("msg",str);
		m.addAttribute("loc",loc);
		return "memo/msg";
	}//--------------------------
	
	// /memoList요청이 오면 memo/list.jsp 보여주도록 매핑하세요
	
	@RequestMapping(value="/memoList", method=RequestMethod.GET)
	public String memoList(Model m, @RequestParam(defaultValue="1") int cpage) {
		//현재 보여줄 페이지 파라미터 값 => cpage
		//총 메모글 수 가져오기
		int totalCount = memoDao.getTotalCount();
		
		int pageSize=5;//한 페이지당 보여줄 목록 개수
		
		int pageCount =(totalCount-1)/pageSize +1;
		
		if(cpage<1) {
			cpage=1;
		}
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		//db에서 끊어올 변수 연산
		int end = cpage*pageSize;
		int start = end -(pageSize-1);
		
		List<MemoVO> memoArr=memoDao.listMemo(start, end);
		m.addAttribute("memoArr", memoArr);
		m.addAttribute("totalCount", totalCount);
		m.addAttribute("pageCount", pageCount);
		m.addAttribute("cpage", cpage);
		
		return "memo/list";
		//"/WEB-INF/views/memo/list.jsp"
	}
	
	//글번호 파라미터: idx 
	//@RequestParam  => req.getParameter("파라미터명")과 동일한 역할을 함
	
	@RequestMapping("/memoDel")
	public String memoDelete(Model m, @RequestParam(defaultValue="0") int idx) {
		System.out.println("idx="+idx);
		if(idx==0) {
			return "redirect:memoList";
		}
		int n=memoDao.deleteMemo(idx);
		String str=(n>0)?"메모 삭제 성공":"삭제 실패";
		String loc="memoList";
		
		m.addAttribute("msg", str);
		m.addAttribute("loc",loc);
		
		return "memo/msg";
	}//---------------------------
	
	@RequestMapping("/memoEdit")
	public String memoEditForm(Model m, @RequestParam(defaultValue="0") int idx) {
		System.out.println("idx="+idx);
		if(idx==0) {
			return "redirect:memoList";
		}
		MemoVO memo = memoDao.selectMemoByIdx(idx);
		m.addAttribute("memo", memo);
		
		return "memo/edit";
	}//---------------------------
	
	@RequestMapping("/memoEditEnd")
	public String memoEditEnd(Model m, @ModelAttribute("memo") MemoVO memo) {
		if(memo.getIdx()==0||memo.getName()==null||memo.getName().trim().isEmpty()) {
			return "redirect:memoList";
		}
		int n = memoDao.updateMemo(memo);
		
		String str = (n > 0) ? "수정 성공" : "수정 실패";
		String loc = (n > 0) ? "memoList" : "javascript:history.back()";
		
		m.addAttribute("msg",str);
		m.addAttribute("loc",loc);
		return "memo/msg";
	}//--------------------------------------


	
	
}////////////////////////////////


