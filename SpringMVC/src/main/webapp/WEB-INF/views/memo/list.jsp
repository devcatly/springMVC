<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/top.jsp"  />
<c:set var="myctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${myctx}/resources/css/memo.css"> 

<!-- core taglib ==> 제어문들이 있음 
		if문
		forEach문
		choose문
-->    

<div id="content" class="col-md-10 col-md-offset-1">
	
	<table class="table">
				<thead>
					<tr>
						<th colspan="4">
							<h1>:::한줄 메모장::: <span class="badge">${totalCount}</span> </h1>
						</th>
					</tr>
					<tr>
						<td>글번호</td>
						<td>메모내용</td>
						<td>작성자</td>
						<td>수정|삭제</td>
					</tr>
				</thead>
				
				<tbody>
					<c:if test="${memoArr !=null && not empty memoArr }">
						<%-- if(memoArr!=null && memoArr.size()!=0) --%>
						<c:forEach var="memo" items="${memoArr}">
						<!--  items: ArrayList등의 자료구조를 값으로 할당
								for(MemoVO memo:memoArr){...}
						-->
						<tr>
							<td>${memo.idx}</td>
							<td>${memo.msg}</td>
							<td>${memo.name}</td>
							<td>
							<a href="memoEdit?idx=${memo.idx}">수정</a>|
							<a href="memoDel?idx=${memo.idx}" >삭제</a>
							</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${memoArr == null || empty memoArr }">
						<tr>
							<td colspan="4">
							데이터가 없습니다.
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="4" style="text-align:center">
							<ul class="pagination">
							<c:forEach var="i" begin="1" end="${pageCount}">
								<li  <c:if test="${cpage ==i}">class="active"</c:if> >
									<a href="memoList?cpage=${i}">${i}</a>
								</li>
							</c:forEach>
								
							</ul>
						
						</td>
					</tr>
				</tbody>
			</table>
</div>
<jsp:include page="/WEB-INF/views/foot.jsp"  />