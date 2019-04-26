<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.restaurant_menu.model.*,java.util.* "%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <% @SuppressWarnings("unchecked")
   Vector<Restaurant_MenuVO> buylist = (Vector<Restaurant_MenuVO>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<table id="table-1">
    <tr> 
      <th width="200">店名</th><th width="100">店家編號</th><th width="100">餐點</th><th width="100">價格</th>
      <th width="120">數量</th><th width="120">
    </tr></table><table>
    
    <%
	 for (int index = 0; index < buylist.size(); index++) {
		 Restaurant_MenuVO order = buylist.get(index);
	%>
	<tr>
		<td width="200">${param.vendor}</td>
		<td width="100"><%=order.getVendor_no()%></td>
		<td width="100"><%=order.getMenu_name()%></td>
		<td width="100"><%=order.getMenu_price()%></td>
		<td width="120"><%=order.getQuantity()%> </td>


 <td width="120">
          <form name="deleteForm" action="<%=request.getContextPath()%>/ord/ord.do" method="get">
              <input type="hidden" name="action"  value="DELETE_menu">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪 除" class="button">
          </form></td>
	</tr>
	<%}%>
</table>

<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/ord/ord.do" method="get">
              <input type="hidden" name="action"  value="checkout"> 
              <input type="submit" value="付款結帳" class="button">
          </form>
<%}%>

</body>
</html>