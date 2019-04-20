<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ord.model.*" %>

<% 
OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
%>
<!DOCTYPE html>
<script  src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<html lang="en">
<head>

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

<!-- jQuery, Bootstrap JS. -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- 貓頭鷹 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- 以下是 自訂的東西 -->
<!-- 線條樣式 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/ord/css/simple-line-icons.css">
<!-- Icon資源池 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/ord/css/themify-icons.css">
<!-- 動態效果 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/ord/css/set1.css">
<!-- 自訂 CSS主檔 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/ord/css/style.css">


<title>SeekFoodTable</title>

</head>
<style type="text/css">
ul, ol, dl {
	list-style-type: none
}

img {
	border: none;
}

.clearfix {
	*zoom: 1;
}

.clearfix:before, .clearfix:after {
	display: table;
	line-height: 0;
	content: "";
}

.clearfix:after {
	clear: both;
}

.p20 {
	padding: 20px;
}

.mb20 {
	margin-bottom: 20px;
}

.mr20 {
	margin-right: 20px;
}

.fl {
	float: left;
	display: block;
}

.fr {
	float: right;
	display: block;
}

#score {
	font-size: 30px;
}

#score .tit img {
	height: 30px;
	width: 30px;
	margin-top: 6px;
}

#score dd {
	width: 40px;
	height: 40px;
	background: url(http://www.5imoban.net/view/demoimg/star2.png) no-repeat
		center;
	margin-left: 15px;
}

#score dd.on {
	background-image:
		url(http://www.5imoban.net/view/demoimg/star2_checked.png);
}

body {
	margin-top: 40px;
}

.stepwizard-step p {
	margin-top: 10px;
}

.stepwizard-row {
	display: table-row;
}

.stepwizard {
	display: table;
	width: 100%;
	position: relative;
}

.stepwizard-step button[disabled] {
	opacity: 1 !important;
	filter: alpha(opacity = 100) !important;
}

.stepwizard-row:before {
	top: 14px;
	bottom: 0;
	position: absolute;
	content: " ";
	width: 100%;
	height: 1px;
	background-color: #ccc;
	z-order: 0;
}

.stepwizard-step {
	display: table-cell;
	text-align: center;
	position: relative;
}

.btn-circle {
	width: 30px;
	height: 30px;
	text-align: center;
	padding: 6px 0;
	font-size: 12px;
	line-height: 1.428571429;
	border-radius: 15px;
}

.star-vote {
	width: 120px;
	height: 20px;
	position: relative;
	overflow: hidden;
}

.star-vote>span {
	position: absolute;
	width: 120px;
	height: 20px;
	background-size: cover;
	background-repeat: no-repeat;
	left: 0px;
	top: 0px;
}

.star-vote>.add-star {
	background-image: url("/images/startfull.png");
}

.star-vote>.del-star {
	background-image: url("images/startempty.png");
	background-color: white;
}

.animated {
	-webkit-transition: height 0.2s;
	-moz-transition: height 0.2s;
	transition: height 0.2s;
}

.stars {
	margin: 20px 0;
	font-size: 24px;
	color: #d17581;
}

.card-inner {
	margin-left: 4rem;
}

