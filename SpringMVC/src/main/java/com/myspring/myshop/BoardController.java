package com.myspring.myshop;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myspring.domain.BoardVO;
import com.myspring.domain.PagingVO;
import com.myspring.service.BoardService;
import com.myspring.util.CommonUtil;

@Controller
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	@Inject
	private CommonUtil util;
	
	private Logger logger=LoggerFactory.getLogger(BoardController.class);
	
	@GetMapping("/board")
	public String boardForm() {
		
		return "/board/boardWrite";
	}
	//�۾��� ó��|�ۼ��� ó��|�亯�۾��� ������ mode������ �ĺ��Ͽ� ó�� ����
	@PostMapping("/board")
	public String boardInsert(Model m,
			HttpServletRequest req,
			@RequestParam("mfilename") MultipartFile mfilename,
			@ModelAttribute("board") BoardVO board) {
		logger.info("board={}, mode={}", board, board.getMode());
		//1. ���ε� ���丮�� ������ ���ϱ�
		ServletContext app=req.getServletContext();
		String UP_DIR=app.getRealPath("/resources/Upload");
		logger.info("UP_DIR={}", UP_DIR);
		
		File dir=new File(UP_DIR);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//������ ÷���ߴٸ�
		if(!mfilename.isEmpty()) {
			//1)���� ÷�����ϸ�, ����ũ�⸦ �˾Ƴ���
			String originFile=mfilename.getOriginalFilename();//�������ϸ�
			long fsize=mfilename.getSize();//����ũ��
			logger.info(originFile+", "+fsize);
			//������ ���ϸ��� ��� ����� �����ϱ� ����
			//������ ���ϸ��� "�����ѹ��ڿ�_�������̸�" ���� ������
			UUID uuid=UUID.randomUUID();
			String filename=uuid.toString()+"_"+originFile;
			logger.info("filename={}", filename);
			
			board.setOriginFilename(originFile);
			board.setFilename(filename);
			board.setFilesize(fsize);
			//��尡 edit�̰� ������ ÷���ߴ� ������ �ִٸ� ���� ������ ���� ó������
			if(board.getMode().equals("edit") && board.getOld_filename()!=null) {
				File delF=new File(UP_DIR, board.getOld_filename());
				if(delF.exists()) {
					boolean b=delF.delete();
					logger.info("old���� ���� ����: {}", b);
				}
				
			}
			
			
			//2) ���� ���ε� ó��
			try {
				mfilename.transferTo(new File(UP_DIR,filename));
				logger.info("���ε� ����");
			}catch(Exception e) {}
			
		}//if-------
		
		int n=0;
		String str="";
		if(board.getMode().equals("write")) {
			//for(int i=0;i<30;i++)
			n=this.boardService.insertBoard(board);
			
			str=(n>0)?"�۾��� ����":"�۾��� ����";
		}else if(board.getMode().equals("edit")) {
			n=this.boardService.editBoard(board);
			str=(n>0)?"�ۼ��� ����":"�ۼ��� ����";
		}else if(board.getMode().equals("rewrite")) {
			n=this.boardService.rewriteBoard(board);
			str=(n>0)?"��۾��� ����":"��۾��� ����";
		}
		
		//m.addAttribute("msg","test");
		String loc=(n>0)? "boardList":"javascript:history.back()";
		return util.addMsgLoc(m, str, loc);
	}//---------------------------------
	
	@GetMapping("/boardList_old") //����¡ ó�� �ϱ� ��
	public String boardList(Model m) {
		//1. �� �Խñۼ� ��������
		int totalCount = this.boardService.getTotalCount();
		int start=1, end=5;
		//2. �Խø�� ��������
		List<BoardVO> boardArr=this.boardService.listBoard(start, end);
		
		m.addAttribute("boardArr", boardArr);
		m.addAttribute("totalCount",totalCount);
		return "/board/boardList";
	}//---------------------------
	
	@GetMapping("/boardList")
	public String boardListPaging(Model m, 
			HttpServletRequest req,
			@ModelAttribute("page") PagingVO page,
			@RequestHeader("User-Agent") String userAgent) {
		logger.info("userAgent={}", userAgent);
		//1. �� �Խñ� �� ��������(�˻��� �Խñۼ��� ����)
		int totalCount = this.boardService.getFindTotalCount(page);
		logger.info("totalCount={}", totalCount);
		
		page.setTotalCount(totalCount);
		page.setPageSize(5);//�� ������ �� ������ ��� ����
		page.setPagingBlock(5);//����¡ �� ���� ��: 5��
		/////////////////////////
		page.init(); //����¡ ���� ������ �����ϴ� �޼ҵ� ȣ��
		/////////////////////////
		logger.info("init()������ page={}", page);
		
		//2. �Խñ� ��� ��������
		List<BoardVO> boardArr=this.boardService.listBoard(page);
		
		//3. ������ �׺���̼� ���ڿ� ��������
		String myctx=req.getContextPath();
		String loc="boardList";
		String pageNavi=page.getPageNavi(myctx, loc, userAgent);
		
		m.addAttribute("boardArr", boardArr);
		m.addAttribute("totalCount", totalCount);
		m.addAttribute("page", page);
		m.addAttribute("pageNavi", pageNavi);
		return "/board/boardList3";
	}
	
	
	/*boardView?idx=1 ==> Query String parameter���==> @RequestParam���� ������ �ȴ�.
					boardView/1 => Path���ٹ�� ==> @PathVariable �޾ƾ� ��
	 * */
	@GetMapping("/boardView/{idx}")
	public String boardView(Model m, @PathVariable(name="idx") String idx) {
		logger.info("idx={}", idx);
		//1. ��ȸ�� ����
		this.boardService.updateReadnum(idx);
		//2. �ش� �� ��������
		BoardVO board=this.boardService.viewBoard(idx);
		
		if(board==null) {
			return util.addMsgBack(m, "�ش� ���� �����ϴ�");
		}	
		
		m.addAttribute("board",board);
		
		return "/board/boardView";
	}
	@PostMapping("/boardEdit")
	public String boardEditForm(Model m, @RequestParam(defaultValue="") String idx,
			@RequestParam(defaultValue="") String pwd) {
			if(idx.isEmpty()|| pwd.isEmpty()) {
				return "redirect:boardList";
			}
			BoardVO board = this.boardService.viewBoard(idx);
			if(board==null) {
				return util.addMsgBack(m, "�ش� ���� �����ϴ�");
			}			
			if(! board.getPwd().equals(pwd)) {				
				return util.addMsgBack(m, "��й�ȣ�� ��ġ���� �ʾƿ�");
			}
			m.addAttribute("board",board);
		
		return "/board/boardEdit";
	}
	@PostMapping("/boardDel")
	public String boardDelete(Model m, 
			HttpServletRequest req,
			@RequestParam(defaultValue="") String idx,
			@RequestParam(defaultValue="") String pwd) {
		if(idx.isEmpty()||pwd.isEmpty()) {
			return "redirect:boardList";
		}
		BoardVO board=this.boardService.viewBoard(idx);
		if(board==null) {
			return util.addMsgBack(m,"�ش� ���� �������� �ʾƿ�");
		}
		if(!board.getPwd().equals(pwd)) {
			
			return util.addMsgBack(m, "��й�ȣ�� ��ġ���� �ʾƿ�");
		}
		//÷�������� �ִٸ� ==> �������� �ش� ÷������ ���� ó��
		ServletContext app=req.getServletContext();
		String UP_DIR=app.getRealPath("/resources/Upload");
		logger.info(UP_DIR);
		
		if(board.getFilename()!=null) {
			File df=new File(UP_DIR, board.getFilename());
			if(df.exists()) {
				boolean b=df.delete();
				logger.info("���� ����: {}", b);
			}
		}
		
		//����� ��ġ�ϸ� ���� ó��
		String msg="";
		String loc="";
		int n=this.boardService.deleteBoard(idx);
		msg=(n>0)?"�� ���� ����":"�ۻ��� ����";
		loc=(n>0)?"boardList":"javascript:history.back()";
		return util.addMsgLoc(m, msg, loc);
	}
	/*[������ �Խ���-�亯�۾��� ����]*/
	@PostMapping("/rewrite")
	public String rewriteForm(Model m, @ModelAttribute("board") BoardVO board) {
		
		logger.info("board={}", board);
		
		m.addAttribute("board", board);//�۹�ȣ, ����
		return "/board/boardRewrite";
	}
	

}/////////////////////////////////////////////










