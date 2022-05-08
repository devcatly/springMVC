<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
    
<%-- isErrorPage="true" 로 주는 것에 주의하자--%>
<% 
	response.setStatus(200);
%>

<script>
	alert('자식 레코드 발견됨')
	//alert('<%=exception.getMessage()%>')
	history.back();
</script>