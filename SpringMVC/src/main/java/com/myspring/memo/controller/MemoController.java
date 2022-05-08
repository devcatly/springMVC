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
	
	
	@RequestMapping("/memo")//����Ʈ ��û=> GET���
	public String memoForm(Model m) {
		m.addAttribute("message","Spring ���� �޸���");
		
		return "memo/input";
		//"/WEB-INF/views/memo/input.jsp"
	}//--------------------------
	//@ModelAttribute ==> html���� �Է��� ������ VO�� �ڵ����� �־��ش�. (�� ����. input name�� VO�� property���� ���ƾ�)
	@RequestMapping(value="/memoAdd", method = RequestMethod.POST)
	public String memoWrite(Model m, @ModelAttribute("memo") MemoVO memo) {
		System.out.println("�ۼ���: "+memo.getName()+", �޸𳻿�: "+memo.getMsg());
		if(memo.getName()==null||memo.getName().trim().isEmpty()) {
			return "redirect:memo";
			//redirect: �� ���ξ�� ���̸� redirect������� �̵��Ѵ�.
		}
		int n=memoDao.createMemo(memo);
		
		String str=(n>0)?"�޸� ��� ����":"��� ����";
		String loc=(n>0)?"memoList":"javascript:history.back()";
		
		m.addAttribute("msg",str);
		m.addAttribute("loc",loc);
		return "memo/msg";
	}//--------------------------
	
	// /memoList��û�� ���� memo/list.jsp �����ֵ��� �����ϼ���
	
	@RequestMapping(value="/memoList", method=RequestMethod.GET)
	public String memoList(Model m, @RequestParam(defaultValue="1") int cpage) {
		//���� ������ ������ �Ķ���� �� => cpage
		//�� �޸�� �� ��������
		int totalCount = memoDao.getTotalCount();
		
		int pageSize=5;//�� �������� ������ ��� ����
		
		int pageCount =(totalCount-1)/pageSize +1;
		
		if(cpage<1) {
			cpage=1;
		}
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		//db���� ����� ���� ����
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
	
	//�۹�ȣ �Ķ����: idx 
	//@RequestParam  => req.getParameter("�Ķ���͸�")�� ������ ������ ��
	
	@RequestMapping("/memoDel")
	public String memoDelete(Model m, @RequestParam(defaultValue="0") int idx) {
		System.out.println("idx="+idx);
		if(idx==0) {
			return "redirect:memoList";
		}
		int n=memoDao.deleteMemo(idx);
		String str=(n>0)?"�޸� ���� ����":"���� ����";
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
		
		String str = (n > 0) ? "���� ����" : "���� ����";
		String loc = (n > 0) ? "memoList" : "javascript:history.back()";
		
		m.addAttribute("msg",str);
		m.addAttribute("loc",loc);
		return "memo/msg";
	}//--------------------------------------


	
	
}////////////////////////////////


