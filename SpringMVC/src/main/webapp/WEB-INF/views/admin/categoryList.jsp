<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table class='table table-striped'>
	<tr>
		<th>카테고리번호</th>
		<th>카테고리코드</th>
		<th>카테고리이름</th>
		<th>삭제</th>
	</tr>
	<!-- -------------- -->
	<c:forEach var="cate" items="${cateAll}">   
	   <tr>
	      <td>${cate.cg_num }</td>
	      <td>${cate.cg_code }</td>
	      <td>${cate.cg_name }</td>
	      <td>
	      <a href="#" onclick="goDel('${cate.cg_num}')" class="btn btn-success">삭제</a>
	      </td>
	   </tr>
   </c:forEach>
	<!-- --------------- -->
</table>