<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/top" />

<div class="container">

	<h1 style="text-align:center">Board List</h1>
	<p style="text-align:center">
		<a href="board">글쓰기</a>
	</p>
	<!-- 검색 폼 시작-------------------------------- -->
	<form name="findF" action="boardList">
	<div class="row">
		<div class="col-md-2 col-md-offset-1">
			<select name="findType" class="form-control">
				<option value="0">::검색 유형::</option>
				<option value="1">제목</option>
				<option value="2">작성자</option>
				<option value="3">글내용</option>
			</select>
		</div>
		<div class="col-md-6">
			<input type="search" name="findKeyword" required
			 placeholder="검색어" class="form-control">
		</div>
		<div class="col-md-3">
			<button class="btn btn-success">검  색</button>
		</div>		
	</div>
	</form>
	<!-- --------------------------------------- -->
	
	<table class="table table-striped" style="margin-top:2rem;">
		<tr>
			<th>글번호</th>
			<th style="width:40%">제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<!-- ------------------------ -->
		<c:if test="${boardArr eq null or empty boardArr}">
			<tr>
				<td colspan="5"><b>게시글이 없습니다</b></td>
			</tr>
		</c:if>
		<c:if test="${boardArr ne null and not empty boardArr}">
		<c:forEach var="board" items="${boardArr}">
		<tr>
			<td>${board.idx}</td>
			<td>
			<!-- 답변 레벨에 따라 들여쓰기 하자 -->
			<c:forEach var="k"  begin="0" end="${board.lev}">
				&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${board.lev>0}">
				<img src="images/re.png">
			</c:if>
			<!-- 글제목 -->			
			<a href="boardView/${board.idx}">${board.subject}</a>
				<!--  
					boardView?idx=1 ==> Query String parameter방식==> @RequestParam으로 받으면 된다.
					boardView/1 => Path접근방식 ==> @PathVariable 받아야 함
				-->
			<!-- 첨부파일 여부 -->
			<c:if test="${board.filename != null}">			
				<img src="images/file.png" style="width:22px">
			</c:if>
			
			<!-- 댓글수 보여주기 -->
			<%-- <c:if test="${board.re_cnt > 0}">
				<span class="badge">${board.re_cnt}</span>
			</c:if> --%>
			
			</td>
			<td>${board.name}</td>
			<td>${board.wdate}</td>
			<td>${board.readnum}</td>
		</tr>
		</c:forEach>
		</c:if>
		<!-- ------------------------ -->
		<tr>
			<td colspan="3" style="text-align:center">
			<!-- page navigation  
			begin:시작값 지정
			end : 끝값
			step: 증가치
			-->
			<ul class="pagination">
			<c:if test="${page.prevBlock>0}">
				<li>
					<a href="boardList?cpage=${page.prevBlock}&findType=${param.findType}&findKeyword=${param.findKeyword}">Prev</a>
				</li>
			</c:if>
			<c:forEach var="i" begin="${page.prevBlock +1}" end="${page.nextBlock-1}" step="1">
				<c:if test="${i <=page.pageCount }">
					<li <c:if test="${page.cpage eq i}">class='active'</c:if> >
						<a href="boardList?cpage=${i}&findType=${param.findType}&findKeyword=${param.findKeyword}">${i}</a>
					</li>
				</c:if>
			</c:forEach>
			
			<c:if test="${page.nextBlock<=page.pageCount}">
				<li>
					<a href="boardList?cpage=${page.nextBlock}&findType=${param.findType}&findKeyword=${param.findKeyword}">Next</a>
				</li>
			</c:if>
			
			</ul>
			
			</td>		
			<td colspan="2" style="text-align:right">
				<span class="text-primary">
					총 게시글 수 : ${totalCount} 개
				</span>
			</td>		
		</tr>
	</table>
</div>
<c:import url="/foot" />



