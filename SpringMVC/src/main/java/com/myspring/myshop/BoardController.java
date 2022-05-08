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
	//글쓰기 처리|글수정 처리|답변글쓰기 로직을 mode값으로 식별하여 처리 예정
	@PostMapping("/board")
	public String boardInsert(Model m,
			HttpServletRequest req,
			@RequestParam("mfilename") MultipartFile mfilename,
			@ModelAttribute("board") BoardVO board) {
		logger.info("board={}, mode={}", board, board.getMode());
		//1. 업로드 디렉토리의 절대경로 구하기
		ServletContext app=req.getServletContext();
		String UP_DIR=app.getRealPath("/resources/Upload");
		logger.info("UP_DIR={}", UP_DIR);
		
		File dir=new File(UP_DIR);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//파일을 첨부했다면
		if(!mfilename.isEmpty()) {
			//1)먼저 첨부파일명, 파일크기를 알아내자
			String originFile=mfilename.getOriginalFilename();//원본파일명
			long fsize=mfilename.getSize();//파일크기
			logger.info(originFile+", "+fsize);
			//동일한 파일명일 경우 덮어쓰기 방지하기 위해
			//물리적 파일명을 "랜덤한문자열_원본파이명" 으로 만들자
			UUID uuid=UUID.randomUUID();
			String filename=uuid.toString()+"_"+originFile;
			logger.info("filename={}", filename);
			
			board.setOriginFilename(originFile);
			board.setFilename(filename);
			board.setFilesize(fsize);
			//모드가 edit이고 예전에 첨부했던 파일이 있다면 예전 파일은 삭제 처리하자
			if(board.getMode().equals("edit") && board.getOld_filename()!=null) {
				File delF=new File(UP_DIR, board.getOld_filename());
				if(delF.exists()) {
					boolean b=delF.delete();
					logger.info("old파일 삭제 여부: {}", b);
				}
				
			}
			
			
			//2) 파일 업로드 처리
			try {
				mfilename.transferTo(new File(UP_DIR,filename));
				logger.info("업로드 성공");
			}catch(Exception e) {}
			
		}//if-------
		
		int n=0;
		String str="";
		if(board.getMode().equals("write")) {
			//for(int i=0;i<30;i++)
			n=this.boardService.insertBoard(board);
			
			str=(n>0)?"글쓰기 성공":"글쓰기 실패";
		}else if(board.getMode().equals("edit")) {
			n=this.boardService.editBoard(board);
			str=(n>0)?"글수정 성공":"글수정 실패";
		}else if(board.getMode().equals("rewrite")) {
			n=this.boardService.rewriteBoard(board);
			str=(n>0)?"답글쓰기 성공":"답글쓰기 실패";
		}
		
		//m.addAttribute("msg","test");
		String loc=(n>0)? "boardList":"javascript:history.back()";
		return util.addMsgLoc(m, str, loc);
	}//---------------------------------
	
	@GetMapping("/boardList_old") //페이징 처리 하기 전
	public String boardList(Model m) {
		//1. 총 게시글수 가져오기
		int totalCount = this.boardService.getTotalCount();
		int start=1, end=5;
		//2. 게시목록 가져오기
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
		//1. 총 게시글 수 가져오기(검색한 게시글수도 포함)
		int totalCount = this.boardService.getFindTotalCount(page);
		logger.info("totalCount={}", totalCount);
		
		page.setTotalCount(totalCount);
		page.setPageSize(5);//한 페이지 당 보여줄 목록 개수
		page.setPagingBlock(5);//페이징 블럭 단위 값: 5개
		/////////////////////////
		page.init(); //페이징 관련 연산을 수행하는 메소드 호출
		/////////////////////////
		logger.info("init()연산후 page={}", page);
		
		//2. 게시글 목록 가져오기
		List<BoardVO> boardArr=this.boardService.listBoard(page);
		
		//3. 페이지 네비게이션 문자열 가져오기
		String myctx=req.getContextPath();
		String loc="boardList";
		String pageNavi=page.getPageNavi(myctx, loc, userAgent);
		
		m.addAttribute("boardArr", boardArr);
		m.addAttribute("totalCount", totalCount);
		m.addAttribute("page", page);
		m.addAttribute("pageNavi", pageNavi);
		return "/board/boardList3";
	}
	
	
	/*boardView?idx=1 ==> Query String parameter방식==> @RequestParam으로 받으면 된다.
					boardView/1 => Path접근방식 ==> @PathVariable 받아야 함
	 * */
	@GetMapping("/boardView/{idx}")
	public String boardView(Model m, @PathVariable(name="idx") String idx) {
		logger.info("idx={}", idx);
		//1. 조회수 증가
		this.boardService.updateReadnum(idx);
		//2. 해당 글 가져오기
		BoardVO board=this.boardService.viewBoard(idx);
		
		if(board==null) {
			return util.addMsgBack(m, "해당 글은 없습니다");
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
				return util.addMsgBack(m, "해당 글은 없습니다");
			}			
			if(! board.getPwd().equals(pwd)) {				
				return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
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
			return util.addMsgBack(m,"해당 글이 존재하지 않아요");
		}
		if(!board.getPwd().equals(pwd)) {
			
			return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
		}
		//첨부파일이 있다면 ==> 서버에서 해당 첨부파일 삭제 처리
		ServletContext app=req.getServletContext();
		String UP_DIR=app.getRealPath("/resources/Upload");
		logger.info(UP_DIR);
		
		if(board.getFilename()!=null) {
			File df=new File(UP_DIR, board.getFilename());
			if(df.exists()) {
				boolean b=df.delete();
				logger.info("파일 삭제: {}", b);
			}
		}
		
		//비번일 일치하면 삭제 처리
		String msg="";
		String loc="";
		int n=this.boardService.deleteBoard(idx);
		msg=(n>0)?"글 삭제 성공":"글삭제 실패";
		loc=(n>0)?"boardList":"javascript:history.back()";
		return util.addMsgLoc(m, msg, loc);
	}
	/*[계층형 게시판-답변글쓰기 관련]*/
	@PostMapping("/rewrite")
	public String rewriteForm(Model m, @ModelAttribute("board") BoardVO board) {
		
		logger.info("board={}", board);
		
		m.addAttribute("board", board);//글번호, 제목
		return "/board/boardRewrite";
	}
	

}/////////////////////////////////////////////










