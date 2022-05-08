<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/top"/>
<%-- 
	# 파일 업로드시 주의사항
	[1] form의  method속성은 반드시 POST로 준다.
	[2] POST방식일 경우 인코딩 방식이 2가지가 있는데
	    이중 multipart/form-data 를 주어야 한다.
	    
	    cf> enctype
	    <1> application/x-www-form-url-encoded : 디폴트값
	    	==>이경우 파일 이름만 전송됨
	    <2> multipart/form-data
	    	==> 이 경우 파일 이름은 물론, 파일 데이터가
	    	    함께 전송된다.
	[3] pom.xml에 아래 의존 라이브러리 등록
	
		<!--FileUpload -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.2.2</version>
		</dependency>
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
		
	[4] servlet-context.xml에 MultipartResolver빈 등록해야 함	
	<!-- MultipartResolver설정 [파일업로드 관련] ======================== 
		주의: id는 반드시 multipartResolver로 등록해야 한다.
		    다른 아이디를 주면 DispatcherServlet이 MultipartResolver로 인식하지 못한다.
	-->
	<beans:bean id="multipartResolver"	 class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="defaultEncoding" value="UTF-8"/>
		<beans:property name="maxUploadSize" value="-1"/>
		<!-- -1을 주면 업로드 용량 무제한 -->
	</beans:bean>
	--%>
	
	<div class="container" style="text-align:center">
		
		<h1>::File Upload::</h1>
		<form name="fileF" method="post" 
		enctype="multipart/form-data" action="fileUpload">
		<table border="1" style="width:90%" class="table table-condensed">
		<tr>
			<th colspan="2">::File Upload::</th>
		</tr>
		<tr>
			<td width="20%"><b>올린이</b></td>
			<td width="80%">
				<input type="text" name="writer"
				 id="writer" class="box">
			</td>
		</tr>
		<tr>
			<td width="20%"><b>파일선택1</b></td>
			<td width="80%">
				<input type="file" name="filename"
				 id="fileone" class="box">
			</td>
		</tr>
		<tr>
			<td width="20%"><b>파일선택2</b></td>
			<td width="80%">
				<input type="file" name="filename"
				 id="filetwo" class="box">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" onclick="Upload()"
				value="파일 올리기">
			</td>
		</tr>
	</table>
		
		</form>
		
	</div>
<c:import url="/foot"/>