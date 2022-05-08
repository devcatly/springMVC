<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/top.jsp"  />
<c:set var="myctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${myctx}/resources/css/memo.css"> 

<script>
	function check(){
		if(!frm.name.value){
			alert('작성자를 입력하세요');
			frm.name.focus();
			return false;
		}
		if(!frm.msg.value){
			alert('메모 내용을 입력하세요');
			frm.msg.focus();
			return false;
		}
		return true;
	}
</script>

<div id="content">

	<div id="memo_wrap">
		<h1>::: ${message} :::</h1>
		<%-- el표현식을 이용 ==> ${requestScope.message} --%>
				
		<%-- <%=request.getAttribute("message"); %> 와 동일 --%>
		
		
		<form name="frm" action="memoAdd" method="post" onsubmit="return check()">
			<table id="memoTable">
				<tr>
					<th class="m1">작성자</th>
					<td>
						<input type="text" name="name" placeholder="Name" class="box sm">
					</td>
				</tr>
				
				<tr>
					<th class="m1">메모내용</th>
					<td>
						<input type="text" name="msg" maxlength="100"
						 placeholder="Message" class="box lg">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:right; padding-right:3rem">
						<button class="btn">메모 남기기</button>
						<button type="reset" class="btn">다시 쓰 기</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/foot.jsp"  />
