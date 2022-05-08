<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/top"/>
<script>
	let req=false;
	
	let init = function(){
		try{
			req=new XMLHttpRequest();//ajax요청을 보낼 때 사용하는 자바스크립트 객체
			//alert(req);
		}catch(ex){
			//IE일 경우(옛날 버전일 경우)
			try{
				req=new ActiveXObject("Msxml2.XMLHTTP");
			}catch(ex2){
				req=false;
			}
		}
		if(!req){
			alert('req생성 실패')
		}
		
	}//-------------------
	
	let send = function(){				
		init();
		//ajax요청을 보내보자. 카테고리 등록을 하기 위해 사용자가 입력한 값 받자.
		
		let cname=cf.cg_name.value;
		let code=cf.cg_code.value;
		//서버쪽에 요청을 보내자. open()=> onreadystatechage속성에 콜백함수 지정 => send()함수 호출
		let url='${pageContext.request.contextPath}/admin/cateAdd?cg_name='+cname+"&cg_code="+code;
		req.open('GET', url , true);
		//open('요청방식', '서버페이지', 비동기여부) 
		req.onreadystatechange = updatePage;
		req.send(null);
		//get방식일 때는 null을 파라미터로 전달하고,
		//post방식일 때는 파라미터 데이터를 매개변수로 넣어준다.
		
	}//send()------------------
	
	let updatePage=function(){
		//alert(req.readyState+"/"+req.status)
		if(req.readyState==4 && req.status==200){
			//성공적인 응답이 왔다면 => 응답 데이터를 받아보자.
			let res=req.responseText;
			//응답유형(text,html,xml,json)
			//text형식이면 responseText로 받고
			//xml형식이면 responseXML로 받는다.
			//alert(res)
			let obj=document.getElementById('categoryList');
			//obj.innerHTML="<h2>"+res+"</h2>";
			//alert(typeof res) string "{"result":"1"}"
			//문자열을 JSON객체로 변환해주는 함수
			let jsonObj=JSON.parse(res);
			console.log(typeof jsonObj)
			if(jsonObj.result>0){
				//obj.innerHTML="등록 성공";
				getAllCategory()
			}else{
				alert('카테고리 등록 실패')
			}
			
		}
	}//updatePage()--------------------------
	
	let getAllCategory=function(){
		init();
		//모든 카테고리 목록 가져오는 요청을 보내자.
		let url="categoryAll";
		req.open("GET", url,true);
		req.onreadystatechange=function(){
			//alert(req.status)
			if(req.readyState==4&& req.status==200){
				let res=req.responseText; //text, html, json
				//alert(res)
				let obj=document.getElementById('categoryList');
				obj.innerHTML=res;
			}
		}//--------
		req.send(null);
	}//getAllCategory()-----------------------------
	
	let goDel=function(cg_num){
		//alert(cg_num);
		init();
		let url="cateDel?cg_num="+cg_num;
		req.open('GET', url, true);
		
		req.onreadystatechange= function(){
			//alert(req.status)
			if(req.readyState==4 && req.status==200){
				let res=req.responseText;
				//alert(res);
				let jsonObj=JSON.parse(res);
				if(jsonObj.result>0){
					getAllCategory()
					cf.cg_name.focus();
				}
				
			}
		}
		//req.send("cg_num="+cg_num)
		req.send(null);
	}//goDel()-------------------------
	
	
	window.onload=getAllCategory;
	

</script>

	<div class="container" style="text-align:center">
		<div>
		<a href="cateMgr">카테고리 관리</a>|
		<a href="prodForm">상품 등록</a>|
		<a href="prodList">상품 목록</a>|
		</div>
		<div class="row">
			<div class="col-md-6">
				<h3>카테고리 등록</h3>
				<form name="cf" id="cf" action="aaa">
				<table class="table">
					<tr>
						<td>
							카테고리명
						</td>
						<td>
							<input type="text" name="cg_name" id="cg_name"
							 class="form-control">
						</td>
					</tr>
					<tr>
						<td>
							카테고리코드
						</td>
						<td>
							<input type="text" name="cg_code" id="cg_code"
							 class="form-control">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center">
							<button type="button" onclick="send()" class="btn btn-primary">등록하기</button>
							<button type="reset" class="btn btn-danger">다시쓰기</button>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<div class="col-md-6" id="categoryList">
				<!-- 여기에 카테고리 목록 테이블 들어올 예정 -->
			</div>		
		</div>
		
	</div>
<c:import url="/foot"/>