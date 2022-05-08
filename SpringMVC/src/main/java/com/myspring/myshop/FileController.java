package com.myspring.myshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	private String UP_DIR="C:/MyJava/upload";
	
	@GetMapping("/fileForm")
	public String fileForm() {
	
		return "fileup/fileForm";
	}
	@PostMapping("/fileUpload")
	public String fileUpload(Model m, 
			@RequestParam(defaultValue = "") String writer,
			@RequestParam("filename") List<MultipartFile> filename) {
		
		logger.info("writer={}",writer);
		logger.info("filename={}",filename);
		if(filename!=null && !filename.isEmpty()) {
			//첨부파일이 있다면
			int i=0;
			for(MultipartFile mf: filename) {
				//파라미터명
				String param=mf.getName();//파라미터명
				logger.info("파라미터명: {}", param); //filename
				//첨부파일명
				String fname=mf.getOriginalFilename();
				logger.info("첨부파일명:{}", fname);
				
				long fsize=mf.getSize();
				logger.info("첨부파일크기:{}", fsize);
				//업로드 처리--transferTo()
				try {
				mf.transferTo(new File(UP_DIR, fname));
				}catch(Exception e) {
					logger.error("파일 업로드 에러: {}", e.getMessage());
				}
				i++;
				m.addAttribute("file"+i, fname);
				
			}//for-------------
			m.addAttribute("writer", writer);
		}
		
		return "fileup/fileUploadEnd";
	}//---------------------------
	/*@ResponseBody 해당 return을 다양한 형태로 변경/지원
	 * 하는 기능. application/json 또는 application/xml
	 * 등으로 응답형태를 지정해야 할 때 사용*/
	@RequestMapping("/fileDown")
	@ResponseBody
	public void fileDownload(HttpServletRequest req, HttpServletResponse res) 
			throws FileNotFoundException, IOException {
		//다운로드 할 파일명 받기
		logger.info("filename={}", req.getParameter("filename"));
		logger.info("originFilename={}", req.getParameter("originFilename"));
		String filename=req.getParameter("filename");
		String originFilename=req.getParameter("originFilename");
		
		res.setContentType("application/octet-stream");
		//한글파일명 처리
		String origin_en=new String(originFilename.getBytes(), "ISO-8859-1");
		String str="attachment; filename="+origin_en;
		res.setHeader("Content-Disposition", str);
		
		String UP_DIR=req.getServletContext().getRealPath("/resources/Upload");
		
		File file=new File(UP_DIR, filename);
		FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
	}
}