#hotp1 {
	height: 300px;
	object-fit: cover;
}
</style>
<body>
	<!--============================= HEADER =============================-->
	${ordVO}
	

	<!-- ==============================自己的div start====================== -->


	<div>
		<h1>HelloWorld!</h1>
	</div>


	<!-- ==============================自己的div end====================== -->



	<section class="main-block">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="styled-heading"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="find-place-img_wrap">
						<div class="grid">
							<figure class="effect-ruby">
								<img src="<%= request.getContextPath() %>/ord/images/zachariah-hagy-484664-unsplash.jpg"
									class="img-fluid" alt="img13" id="hotp1" />
								<figcaption>
									<h5></h5>

								</figcaption>
							</figure>
						</div>
					</div>
				</div>


				<div class="col-md-4">
					<div class="find-place-img_wrap">
						<div class="grid">
							<figure class="effect-ruby">
								<img src="<%= request.getContextPath() %>/ord/images/zachariah-hagy-484664-unsplash.jpg"
									class="img-fluid" alt="img13" id="hotp2" />
								<figcaption>
									<h5></h5>

								</figcaption>
							</figure>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="find-place-img_wrap">
						<div class="grid">
							<figure class="effect-ruby">
								<img src="<%= request.getContextPath() %>/ord/images/zachariah-hagy-484664-unsplash.jpg"
									class="img-fluid" alt="img13" id="hotp3" />
								<figcaption>
									<h5></h5>

								</figcaption>
							</figure>
						</div>
					</div>
				</div>
			</div>
			 
     
		</div>
	</section>
	<ul class="nav nav-tabs justify-content-center" id="myTab"
		role="tablist">
		<li class="nav-item"><a class="nav-link active" id="home-tab"
			data-toggle="tab" href="#home" role="tab" aria-controls="home"
			aria-selected="true">訂位</a></li>
		<li class="nav-item"><a class="nav-link" id="profile-tab"
			data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
			aria-selected="false">資訊</a></li>
		<li class="nav-item"><a class="nav-link" id="contact-tab"
			data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
			aria-selected="false">評論</a></li>
	</ul>

		<div class="container" style="margin-top: 80px">
		          <div class="stepwizard">
		              <div class="stepwizard-row setup-panel">
		                  <div class="stepwizard-step">
		                      <a href="#step-1" type="button" class="btn btn-primary btn-circle">1</a>
		                      <p>Step1 訂位</p>
		                  </div>
		                  <div class="stepwizard-step">
		                      <a href="#step-2" type="button" class="btn btn-default btn-circle">2</a>
		                      <p>Step2 訂餐</p>
		                  </div>
		                  <div class="stepwizard-step">
		                      <a href="#step-3" type="button" class="btn btn-default btn-circle" >3</a>
		                      <p>Step3 結帳</p>
		                  </div>
		              </div>
		          </div>
		      </div>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">

				<!-- 訂位流程 -->
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="home" role="tabpanel"
						aria-labelledby="home-tab">
						...
						<!-- 錯誤表列 -->
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<form id="form1" action="<%= request.getContextPath() %>/ord/ord.do" method="get">
						<input type="hidden" name="action" id="action" value="updateDate"> 
							
							
						<tr>
			<td>用餐日期</td>
			<td><input name="booking_date" id="f_date1" type="text"
				value="${ordVO.booking_date}"></td>
		</tr>
		<input type="hidden" name="vendor_no" value="${param.vendor_no}">
		

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
						<input class="btn btn-primary" type="hidden" name="booking_time"
							value="${reservation_TimeVO.r_time}">
					</c:forEach>

				</div>
			</div>


		</div>	
		</form>




								<!-- form表單開始 -->
								<jsp:useBean id="now" scope="page" class="java.util.Date"/>
						<FORM METHOD="get" ACTION="<%= request.getContextPath() %>/ord/ord.do" name="form2">
						<div class="row justify-content-center" style="margin-top: 50px">
							<div class="col-md-6">
								<input type="hidden" name="mem_no" value="M000004">
<!-- 								<input type="hidden" name="vendor_no" value="V000004"> -->
								<input type="hidden" name="tbl_no" value="T000001">
								<input type="hidden" name="share_mem_no1" value="${ordVO.share_mem_no1}">
								<input type="hidden" name="share_mem_no2" value="${ordVO.share_mem_no2}">
								<input type="hidden" name="share_amount" value="0">
<!-- 							 	<input type="hidden" name="booking_time" value="12:30">	  -->
								<input type="hidden" name="ord_time" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>">	
								
								<input type="hidden" name="total" value="1000">
								<input type="hidden" name="arrival_time" value="${ordVO.arrival_time}">
								<input type="hidden" name="finish_time" value="${ordVO.finish_time}">
								<input type="hidden" name="verif_code" value="8JPXY6wQc5bvrN2y6h4h">
								<input type="hidden" name="status" value="3">
								<input type="hidden" name="vendor_no" value="${param.vendor_no}">
									<tr>
