<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*"%>


<%
	OrdService ordSvc = new OrdService();
    List<OrdVO> list = ordSvc.getAll();
    OrdVO ordVO = new OrdVO();
    pageContext.setAttribute("list",list);
%>
<html>
<head>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <li><a href="<%= request.getContextPath() %>/ord/ord/addOrd.jsp">新增訂單</a> a new Emp.</li>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>廠商編號</th>
		<th>卓位編號</th>
		<th>人數</th>
		<th>共同支付會員編號</th>
		<th>共同支付會員編號</th>
		<th>分攤金額</th>
		<th>訂單成立時間</th>
		<th>訂位日期</th>
		<th>訂位時間</th>
		<th>備註</th>
		<th>總金額</th>
		<th>開始用餐時間</th>
		<th>結束用餐時間</th>
		<th>驗證序號</th>
		<th>訂單狀態</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ordVO.ord_no}</td>
			<td>${ordVO.mem_no}</td>
			<td>${ordVO.vendor_no}</td>
			<td>${ordVO.tbl_no}</td>
			<td>${ordVO.party_size}</td>
			<td>${ordVO.share_mem_no1}</td> 
			<td>${ordVO.share_mem_no2}</td> 
			<td>${ordVO.share_amount}</td>
			<td>${ordVO.ord_time}</td> 
			<td>${ordVO.booking_date}</td> 
			<td>${ordVO.booking_time}</td> 
			<td>${ordVO.notes}</td> 
			<td>${ordVO.total}</td> 
			<td>${ordVO.arrival_time}</td> 
			<td>${ordVO.finish_time}</td> 
			<td>${ordVO.verif_code}</td> 
			<td>${ordVO.status}</td> 
			<td>
			  <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/ord/ord.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ord_no"  value="${ordVO.ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ord/ord.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ord_no"  value="${ordVO.ord_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>