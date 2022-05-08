<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--  -->
<c:import url="/top"/>
<div class="container">
<div class="row">
<div align="center" class="col-md-10 col-md-offset-1">
<hgroup>
	<h1>MyPage</h1>
</hgroup>

	<br/>
	
	<!-- 주문내역의 공통사항을 추출하여 order에 셋팅ㄷ -->
	
	<table  class="table">
		<tr height="30" bgcolor="#ffefef">
			<th colspan="5" class="text-center">
			<span class="glyphicons glyphicons-notes"></span>
			<h3>${loginUser.name}
			[${loginUser.userid}]님의 MyPage</h3>
			</th>
		</tr>
		<tr>
			<th>주문번호</th>
			<th>상품명</th>
			<th>이미지</th>
			<th>주문날짜</th>
			<th>주문상세</th>
		</tr>
		<!-- -------------- -->
		<c:forEach var="ovo" items="${myOrderList}">
		<tr>
			<td>${ovo.onum}</td>
			<td>${ovo.pname}</td>
			<td>
				<img src="../product_images/${ovo.pimage}" width="80px">
			</td>
			<td>${ovo.orderDate}</td>
			<td>
			<a href="orderDetail?onum=${ovo.onum}" target="_blank">주문상세</a>
			</td>
		</tr>		
		</c:forEach>
		<!-- -------------- -->
	</table>	
</div>
</div>
</div>	
<c:import url="/foot"/>	
