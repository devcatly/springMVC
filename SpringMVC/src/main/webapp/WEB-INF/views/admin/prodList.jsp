<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/top"/>
	<!-- table정렬 플러그인------------------------------- -->
	<script src="../resources/js/stupidtable.js"></script>
	<!-- ------------------------------------------- -->
	<script>
		$(function(){
			$('#products').stupidtable();
		})
	</script>
	
	<div class="container" style="text-align:center">
		
		<div class="row">
        <div class="col-md-12">
           <h1 class="text-center m-4" style="margin:1em">::Product List [Admin Page]:::</h1>
           
           <table class="table table-striped" id="products">
              <thead>
                 <tr>
                    <th>상품번호</th>
                    <th>카테고리</th>
                    <th data-sort="string">상품명</th>
                    <th>이미지</th>
                    <th data-sort="string">가    격</th>
                    <th>수정|삭제</th>
                 </tr>
              </thead>
              <tbody>
              <!-- ------------------------ -->
              <c:if test="${prodArr eq null or empty prodArr}">
              	<tr>
              		<td colspan="6">등록된 상품이 없습니다</td>
              	</tr>
              </c:if>
              <c:if test="${prodArr ne null and not empty prodArr }">
              <c:forEach var="item" items="${prodArr}">
                 <tr>   
                    <td>${item.pnum}</td>
                    <td>${item.cg_num}
                    </td>
                    <td>${item.pname}</td>
                    <td width="15%">
                    <a href="../prodDetail?pnum=${item.pnum}" target="_blank">
                    <c:if test="${item.pimage1 != null}">
                       <img src="../product_images/${item.pimage1}" class="img-fluid" 
                       style="width:90%;margin:auto">
                    </c:if>
                    <c:if test="${item.pimage1 == null}">
                       <img src="../product_images/noimage.png" class="img-fluid" 
                       style="width:90%;margin:auto">
                    </c:if>
                    </a>
                    </td>
                    <td>
                    <del>정  가: ${item.price}원</del> 
                    <br>
                    <b class="text-primary">판매가 :${item.saleprice}원</b><br>
                    <span class="badge badge-danger">${item.percent}%할인</span>
                    </td>
                    <td>
                    <a href="javascript:edit('a')">수정</a>|
                    <a href="#" onclick="remove('a')">삭제</a>
                    </td>
                 </tr>
                </c:forEach>
             </c:if>
                 
            <!-- ------------------------ -->                 
              </tbody>
              
           </table>
        </div>
      </div>
		
	</div>
<c:import url="/foot"/>