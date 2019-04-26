<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ page import="com.ord.model.*"%>
<%@ page import="com.restaurant_menu.model.*"%>
<%@ page import="com.vendor.model.*,java.util.* "%>


<!DOCTYPE html>
<html>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

body {
    margin-top: 20px;
}

</style>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <address>
                        <strong>Elf Cafe</strong>
                        <br>
                        2135 Sunset Blvd
                        <br>
                        Los Angeles, CA 90026
                        <br>
                        <abbr title="Phone">P:</abbr> (213) 484-6829
                    </address>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                    <p>
                        <em>Date: 1st November, 2013</em>
                    </p>
                    <p>
                        <em>Receipt #: 34522677W</em>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="text-center">
                    <h1>點餐明細</h1>
                </div>
                </span>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>餐點</th>
                            <th>數量</th>
                            <th class="text-center">單價</th>
                            <th class="text-center">總額</th>
                        </tr>
                    </thead>
                      <tbody>
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
                           <td class="col-md-9"><h4><em><%= menu_name %></em></h4></td>
                            <td class="col-md-1" style="text-align: center"> <%=quantity %> </td>
                            <td class="col-md-1 text-center"><%= menu_price %></td>
                            <td class="col-md-1 text-center"></td>
                        </tr>
                        <%
							}
						%>
                       
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td class="text-right"><h4><strong>Total: </strong></h4></td>
                            <td class="text-center text-danger"><h4><strong><%=amount%></strong></h4></td>
                        </tr>
                    </tbody>
                </table>
                <tr>
                	<td>
	                	<a href="<%=request.getContextPath()%>/ord/ord/ordfood.jsp" >
							<button class="btn btn-success btn-lg btn-block" >繼續點餐<span class="glyphicon glyphicon-shopping-cart"></span></button>
						</a>
					</td>
					<td>
		                <button type="button" class="btn btn-success btn-lg btn-block"> Pay Now<span class="glyphicon glyphicon-usd"></span>
		                </button>
		           </td>
                </tr>
            </div>
        </div>
    </div>
</div>
</body>
</html>