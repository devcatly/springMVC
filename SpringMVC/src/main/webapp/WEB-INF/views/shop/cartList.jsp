<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/top"/>

	
	<div class="container" style="text-align:center">
		
		<div class="row">
<div align="center" class="col-md-12">
<!--주문 form 시작===================  -->
<form name="orderF" id="orderF" action="order" method="post">
<table class="table table-condensed">
	<thead>
	<tr class="default">
		<th colspan="6" style="text-align:center">
		<h3 class="text-primary">${loginUser.name}[${loginUser.userid}] 님의 장바구니</h3>
		</th>
	</tr>
	<tr>
		<th>
		<label for="allChk">
		<input type="checkbox" name="allChk" id="allChk">
		번호
		</label>
		</th>
		<th>상품명</th>
		<th>수량</th>
		<th>단가</th>
		<th>금액</th>
		<th>삭제</th>
	</tr>
	</thead>
	<tbody>
	
		<!------------------------------  -->
	<c:if test="${cart eq null or empty cart}">
		<tr><td colspan="6">장바구니 상품이 없습니다.</td></tr>		
	</c:if>	
	<c:if test="${cart ne null and not empty cart}">
	<c:forEach var="cvo" items="${cart }" varStatus="state">
	<!--  state.index : 인덱스번호
		  state.count : 반복문 횟수
	-->
		<tr>
			<td>
	<input type="checkbox" name="opnum" id="opnum${state.index}"
	 value="${cvo.pnum_fk}"> ${cvo.pnum_fk}
			</td>
			<td>
			<a href="../prodDetail?pnum=${cvo.pnum_fk}" target="_blank">
			<img src="../product_images/${cvo.pimage1}" width="100">
			</a>
			<br>
			${cvo.pname}
			</td>
			<td>
			<!--수량--------------  -->
			<input type="number" size="2" name="oqty"
			 id="oqty${state.index}"
			 value="${cvo.oqty}" style="width:50px">			 
			<button type="button" 
					onclick="goEdit('${cvo.cartNum}','${state.index}')"
			  class="btn btn-default">수정</button>
			<!---------------------  -->
			</td>
			<td>
		<fmt:formatNumber value="${cvo.saleprice}" pattern="###,###"/>원<br>
		[${cvo.point}]POINT
			</td>
			<td>
		<fmt:formatNumber value="${cvo.totalPrice}" pattern="###,###"/>원	
		<br>총 [${cvo.totalPoint}  ]POINT
			</td>
		<td>
		<a href="javascript:goDel('${cvo.cartNum}')">삭제</a>
		</td>
		</tr>
		</c:forEach>
		</c:if>
		<!-- --------------------------- -->
		<tr>
			<td colspan="4">
			<b>장바구니 총액</b>: 
			<span style="color:blue"><b>
		<fmt:formatNumber value="${cartTotal.cartTotalPrice}" pattern="###,###"/>원
		</b>
			</span><br>
			<b>총 포인트</b>:
			<span style="color:red"><b>${cartTotal.cartTotalPoint}</b></span>POINT
			</td>
			<td colspan="2">
		<button type="button" onclick="goOrder()"
		 class="btn btn-primary">주문하기</button>
		<button type="button"
			onclick="history.go(-2)"
		 class="btn btn-warning">계속쇼핑</button>
				
			</td>
		</tr>
	</tbody>
</table>
</form><!-- 주문form끝---------------------- -->


<!-- 수량 수정 form시작----------------------- -->
<form name="editF" action="cartEdit" method="POST">
	<input type="hidden" name="cartNum" >
	<input type="hidden" name="oqty">	
</form>	
<!--수량 수정 form끝-------------------------  -->
</div>
</div>
	</div>
<script>
	$(function(){
		//
		$('#allChk').click(function(){
			let chkList=$('input[name="opnum"]')
			if($(this).is(":checked")){
				chkList.each(function(i, ck){
					ck.checked=true;
				})
			}else{
				chkList.each(function(i, ck){
					ck.checked=false;
				})
			}
		})
		
	})//$() end--------------------


//체크박스에 체크한 상품정보(상품번호-opnum, 수량-oqty)를 가지고 주문폼 페이지로 이동
	function goOrder(){
		let chk=orderF.opnum; //$('input[name="opnum"]')
		if(chk==null) return;
		//alert(chk.length)
		//체크박스 1개일 때는 length속성이 안먹음
		if(!chk.length){
			//체크박스가 한 개 있다면=>checkbox객체
			if(!chk.checked){
				alert('주문할 상품을 체크하세요')
				chk.focus();
				return;
			}
		}else{
			//체크박스가 여러 개 있다면=>chk배열
			let cnt=0;
			for(let i=0;i<chk.length;i++){
				if(!chk[i].checked){
					cnt++;//체크안된 체크박스 개수 세기
					//체크안된 상품이 있다면
					//해당 상품의 수량이 서버에 전송되지 않도록 해야 한다.
					$('#oqty'+i).prop('disabled', true)
				}else{
					$('#oqty'+i).prop('disabled', false)
				}
			}//for----
			if(cnt==chk.length){
				alert('주문할 상품을 체크해야 해요');
				$('#oqty'+i).prop('disabled', false)
				return;
			}
			
		}//else----
		orderF.method='post';
		orderF.submit();
		
	}//-------------------------------


	function goEdit(cartNum, index){
		//alert(index);
		let qty=$('#oqty'+index).val();
		//alert(qty);
		editF.cartNum.value=cartNum;
		editF.oqty.value=qty;
		editF.submit();
	}//-----------------------


	function goDel(cartNum){
		//alert(cartNum);
		let yn=confirm("정말 삭제할까요?")
		if(yn){
			location.href="cartDel?cartNum="+cartNum;
		}
	}
</script>	
	
<c:import url="/foot"/>