<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ page import="com.ord.model.*"%>
<%@ page import="com.restaurant_menu.model.*"%>
<%@ page import="com.vendor.model.*,java.util.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table id="table-1" style="margin: auto;">
	<tr>
		<th width="200">店名</th>
		<th width="100">店家編號</th>
		<th width="100">餐點</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"><h3>總價</h3></th>
	</tr></table>
	<table style="margin: auto;">
	
	<%  @SuppressWarnings("unchecked")
		Vector<Restaurant_MenuVO> buylist = (Vector<Restaurant_MenuVO>) session.getAttribute("shoppingcart");
		String amount =  (String) request.getAttribute("amount");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
		Restaurant_MenuVO menu = buylist.get(i);
		
			String vendor=menu.getMenu_no();
			System.out.print("vendor"+vendor);
			String name = menu.getVendor_no();
			String menu_name = menu.getMenu_name();
			String menu_price = menu.getMenu_price();
			Integer quantity = menu.getQuantity();
	%>
	
	<tr>
		<td width="200"><%= vendor %></td>
		<td width="100"><%= name %></td>
		<td width="100"><%= menu_name %></td>
		<td width="100"><%= menu_price %></td>
		<td width="120"><%=quantity %> </td>
	</tr>
	<%
		}
	%>
	
	<tr>
		<td colspan="6" style="text-align:right;"> 
		   <font size="+2">總金額： <h4>$<%=amount%></h4> </font>
	    </td>
	</tr>
</table>
	
	
	
<table id="table-1">
    <tr> 
      <th width="200">店名</th><th width="100">店家編號</th><th width="100">餐點</th><th width="100">價格</th>
      <th width="120">數量</th><th width="120">
    </tr></table><table style="margin: auto;">
    
<%--     <%  @SuppressWarnings("unchecked") --%>
// 		Vector<Restaurant_MenuVO> buylist = (Vector<Restaurant_MenuVO>) session.getAttribute("shoppingcart");
// 		String amount =  (String) request.getAttribute("amount");
<%-- 	%>	 --%>
<%-- 	<%	for (int i = 0; i < buylist.size(); i++) { --%>
// 		Restaurant_MenuVO menu = buylist.get(i);
		
// 			String vendor=menu.getMenu_no();
// 			System.out.print("vendor"+vendor);
// 			String name = menu.getVendor_no();
// 			String menu_name = menu.getMenu_name();
// 			String menu_price = menu.getMenu_price();
// 			Integer quantity = menu.getQuantity();
<%-- 	%> --%>
	
<!-- 	<tr> -->
<%-- 		<td width="200"><%= vendor %></td> --%>
<%-- 		<td width="100"><%= name %></td> --%>
<%-- 		<td width="100"><%= menu_name %></td> --%>
<%-- 		<td width="100"><%= menu_price %></td> --%>
<%-- 		<td width="120"><%=quantity %> </td> --%>
<!-- 	</tr> -->
<%-- 	<% --%>
// 		}
<%-- 	%> --%>
	 
	
<!-- 	<tr> -->
<!-- 		<td colspan="6" style="text-align:right;">  -->
<%-- 		   <font size="+2">總金額： <h4>$<%=amount%></h4> </font> --%>
<!-- 	    </td> -->
<!-- 	</tr> -->
<!-- </table> -->
       
       
       <p><a href="/ord/ord/ordfood.jsp"><font size="+1"> 是 否 繼 續 購 物</font></a>
	
    
    


</body>
</html>