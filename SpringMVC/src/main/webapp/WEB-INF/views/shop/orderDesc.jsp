<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--  -->
<c:import url="/top"/>
<div class="container">
<div class="row">
<div align="center" class="col-md-10 offset-md-1">
<hgroup>
   <h1>주문 내역 정보</h1>
</hgroup>

   <br/>
   <%-- ${orderDesc[0]} --%>
   <!-- 주문내역의 공통사항을 추출하여 order에 셋팅 -->
	<c:set var="order" value="${orderDesc[0]}"/>
   
   <table  class="table">
      <tr height="30" bgcolor="#ffefef">
         <th colspan="4" class="text-center">
         <span class="glyphicons glyphicons-notes"></span>
         <h3>${loginUser.name}
         [${loginUser.userid}]님의 주문 내역서</h3>
         </th>
      </tr>
      <tr>
         <td>주문번호</td>
         <td><b>${onum}</b></td>
         <td>수령자</td>
         <td><b>${order.receiver.rcvname}</b></td>
      </tr>      
   
      <tr>
         <td>총구매가격</td>
         <td><b style="color:red">
      <fmt:formatNumber value="${order.ototalPrice}"
       pattern="###,###"/>   
            원</b>
      <br>[사용포인트:<b>${order.omileage}</b>POINT]      
            </td>
         <td>총누적 포인트</td>
         <td><b>${order.ototalPoint} POINT</b></td>
      </tr> 
      <tr>
         <td>연락처</td>
         <td><b>${order.receiver.allHp}
         </b></td>
         <td>메모</td>
         <td><b>${order.omemo}</b></td>
      </tr>
      <tr>
         <td>배송지</td>
         <td colspan="3">
         <b>
         ${order.receiver.allAddr}
         </b>
         </td>         
      </tr>
   </table>
   <br/>
   <table width="95%" class="table table-bordered">
      <tr bgcolor="#ffefef" height="30">
         <th>상품번호</th>
         <th>상품명</th>
         <th>판매가</th>
         <th>수량</th>
         <th>소계금액</th>
         <th>지불상태</th>
         <th>배송상태</th>
      </tr>
      <!-- ---------------- -->
     <c:forEach var="prod" items="${order.orderList}">
      <tr>
         <td>${prod.pnum}</td>
         <td align="center">
   <a href="../prodDetail?pnum=${prod.pnum}" target="_blank">
   <img src="../product_images/${prod.pimage1}"
          width="80" height="80" /></a>
         <br/> 
         <b>${prod.pname}</b>
         </td>
         <td align="right">
         <fmt:formatNumber value="${prod.saleprice}" pattern="###,###"/>
         	원
         	<br>
         	[${prod.point}]POINT
         </td>
         <td>${prod.pqty}개</td>
         <td align="right">
         <fmt:formatNumber 
         value="${prod.saleprice * prod.pqty}" pattern="###,###"/>
         	원
         <br>	
         [${prod.point * prod.pqty} ]POINT	
         </td>
         <td>${order.opayState}</td>
         <td>${order.odeliverState}</td>
      </tr>
	  </c:forEach>	
      <!-- ---------------- -->
   
   </table>   
</div>
</div>
</div>
<c:import url="/foot"/>