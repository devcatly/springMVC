package com.myspring.domain;

import java.sql.Date;

public class MemberVO {
	//멤버변수: property
	private int idx;
	private String name;
	private String userid;
	private String pwd;
	private String hp1;
	private String hp2;
	private String hp3;
	private String zipcode;
	private String addr1;
	private String addr2;
	private java.sql.Date indate;
	private int mileage;
	
	private boolean saveId;//아이디 저장 체크박스 값이 들어감
	
	
	public MemberVO() {
	
	}//기본생성자-----

	//setter, getter-------
	public boolean isSaveId() {
		return saveId;
	}

	public void setSaveId(boolean saveId) {
		this.saveId = saveId;
	}

	
	
	public void setName(String name) {
		this.name = name;
		System.out.println("setName(): "+name);
	}
	
	
	public String getName() {
		return name;
	}
	public void setUserid(String userid) {
		this.userid = userid;
		System.out.println("setUserid(): "+userid);
	}
	
	public String getUserid() {
		return userid;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	
	public String getHp1() {
		return hp1;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	
	public String getHp2() {
		return hp2;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	
	public String getHp3() {
		return hp3;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	public String getAddr1() {
		return addr1;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	public String getAddr2() {
		return addr2;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public java.sql.Date getIndate() {
		return indate;
	}

	public void setIndate(java.sql.Date indate) {
		this.indate = indate;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	public String getAllHp() {
		return hp1+"-"+hp2+"-"+hp3;
	}
	
	public String getAllAddr() {
		return "["+zipcode+"] "+addr1+" "+addr2;
	}
	
	@Override
	public String toString() {
		return "MemberVO [idx=" + idx + ", name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", hp1=" + hp1
				+ ", hp2=" + hp2 + ", hp3=" + hp3 + ", zipcode=" + zipcode + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", indate=" + indate + ", mileage=" + mileage + ", saveId=" + saveId + "]";
	}

	
}
