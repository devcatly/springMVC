<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	
	<div class="container" style="text-align:center">
		
		

<div class = "row">
   <div class = "col-md-12">
      <h1 class = "badge badge-warning"> ${pspec}</h1>
   </div>
</div>

<div class = "row">
<c:if test="${plist eq null or empty plist}">
		<div class="col-md-3">
			<h4>상품 준비 중</h4>
		</div>
</c:if>
<c:if test="${plist ne null and not empty plist }"> 
	<c:forEach var="pd" items="${plist}" varStatus="state" begin="0" end="3">
	<!--  varStatus를 이용하면 반복문의 상태정보를 알아낼 수 있다.
			state.count : 반복문 횟수 (1부터 시작)
			state.index : 인덱스 (0부터 시작)
		  begin : 시작 인덱스
		  end :   끝 인덱스	
	-->
	      <div class = "col-md-3" style="text-align:center">
	         <a href="prodDetail?pnum=${pd.pnum}">
	         <c:if test="${pd.pimage1 ne null}">
	         <img src = "product_images/${pd.pimage1}" class = "img img-responsive">
	         </c:if>
	         <c:if test="${pd.pimage1 eq null}">
	         <img src = "product_images/noimage.png" class = "img img-responsive"
	         style = "height:220px">
	         </c:if>
	         </a>
	         <br><br>
	         <h4> ${pd.pname} </h4>
	         <del> 
				<fmt:formatNumber value="${pd.price}" pattern="###,###"/>
	         </del> 원 <br>
	         <span style="color:blue;font-weight:bold"> 
	         	<fmt:formatNumber value="${pd.saleprice}" pattern="###,###"/>
	         </span> 원 <br>
	         <span class = "label label-danger"> ${pd.percent}%</span> <br>
	         <span class = "label label-success">${pd.point}</span> POINT <br>
	      </div> <!--  col-md-3 end -->
	      </c:forEach>
	     </c:if>
      	</div> <!--  row end -->
		
	</div><!--  .container end -->
