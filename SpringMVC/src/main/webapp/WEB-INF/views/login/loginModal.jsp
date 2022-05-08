<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Modal ------------------------------ -->
        <div class="modal" id="myModal" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content -->
                <div class="modal-content">
                    <div class="modal-header" style="text-align: center;">
                        <h2 class="text-primary">Login Page</h2>
                    </div>
                    <div class="modal-body">
                        <form name="loginF" action="${pageContext.request.contextPath}/login" method="post">
                            <div class="form-group">
                                <label for="userid">아이디:</label>
                                <%-- ${cookie.키.value} --%>
                                <input type="text" name="userid" id="userid"                                
                                value="${cookie.uid.value}"                                
                                required
                                placeholder="User ID" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="pwd">비밀번호:</label>
                                <input type="password" name="pwd" id="pwd"
                                required
                                placeholder="Password" class="form-control">
                            </div>
                            <div class="checkbox">
                                <label>
                   <input type="checkbox" name="saveId"
                   	<c:if test="${cookie.uid ne null}">
                   		checked
                   	</c:if>
                    id="saveId">
                                    아이디 저장
                                </label>
                            </div>
                            <div style="text-align:right">
                                <button class="btn btn-success">Login</button>
                                <button class="btn btn-danger" data-dismiss="modal">
                                    Close
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        
                    </div>
                </div>                
            </div>
        </div>
        <!-- ------------------------------------ -->