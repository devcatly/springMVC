<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
   href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
   rel="stylesheet" type="text/css">
<link
   href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
   rel="stylesheet" type="text/css">
</head>
<body>

   <div class="navbar navbar-default navbar-static-top">
      <div class="container">
         <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
               data-target="#navbar-ex-collapse">
               <span class="sr-only">Toggle navigation</span><span
                  class="icon-bar"></span><span class="icon-bar"></span><span
                  class="icon-bar"></span>
            </button>
            
            <c:set var="myctx" value="${pageContext.request.contextPath}"  scope="session"/>
            
            <a class="navbar-brand" href="${myctx}/index"><span>ICT SHOP</span></a>
         </div>
         <div class="collapse navbar-collapse" id="navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
               <li class="active"><a href="${myctx}/memoList">Memo</a></li>
               <li><a href="${myctx}/admin/index">Admin</a></li>
               <li><a href="${myctx}/signup">Signup</a></li>
               <li><a href="${myctx}/user/mypage">MyPage</a></li>
               <li><a href="${myctx}/user/cartList">Cart</a></li>
               <li><a href="${myctx}/board">Board Write</a></li>
               <li><a href="${myctx}/boardList">Board List</a></li>
               <c:if test="${loginUser == null}">
               <li><a href="#myModal" data-toggle="modal">Login</a></li>
               </c:if>
               <c:if test="${loginUser != null }">
               <li class="bg-info">
               <a href="#">${loginUser.userid}님 로그인 중...</a></li>               
               <li><a href="${myctx}/logout">Logout</a></li>
               </c:if>
               
               <li><a href="${myctx}/fileForm">파일업로드1</a></li>
            </ul>
         </div>
      </div>
   </div>
   <!-- top끝 -----------------------------------------ㄴ -->  
  