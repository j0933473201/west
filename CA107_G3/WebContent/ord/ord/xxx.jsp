<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ord.model.*" %>
<% 
OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		alert("....");
		$('#inlineFormCustomSelectPref').change(function() {
		
			$('#form1').submit();
		})
		$('#f_date1').change(function() {
			$('#form1').submit();
		})
	})
</script>

</head>
<body>
				<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />			 
	<form id="form1" action="<%= request.getContextPath() %>/ord/servletRes.do" method="get">
		<input type="hidden" name="action" id="action" value="updateDate"> 
<!-- 		<input type="hidden" name="tbl_no" value="T000001">  -->
<%-- 		<input type="hidden" name="share_mem_no1" value="${ordVO.share_mem_no1}"> --%>
<%-- 		<input type="hidden" name="share_mem_no2" value="${ordVO.share_mem_no2}">  --%>
<!-- 		<input type="hidden" name="share_amount" value="0">  -->
<!-- 		<input type="hidden" name="booking_time" value="12:30">  -->
<%-- 		<input type="hidden" name="ord_time" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"> --%>

		<tr>
			<td>用餐日期</td>
			<td><input name="booking_date" id="f_date1" type="text"
				value="${ordVO.booking_date}"></td>
		</tr>

		

		<div class="select1">
			人數選擇 <select class="custom-select my-1 mr-sm-4"
				id="inlineFormCustomSelectPref" style="width: 500px;"
				name="party_size">

				<option name="2" value="2">二人</option>
				<option name="3" value="3">三人</option>
				<option name="4" value="4">四人</option>
				<option name="5" value="5">五人</option>
				<option name="6" value="6">六人</option>
				<option name="7" value="7">七人</option>
				<option name="8" value="8">八人</option>
				<option name="9" value="10">十人</option>

			</select>
		</div>


		<div class="container" style="margin-top: 50px">
			<div class="col-md-12">
				<div class=" btn-group-toggle" data-toggle="buttons"
					style="text-align: center;">

					<jsp:useBean id="rev_tSvc" scope="page"
						class="com.reservation_time.model.Reservation_TimeService" />

					<c:forEach var="reservation_TimeVO"
						items="${rev_tSvc.getVendor(param.vendor_no)}">
						<input class="btn btn-primary" type="button" name="booking_time"
							value="${reservation_TimeVO.r_time}">
					</c:forEach>

				</div>
			</div>


		</div>
	</form>
	<!-- 		班別： 年級： -->
	<!-- 	<select id="grade"> -->
	<!-- 		<option value="-1">請選擇</option> -->
	<!-- 		<option value="grade3">三年級</option> -->
	<!-- 		<option value="grade2">二年級</option> -->
	<!-- 		<option value="grade1">一年級</option> -->
	<!-- 	</select> -->
	<!-- 	班別： -->
	<!-- 	<select id="class"> -->
	<!-- 		<option value="-1">請選擇</option> -->
	<!-- 	</select> -->
	<!-- 	姓名: -->
	<!-- 	<select id="name"> -->
	<!-- 		<option value="-1">請選擇</option> -->
	<!-- 	</select> -->
	

</body>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script  src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		//format:'Y-m-d',         
		format : 'Y-m-d',
		value : 'new Date()',
	});
</script>
</html>