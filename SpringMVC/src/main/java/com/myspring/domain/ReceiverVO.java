package com.myspring.domain;

public class ReceiverVO {
	private String onum;
	private String rcvname;
	private String hp1;
	private String hp2;
	private String hp3;
	private String zipcode;
	private String addr1;
	private String addr2;
	//setter, getter------	
	public String getOnum() {
		return onum;
	}
	public void setOnum(String onum) {
		this.onum = onum;
	}
	public String getRcvname() {
		return rcvname;
	}
	public void setRcvname(String rcvname) {
		this.rcvname = rcvname;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAllHp() {
		return hp1+"-"+hp2+"-"+hp3;
	}
	public String getAllAddr() {
		return "["+zipcode+"] "+addr1+" "+addr2;
	}
	@Override
	public String toString() {
		return "ReceiverVO [onum=" + onum + ", rcvname=" + rcvname + ", hp1=" + hp1 + ", hp2=" + hp2 + ", hp3=" + hp3
				+ ", zipcode=" + zipcode + ", addr1=" + addr1 + ", addr2=" + addr2 + "]";
	}

	
}