<!-- 										<td>用餐日期</td> -->
										<td><input name="booking_date" id="f_date2" type="hidden" value="${param.booking_date}"
											></td>
									</tr>

								</div>
						</div>

						<div class="row justify-content-center" style="margin-top: 50px">
							<div class="col-md-9">
								<form class="form-inline" >
								
							<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
							
									<div class="select1" type="hidden">
										人數選擇 
											<select  type="hidden"class="custom-select my-1 mr-sm-4"
											id="inlineFormCustomSelectPref"  style="width: 500px;" name="party_size" id="party_sizeid">
											
											<option  name="2" value="2">二人</option>
											<option  name="3" value="3">三人</option>
											<option  name="4" value="4">四人</option>
											<option  name="5" value="5">五人</option>
											<option  name="6" value="6">六人</option>
											<option  name="7" value="7">七人</option>
											<option  name="8" value="8">八人</option>
											<option  name="9" value="10">十人</option>
											
										</select>
									</div>
							</div>
						</div>

				<div class="row justify-content-center"  style="margin-top:50px">
					<div class="col-md-9" >
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								id="inlineCheckbox1"  name="total"value="100"> <label
								class="form-check-label" for="inlineCheckbox1">大俠愛吃漢堡包</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								id="inlineCheckbox2" name="total"value="400"> <label
								class="form-check-label" for="inlineCheckbox2">豆腐三重奏</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								id="inlineCheckbox3" name="total"value="300"> <label
								class="form-check-label" for="inlineCheckbox3">黃金開口笑</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								id="inlineCheckbox4" name="total"value="200"><label
								class="form-check-label" for="inlineCheckbox4">彗星炒飯</label>
						</div>
						
					</div>
							
					</div>
					<div class="note" style="margin-top:50px">
						<tr>
								<td>備註:</td>
								<td><input type="TEXT" name="notes" size="70"  
										 value="" />
								</td>
						</tr>
					</div>
				</div>
				</div>
				
				

						<div class="container" style="margin-top: 50px">
							<div class="col-md-12">
								<div class=" btn-group-toggle" data-toggle="buttons"
									style="text-align: center;">
