<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.restaurant_menu.model.*,java.util.* "%>
<!DOCTYPE html>
<html>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
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
		<div class="col-xs-8">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="row">
							<div class="col-xs-6">
							 <% @SuppressWarnings("unchecked")
							   Vector<Restaurant_MenuVO> buylist = (Vector<Restaurant_MenuVO>) session.getAttribute("shoppingcart");%>
							<%if (buylist != null && (buylist.size() > 0)) {%>
								<h5><span class="glyphicon glyphicon-shopping-cart"></span> ${param.vendor}</h5>
							</div>
							
						</div>
					</div>
				</div>
				<div class="panel-body">
				 <%
	 for (int index = 0; index < buylist.size(); index++) {
		 Restaurant_MenuVO order = buylist.get(index);
	%>
					<div class="row">
						<div class="col-xs-2"><img class="img-responsive" src="http://placehold.it/100x70">
						</div>
						<div class="col-xs-4">
							<h4 class="product-name"><strong><%=order.getMenu_name()%></strong></h4><h4><small>Product description</small></h4>
						</div>
						<div class="col-xs-6">
							<div class="col-xs-6 text-right">
								<h6><strong><%=order.getMenu_price()%> <span class="text-muted">x</span></strong></h6>
							</div>
							<div class="col-xs-4">
								<input type="text" class="form-control input-sm" value="<%=order.getQuantity()%>">
							</div>
							<div class="col-xs-2">
							 <form name="deleteForm" action="<%=request.getContextPath()%>/ord/ord.do" method="get">
				              <input type="hidden" name="action"  value="DELETE_menu">
				              <input type="hidden" name="del" value="<%= index %>">
								<button type="submit"class="btn btn-link btn-xs">
									<span class="glyphicon glyphicon-trash"> </span>
								</button>	
							</form>
								
							</div>
						</div>
					</div>
					<hr>
					<%}%>
					
					<div class="row">
						<div class="text-center">
							<div class="col-xs-9">
								<h6 class="text-right">Added items?</h6>
							</div>
							<div class="col-xs-3">
								<button type="button" class="btn btn-default btn-sm btn-block">
									Update cart
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row text-center">
						<div class="col-xs-9">
							<h4 class="text-right">Total <strong>$50.00</strong></h4>
						</div>
						<div class="col-xs-3">
						 <form name="checkoutForm" action="<%=request.getContextPath()%>/ord/ord.do" method="get">
              				<input type="hidden" name="action"  value="checkout"> 
							<input type="submit" value="付款結帳" class="btn btn-success btn-block">
							 </form>
						
						</div>
					</div>
				</div>
				<%}%>
			</div>
		</div>
	</div>
</div>

</body>
</html>