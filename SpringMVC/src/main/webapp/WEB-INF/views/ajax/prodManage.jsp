<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/top"/>
<script>
	//[1] XMLHttpRequest객체를 이용
	//[2] jquery 의 $.ajax()함수를 이용
	//[3] axios 라이브러리 이용
	/*$.ajax({
		  type: '요청방식(get|post)',
		  url : '서버페이지기술',
		  dataType:'응답받을 데이터 유형(json,text,xml, html)',
		  data: '전송할파라미터데이터',
		  success: function(){
			  성공적인 응답이 올경우 이 함수에서 처리
		  },
		  error: function(){
			  에러가 발생할 경우 이 함수에서 처리
		  }
	   })
	*/

	function getProd(){
		//alert('hi')
		$.ajax({
			type:'get',
			url:'prodAll',
			dataType:'json',
			cache:false,
			success:function(res){
				//alert(res) //xml로 받을 경우=>XMLDocument객체로 들어온다.
				//let pname=$(res).find('pname').text();
				//json으로 받을 경우==[{object:Object}]
				//alert(res)
				//showList(res)
				showTable(res);
			},
			error:function(err){
				alert('error: '+err.status)
			}
		})
	}
	//응답이 배열로 올경우 => 반복문 이용
	function showList(res){
		let str="<ul class='list-group'>";
		$.each(res, function(i, item){
			//alert(item.pname)
			str+="<li class='list-group-item'>";
			str+="<h3>"+item.pname+"</h3>";
			//여기에 상품 이미지 들어가게 <img>태그로 경로 맞춰서
			str+="<img src='product_images/"+item.pimage1+"' class='img img-thumbnail' width='180px'>";
			str+="<h3>가격: "+item.saleprice+"</h3>";
			//상품가격도
			str+="</li>";
		})
		str+="</ul>";
		$('#result').html(str);
	}
	
	function showTable(res){
		let str=`<table class='table'>`;
			str+=`<tr>`
		$.each(res, (i, item)=>{
			str+=`
				
					<td>
						<h3>`+item.pname+`</h3>
						<img src='product_images/`+item.pimage1+`' class='img img-thumbnail' width='180px'>
						<h3>가격: `+item.saleprice+`</h3>
					</td>
				`
			if(i%4==3){
				str+=`</tr><tr>` //줄바꿈
			}
		})		
		str+=`</tr>`;		
		str+=`</table>`	;
		$('#result').html(str);
	}
	
</script>
	
	<div class="container" style="text-align:center">
		
		<button onclick="getProd()">상품목록 가져오기(xml)</button>
		
		<hr>
		<div id="result">
		
		</div>
	</div>
<c:import url="/foot"/>