package com.myspring.domain;

import java.sql.Date;

public class BoardVO {
	private String mode; //글쓰기:write, 글수정: edit, 답변글쓰기: rewrite
	
	private int idx;
	private String name;
	private String subject;
	private String content;
	private int readnum;
	
	private String filename;//첨부파일명(물리적 파일명: 파일명_xxxx.txt)
	private String originFilename;//원본파일명
	private long filesize;//첨부파일크기
	
	private String old_filename;//예전에 첨부했던 파일명[수정처리시]
	
	private java.sql.Date wdate;
	private String pwd;
	
	private int refer;
	private int lev;
	private int sunbun;
	
	//댓글수
	private int re_cnt;
	
	public BoardVO() {
		
	}	
	//setter, getter---------------
	public String getOld_filename() {
		return old_filename;
	}
	
	
	public void setOld_filename(String old_filename) {
		this.old_filename = old_filename;
	}
	
	
	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadnum() {
		return readnum;
	}

	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public java.sql.Date getWdate() {
		return wdate;
	}

	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}

	public int getRe_cnt() {
		return re_cnt;
	}

	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getOriginFilename() {
		return originFilename;
	}
	public void setOriginFilename(String originFilename) {
		this.originFilename = originFilename;
	}
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getSunbun() {
		return sunbun;
	}
	public void setSunbun(int sunbun) {
		this.sunbun = sunbun;
	}
	@Override
	public String toString() {
		return "BoardVO [mode=" + mode + ", idx=" + idx + ", name=" + name + ", subject=" + subject + ", content="
				+ content + ", readnum=" + readnum + ", filename=" + filename + ", originFilename=" + originFilename
				+ ", filesize=" + filesize + ", wdate=" + wdate + ", refer=" + refer + ", lev=" + lev + ", sunbun="
				+ sunbun + ", re_cnt=" + re_cnt + "]";
	}

	
	
}///////////////////////////////////////