<!-- 									<button class="btn btn-primary" type="button" -->
<!-- 									name="booking_time" value="11:00" -->
<!-- 										style="background-color: #dc8f33;">11:00</button> 	 -->
<!-- 									<button class="btn btn-primary" type="button" -->
<!-- 									name="booking_time" value="14:00" -->
<!-- 										style="background-color: #dc8f33;">14:00</button> -->
<!-- 									<button class="btn btn-primary" type="button" -->
<!-- 									name="booking_time" value="18:00" -->
<!-- 										style="background-color: #dc8f33;">18:00</button> -->
<!-- 									<button class="btn btn-primary" type="button" -->
<!-- 									name="booking_time" value="19:00" -->
<!-- 										style="background-color: #dc8f33;">19:00</button> -->
<!-- 									<button class="btn btn-primary" type="button" -->
<!-- 									name="booking_time" value="11:00" -->
<!-- 										style="background-color: #dc8f33;">18:00</button> -->
									
				
					<c:forEach var="reservation_TimeVO" items="${rev_tSvc.getVendor(param.vendor_no)}">		 															
				 	 <input class="btn btn-primary" type="button"  name="booking_time" value="${reservation_TimeVO.r_time}" >										
				 	</c:forEach>
										
								</div>
							</div>
				
							
						</div>

				


						<div class="row justify-content-center" style="margin-top: 50px">
							<div class="col-md-4">
								<div class="featured-btn-wrap">
									<input type="hidden" name="action" value="insert">
									<input type="submit" value="送出新增"></FORM>
								</div>
							</div>
						</div>


					</div>
					<div class="tab-pane fade" id="profile" role="tabpanel"
						aria-labelledby="profile-tab">
						...
						<div class="row justify-content-center">
							<div class="col-md-6">
								<div class="row">
								
									<div class="col-md-12">
										<div class="find-place-img_wrap">
											<div class="grid">
												<figure class="effect-ruby">
													<img src="images/zachariah-hagy-484664-unsplash.jpg"
														class="img-fluid" alt="img13" id="hotp1" />
													<figcaption>
														<h5></h5>
					
													</figcaption>
												</figure>
											</div>
										</div>
									</div>
								<dl data-v-2ee1f21e="" class="info-list reset-list">
									<dt data-v-2ee1f21e="">餐廳位置</dt>
									<hr>
									<dd data-v-2ee1f21e="">台北信義新天地 A11 2F</dd>
									<hr>
									<dt data-v-2ee1f21e="">料理類型</dt>
									<hr>
									<dd data-v-2ee1f21e="">美式</dd>
									<!---->
									<hr>
									<dt data-v-2ee1f21e="">店家電話</dt>
									<hr>
									<dd data-v-2ee1f21e="">
										<a data-v-2ee1f21e="" href="tel:02-8786-7588">02-8786-7588</a>
									</dd>
									<hr>
									<dt data-v-2ee1f21e="">營業時間</dt>
									<hr>
									<dd data-v-2ee1f21e="">
										
										11:00-00:00
									</dd>
									<hr>
									<dt data-v-2ee1f21e="">可接受付款方式</dt>
									<hr>
									<dd data-v-2ee1f21e="">現金、禮券(禮券、商品禮券、電子券類、餘代卡)、信用卡 (VISA、
										MASTERCARD、 JCB、 AE、銀聯卡、晶片金融卡、Apple Pay 、Android Pay、Samsung
										Pay )、LINE Pay 、微信支付、支付寶</dd>
									<hr>
								</dl>

							</div>
						</div>
					</div>
					</div>
					<div class="tab-pane fade" id="contact" role="tabpanel"
						aria-labelledby="contact-tab">
						...
						<div class="container">
						    <div class="row">
						        <div class="col-12 ">
						
						<div class="col-md-12 " >
							<h1 class="rating-num">4.0</h1>
							<div class="rating">
								<span class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star-empty"></span>
							</div>
							<div>
								<span class="glyphicon glyphicon-user"></span>1,050,008 total
							</div>

							<div class="col-md-6" style="margin-top: 100px">
								<div class="row rating-desc">
									<div class="col-xs-3 col-md-3 text-right">
										<span class="glyphicon glyphicon-star"></span>5
									</div>
									<div class="col-xs-8 col-md-9">
										<div class="progress progress-striped">
											<div class="progress-bar progress-bar-success"
												role="progressbar" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100" style="width: 80%">
												<span class="sr-only">80%</span>
											</div>
										</div>
									</div>
									<!-- end 5 -->
									<div class="col-xs-3 col-md-3 text-right">
										<span class="glyphicon glyphicon-star"></span>4
									</div>
									<div class="col-xs-8 col-md-9">
										<div class="progress">
											<div class="progress-bar progress-bar-success"
												role="progressbar" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100" style="width: 60%">
												<span class="sr-only">60%</span>
											</div>
										</div>
									</div>
									<!-- end 4 -->
									<div class="col-xs-3 col-md-3 text-right">
										<span class="glyphicon glyphicon-star"></span>3
									</div>
									<div class="col-xs-8 col-md-9">
										<div class="progress">
											<div class="progress-bar progress-bar-info"
												role="progressbar" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100" style="width: 40%">
												<span class="sr-only">40%</span>
											</div>
										</div>
									</div>
									<!-- end 3 -->
									<div class="col-xs-3 col-md-3 text-right">
										<span class="glyphicon glyphicon-star"></span>2
									</div>
									<div class="col-xs-8 col-md-9">
										<div class="progress">
											<div class="progress-bar progress-bar-warning"
												role="progressbar" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100" style="width: 20%">
												<span class="sr-only">20%</span>
											</div>
										</div>
									</div>
									<!-- end 2 -->
									<div class="col-xs-3 col-md-3 text-right">
										<span class="glyphicon glyphicon-star"></span>1
									</div>
									<div class="col-xs-8 col-md-9">
										<div class="progress">
											<div class="progress-bar progress-bar-danger"
												role="progressbar" aria-valuenow="80" aria-valuemin="0"
												aria-valuemax="100" style="width: 15%">
												<span class="sr-only">15%</span>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div id="score" class="p20">
								<div class="tit clearfix"></div>
								<ul class="p20">
									<li class="clearfix mb20">
										<div class="fl"></div>
										<dl class="fl clearfix">
											<dd class="on fl"></dd>
											<dd class="on fl"></dd>
											<dd class="on fl"></dd>
											<dd class="on fl"></dd>
											<dd class="on fl"></dd>
										</dl>
									</li>

								</ul>
							</div>

							<ul class="list-unstyled">
								<li class="media"><img src="images/4809.jpg" class="mr-3"
									alt="...">
									<div class="media-body">
										<h5 class="mt-0 mb-1">東西難吃</h5>
										Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
										scelerisque ante sollicitudin. Cras purus odio, vestibulum in
										vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
										nisi vulputate fringilla. Donec lacinia congue felis in
										faucibus.
									</div></li>
								<hr>
								<li class="media my-4"><img src="images/4809.jpg"
									class="mr-3" alt="...">
									<div class="media-body">
										<h5 class="mt-0 mb-1">裝潢漂亮</h5>
										Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
										scelerisque ante sollicitudin. Cras purus odio, vestibulum in
										vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
										nisi vulputate fringilla. Donec lacinia congue felis in
										faucibus.
									</div></li>
								<li class="media"><img src="images/4809.jpg" class="mr-3"
									alt="...">
									<div class="media-body">
										<h5 class="mt-0 mb-1">氣憤好</h5>
										Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
										scelerisque ante sollicitudin. Cras purus odio, vestibulum in
										vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
										nisi vulputate fringilla. Donec lacinia congue felis in
										faucibus.
									</div></li>
								<hr>
								<li class="media"><img src="images/4809.jpg" class="mr-3"
									alt="...">
									<div class="media-body">
										<h5 class="mt-0 mb-1">服務差</h5>
										Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
										scelerisque ante sollicitudin. Cras purus odio, vestibulum in
										vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
										nisi vulputate fringilla. Donec lacinia congue felis in
										faucibus.
									</div></li>
								<hr>
							</ul>

						</div>
						</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


















	<!--============================= FOOTER =============================-->
		<!--//END FOOTER -->

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<!-- 貓頭鷹 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

	<!-- 日期的ＪＳ -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script>
	$.datetimepicker.setLocale('zh');
    $('#f_date1').datetimepicker({
       theme: '',              //theme: 'dark',
       timepicker:false,       //timepicker:true,
       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
       //format:'Y-m-d',         
       format:'Y-m-d',
       value:   'new Date()',
    });
	</script>
	


	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="http://www.5imoban.net/download/jquery/jquery-1.8.3.min.js"></script>

	<%--  window.onload=showStar(7);
            
            //n表示后台获取的星数
            function showStar(n){
              var del_star=document.getElementById("del_star");
                var con_wid=document.getElementById("star_con").offsetWidth;
                
                console.log(con_wid+"??");
                console.log(del_star+"00");
                
                //透明星星移动的像素
                var del_move=(n*con_wid)/10;
                
                del_star.style.backgroundPosition=-del_move+"px 0px";
                del_star.style.left=del_move+"px";
            }; --%>

	
	<script>
		$(function() {
			//评分
			$("#score li dd").click(
					function() {
						var index = $(this).index();
						$(this).parent("dl").children("dd").removeClass("on");
						var i = index + 1;
						$(this).parent("dl").children("dd:lt(" + i + ")")
								.addClass("on");
					})
		})
	</script>


	
	
</body>
</html>
