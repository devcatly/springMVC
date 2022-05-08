<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="/top" />    

<div class="container" style="margin-top:2rem;">
	<h1 style="text-align:center">Board View</h1>
	
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-hover table-striped">
				<tr>
					<td style="width:20%"><b>글번호</b></td>
					<td style="width:30%">${board.idx}</td>
					<td style="width:20%">작성일</td>
					<td style="width:30%">${board.wdate}</td>
				</tr>
				
				<tr>
					<td style="width:20%"><b>글쓴이</b></td>
					<td style="width:30%">${board.name}</td>
					<td style="width:20%">조회수</td>
					<td style="width:30%">${board.readnum}</td>
				</tr>
				
				<tr>
					<td style="width:20%"><b>첨부파일</b></td>
					<td colspan="3">
					
					<a href="#" onclick="goDown('${board.filename}')">${board.originFilename}</a>
					 
					[${board.filesize} bytes]</td>
				</tr>
				<tr>
					<td style="width:20%"><b>제목</b></td>
					<td colspan="3">${board.subject}</td>
				</tr>
				<tr style="height:200px;">
					<td style="width:20%"><b>글내용</b></td>
					<td colspan="3" style="overflow: auto">
					<pre style="border:none">${board.content}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-center">
					<a href="../boardList"><i class="fa fa-2x fa-align-justify fa-fw"></i></a>|
					
					<a href="#" onclick="goEdit()"><i class="fa fa-edit fa-fw fa-2x"></i></a>|
					<a href="#" onclick="goDel()"><i class="fa fa-2x fa-cut fa-fw"></i></a>|
										
					<a href="javascript:goRe()"><i class="fa fa-2x fa-fw fa-reply hub"></i></a>
					</td>
				</tr>
				
			</table>
		</div> <!-- .col end -->
	</div> <!-- .row end -->
	
</div> <!-- .container end -->
<!-- 게시글 삭제 또는 수정 폼 ------------------- -->
<form name="frm" method="post">
	<input type="hidden" name="idx" value="${board.idx}">
	<input type="hidden" name="mode">
	<div class="row mt-4" id="divPwd" style="display:none">
            <div class="col-md-3 col-md-offset-1" style="text-align:right">
               <label>글 비밀번호</label>
            </div>
            <div class="col-md-4">
               <input type="password" name="pwd" id="bpwd" class="form-control" placeholder="Password" required>
            </div>
            <div class="col-md-3">
               <button class="btn btn-success" id="btn"></button>
            </div>
     </div>
</form>
<!-- ----------------------------------- -->

<!-- 파일 다운로드를 위한 form --------------- -->
<form name="fileF" action="${pageContext.request.contextPath}/fileDown" method="post">
	<input type="hidden" name="filename" value="${board.filename}"><!-- 물리적 파일명 -->
	<input type="hidden" name="originFilename" value="${board.originFilename}"><!-- 원본파일명 -->	
</form>
<!-- --------------------------------- -->

<!-- 답변달기를 위한 form---------------------- -->
<form name="reF" action="${pageContext.request.contextPath}/rewrite" method="post">
	<!-- hidden으로 부모글(원글)의 글번호(idx)와 제목(subject)를 보내자 -->
	<input type="hidden" name="idx" value="${board.idx}">
	<input type="hidden" name="subject" value="${board.subject}">
</form>
<!-- ------------------------------------- -->
<script>
	function goRe(){
		reF.submit();
	}

	function goDel(){
		frm.mode.value='delete';
		frm.action="../boardDel";
		$('#btn').text('글삭제')
		$('#divPwd').show(1000)
		$('#bpwd').focus();
		
	}
	function goEdit(){
		frm.mode.value='edit';
		frm.action="../boardEdit"
		$('#btn').text('글수정')
		$('#divPwd').show(1000)
		$('#bpwd').focus();
	}
	function goDown(fname){
		fileF.submit();
		//alert(fname);
		//location.href='../FileDown?fname='+encodeURIComponent(fname);
		//encodeURIComponent(): uri에 들어오는 한글을 인코딩 처리해줌
	}

</script>

<c:import  url="/foot" />


