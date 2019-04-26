
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.*"%>
<%@page import="com.restaurant_menu.model.*"%>

<jsp:useBean id="vVO" scope="session" class="com.vendor.model.VendorVO" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<meta charset="UTF-8">
<title>廠商後台-菜單管理</title>

<style>
img{
max-width:300px;
}
.resTitle {
	font-size: 30px;
	margin-left: 5%;
}

body {
	background-color: #eee;
	font-family: "微軟正黑體";
}

.col-4, .col-6{
 margin-top:1%;
}
</style>



</head>
<body>



<div class="container">
  <div class="row">
    <div class="col-12">
<%-- <button type="button" class="btn btn-warning justify-content-end" onclick="location.href='<%=request.getContextPath()%>/Restaurant_Menu/addMenu.jsp'">新增菜色</button> --%>
<button type="button" class="btn btn-warning justify-content-end" data-toggle="modal" data-target="#menuCenter">新增菜色</button>
</div>
</div>
</div>

<!-- Modal**************************************************************************** -->
<div class="modal fade" id="menuCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
     <div class="modal-header">
              
                <label id="topicBar" class="col-12">菜單新增畫面</label>
            </div>
			
			<div class="modal-body">
<!-- ========================================BODY開始========================================== -->
              

<table id="table-1" data-toggle="table"
  data-url="data1.json"
  data-pagination="true"
  data-search="true">

</table>

<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<%-- 錯誤表列 --%>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Restaurant_Menu/Restaurant_Menu.do" name="form1" enctype="multipart/form-data" >
<table>
<!-- 	<tr> -->
<!-- 		<td>品項編號:<font color=red><b>*</b></font></td> -->
<%-- 		<td>${rmVO.menu_no}</td> --%>
<!-- 	</tr> -->

<!-- 	<tr> -->
<!-- 		<td>廠商編號:</td> -->
<%-- 		<td><input type="TEXT" name="vendor_no" size="45" value="${rmVO.vendor_no}" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>品名:</td>
		<td><input type="TEXT" name="menu_name" size="45"	value="${rmVO.menu_name}" /></td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="menu_pic" onchange="changePic(event)"></td>
	</tr>
	<tr>
		<td>價格:</td>
		<td><input type="TEXT" name="menu_price" size="45"	value="${rmVO.menu_price}" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
<%-- 		<td><input type="TEXT" name="menu_stat" size="45"	value="${rmVO.menu_stat}" /></td> --%>
		<td>
		<select name="menu_stat">
		　<option value="1">上架</option>
		　<option value="2" selected>下架</option>
		　<option value="3">審核中</option>
		　<option value="4">黑名單</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>說明:</td>
		<td><input type="TEXT" name="menu_text" size="45"	value="${rmVO.menu_text}" /></td>
	</tr>
	
</table>
<input type="hidden" name="action" value="insertOneFood">
<input type="hidden" name="vendor_no" value="${vendor_no}">
<%-- <input type="hidden" name="menu_no" value="${rmVO.menu_no}"> --%>
<input type="submit" value="送出"></FORM>
<img src="" alt="" id="upimg">
              
              
              
<!-- =========================================BODY結束========================================== -->
			</div>
			
			<div class="modal-footer">
           
            </div>
      </div>
    </div>
  </div>
</div>

<!-- Modal結束*******************************************************  -->

<div class="container">
<div class="row">


<c:forEach var="rmVO" items="${rmlist}">


<div class="col-4">

 <div class="profile-img"><img id="p${rmVO.menu_no}" src="<%= request.getContextPath()%>/ShowImg.do?menu_no='${rmVO.menu_no}'"/></div>
 
 </div>     
  <div class="col-7"> 
  <h3>${rmVO.menu_name}<span>$${rmVO.menu_price}</span></h3>
   <span>${rmVO.menu_text}</span>
   <div class="btn-group align-self-end align-items-end" role="group" aria-label="Basic example">
      <button type="button" class="btn btn-secondary pic" id="xx${rmVO.menu_no}">換圖</button>
      <button type="button" class="btn btn-secondary edit">編輯</button>
      <button type="button" class="btn btn-secondary updown">下架</button>
    </div>

</div>
      

</c:forEach>


</div>
 </div>






<c:forEach var="rmVO" items="${rmlist}">

<script type="text/javascript">
$("#xx${rmVO.menu_no}").click(async function(event) {
    
	const {value: file} = await Swal.fire({
	  title: '請選擇圖片',
	  input: 'file',
	  inputAttributes: {
	    'accept': 'image/*',
	    'aria-label': 'Upload your profile picture'
	  }
	})
	if (file) {
	  const reader = new FileReader
	  reader.onload = (e) => {
	    Swal.fire({
	      title: '圖片已變更為',
	      imageUrl: e.target.result,
	      imageAlt: 'The uploaded picture'
	      
	   
	    }).then(function(file){
	    	$.ajax({
	    		url: "<%=request.getContextPath()%>/Restaurant_Menu/Restaurant_Menu.do",
	            type : 'post',
				data: { action: 'upMenu',ile: e.target.result , menu_no:'${rmVO.menu_no}'},
				dataType: 'json',
				async : false,//同步請求
				cache : false,//不快取頁面
				
	    	})
	    })
	    $('#p${rmVO.menu_no}').attr('src', e.target.result);
	  }
	  
	  reader.readAsDataURL(file)
	}
	})


</script>

</c:forEach>
<!-- 秀圖的JS裝置 -->
<script type="text/javascript">
	function changePic(e) {		
		
		document.getElementsByTagName("img")[0].src = URL.createObjectURL(event.target.files[0]); 		
	}
</script>
</body>
</html>