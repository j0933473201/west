<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.ord.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
OrdService ordSvc = new OrdService();
OrdVO ordVO = ordSvc.getOneOrd(request.getParameter("ord_no"));
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
	width: 600px;
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

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	
</table>

<table>
	<tr>
		<th>�q��s��</th>
		<th>�|���s��</th>
		<th>�t�ӽs��</th>
		<th>����s��</th>
		<th>�H��</th>
		<th>�@�P��I�|���s��</th>
		<th>�@�P��I�|���s��</th>
		<th>���u���B</th>
		<th>�q�榨�߮ɶ�</th>
		<th>�q����</th>
		<th>�q��ɶ�</th>
		<th>�Ƶ�</th>
		<th>�`���B</th>
		<th>�}�l���\�ɶ�</th>
		<th>�������\�ɶ�</th>
		<th>���ҧǸ�</th>
		<th>�q�檬�A</th>
		<td>�\��϶�</td>
	</tr>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="ord_no"  value="${ordVO.ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			
			
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ord/ord.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="ord_no"  value="${ordVO.ord_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			
			
			 
			   
			</td>
	</tr>
</table>

</body>
</html>