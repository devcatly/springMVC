<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/top"/>
	
	<div class="container" style="text-align:center">
		
		<h1>올린이: ${writer }</h1>
		<h2>첨부파일1: ${file1}</h2>
		<h2>첨부파일2: ${file2}</h2>
		
	</div>
<c:import url="/foot"/>