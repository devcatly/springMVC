package com.myspring.domain;

import java.sql.Date;
import java.util.List;

public class OrderVO {
	//orderDesc 관련 프로퍼티
	private String onum; //주문번호
	private int idx_fk; //주문자 회원번호
	private int ototalPrice; //주문총액
	private int ototalPoint;
	private int odeliverCost;//배송비(default 3000)
	
	private String odeliverState;//배송상태(배송전, 배송중, 배송완료)
	private String opayWay;//지불방법(100:무통장입금, 200:카드결제)
	private String opayState;//지불상태(미결제,결제완료,결제취소)
	private Date orderDate;//주문일
	private int omileage;//사용 마일리지
	private String omemo;//주문 요청사항

	//orderProduct관련 프로퍼티
	private String pname;
	private String pimage;
	//OrderVO has Products
	private List<ProductVO> orderList;//주문상품 목록

	//OrderVO has a Receiver
	private ReceiverVO receiver;//수령자 정보

	public String getOnum() {
		return onum;
	}

	public void setOnum(String onum) {
		this.onum = onum;
	}

	public int getIdx_fk() {
		return idx_fk;
	}

	public void setIdx_fk(int idx_fk) {
		this.idx_fk = idx_fk;
	}

	public int getOtotalPrice() {
		return ototalPrice;
	}

	public void setOtotalPrice(int ototalPrice) {
		this.ototalPrice = ototalPrice;
	}

	public int getOtotalPoint() {
		return ototalPoint;
	}

	public void setOtotalPoint(int ototalPoint) {
		this.ototalPoint = ototalPoint;
	}

	public int getOdeliverCost() {
		return odeliverCost;
	}

	public void setOdeliverCost(int odeliverCost) {
		this.odeliverCost = odeliverCost;
	}

	public String getOdeliverState() {
		return odeliverState;
	}

	public void setOdeliverState(String odeliverState) {
		this.odeliverState = odeliverState;
	}

	public String getOpayWay() {
		return opayWay;
	}

	public void setOpayWay(String opayWay) {
		this.opayWay = opayWay;
	}

	public String getOpayState() {
		return opayState;
	}

	public void setOpayState(String opayState) {
		this.opayState = opayState;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOmileage() {
		return omileage;
	}

	public void setOmileage(int omileage) {
		this.omileage = omileage;
	}

	public String getOmemo() {
		return omemo;
	}

	public void setOmemo(String omemo) {
		this.omemo = omemo;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public List<ProductVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ProductVO> orderList) {
		this.orderList = orderList;
	}

	public ReceiverVO getReceiver() {
		return receiver;
	}

	public void setReceiver(ReceiverVO receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "OrderVO [onum=" + onum + ", idx_fk=" + idx_fk + ", ototalPrice=" + ototalPrice + ", ototalPoint="
				+ ototalPoint + ", odeliverCost=" + odeliverCost + ", odeliverState=" + odeliverState + ", opayWay="
				+ opayWay + ", opayState=" + opayState + ", orderDate=" + orderDate + ", omileage=" + omileage
				+ ", omemo=" + omemo + ", pname=" + pname + ", pimage=" + pimage + ", orderList=" + orderList
				+ ", receiver=" + receiver + "]";
	}
	
	
	
	

}
