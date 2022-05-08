<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%-- <c:import url="/top" /> --%>

<script> 
   $(function() {
      
      //ajax 요청을 보내서 모든 카테고리 목록을 가져와서
      //id가 category인 곳에 출력
      $.ajax({
         type: 'get',
         url: 'cateAll',
         dateType: 'json',
         cache: false,
         success: function(res) {
            //alert(res);
            showDropMenu(res);
         },
         error: function(err) {
            alert('error: '+err.status);
         }
      });
   })
   
   function showDropMenu(res) {
      let str = ``;
      $.each(res, (i, item)=>{
    	 str+=`<div class="dropdown" >` 
         str+=`<button onclick='getItem("`+i+`","`+item.cg_num+`")' id='cateBtn`+i+`' class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">`;
         str+=item.cg_name;
         str+=`<span class="caret"></span>`;
         str+=`</button>`;
         str+=`</div>`
      });
      $('#category').html(str);
   }
   
   function getItem(i, cg_num) {
      //alert(i+"/"+cg_num);
      $.ajax({
         type: 'get',
         url: 'prodInfo?cg_num='+cg_num,
         dataType: 'json',
         cache: false,
         success:function(res) {
            //alert(res);
            if(res.length>0){
            	showMenuItem(res, i);
            }
         },
         error:function(err) {
            alert('error: '+err.status);
         }
      })
   }
   
   function showMenuItem(res, i) {
      //alert(i);
      let str = ``;
      str+=`<ul class="dropdown-menu">`;
      $.each(res, (k, prod)=> {
         str+=`<li><a href="${pageContext.request.contextPath}/prodDetail?pnum=`+prod.pnum+`">`+prod.pname+`</a></li>`;
      });
      
      str+=`</ul>`;
      //$('#cateBtn'+i).next().html(str); [x]
      $('#cateBtn'+i).after(str)      
      //alert(str);
   }
</script>



<div class="container" id="category" style="display:flex">

   <!--<div class="dropdown" >
       <button id="cateBtn" class="btn btn-primary dropdown-toggle"
         type="button" data-toggle="dropdown">
         카테고리명 <span class="caret"></span>
      </button>
      <ul class="dropdown-menu">
         <li><a href="#">상품명</a></li>
      </ul> 
   </div>
   -->

</div>
<%-- <c:import url="/foot" /> --%>