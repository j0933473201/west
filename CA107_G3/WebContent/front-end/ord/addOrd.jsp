<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="com.vendor.model.*" %>

<%
	
%>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>

<html lang="en">
<head>


<script 
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC3teApdwmpN2yUfc6dftcDkHw1dLpV2B4&callback=initMap"></script>

<!-- 提交FORM表單 -->
<script type="text/javascript">
	$(document).ready(function() {
// 		$('.date #f_date1').change(function() {
// 			$('#form1').submit();
// 		})
		$('#inlineFormCustomSelectPref').change(function() {

			$('#form1').submit();
		})
		
	})
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>

  <script>

    $(function(){
    // 取得使用者輸入的地址
      var addr = $('#addr').val();
      // 建立 Geocoder() 物件
      var gc = new google.maps.Geocoder();
   // 用使用者輸入的地址查詢
      gc.geocode({'address': addr}, function(result, status){
        // 確認 OK
        if(status == google.maps.GeocoderStatus.OK) {
          var latlng = result[0].geometry.location;
          // 將查詢結果設為地圖的中心
          var LAT=latlng.lat(); //顯示經度
          var LNG=latlng.lng(); //顯示緯度
          
          var position = {
              lat: LAT,
              lng: LNG
            };
          
          var mymap = new google.maps.Map($('#map').get(0), {
              zoom: 15,
              center: {lat:LAT , lng:LNG}
    });
          
          var marker = new google.maps.Marker({
           position: position,
           map: mymap,
           animation: google.maps.Animation.BOUNCE
         });
          
        }
      }); 
   
    });
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/ord/css/simple-line-icons.css">
<!-- Icon資源池 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/ord/css/themify-icons.css">
<!-- 動態效果 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/ord/css/set1.css">
<!-- 自訂 CSS主檔 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/ord/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

 <!-- 星星圖庫來源樣式 -->
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../front-end/css/starrr.css">
<!-- 評論區樣式 -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/> -->


<title>餐廳首頁</title>
<jsp:useBean id="res_tboSvc" scope="page" class="com.reservation_table_ordered.model.Reservation_Table_OrderedService" />
<jsp:useBean id="rev_tSvc" scope="page" class="com.reservation_time.model.Reservation_TimeService" />
<jsp:useBean id="now" scope="page" class="java.util.Date" />
<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<jsp:useBean id="exc_dateSvc" scope="page" class="com.exception_date.model.Exception_DateService" />
<jsp:useBean id="commentSvc" scope="page" class="com.comment.model.CommentsService" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

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
.nav-link {
    margin-inline-start: 100px;
    margin-right: 100px;
}
</style>
<body onload="connect();" onunload="disconnect();">

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
								<img
									src="<%=request.getContextPath()%>/ord/images/zachariah-hagy-484664-unsplash.jpg"
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
								<img
									src="<%=request.getContextPath()%>/ord/images/zachariah-hagy-484664-unsplash.jpg"
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
								<img
									src="<%=request.getContextPath()%>/ord/images/zachariah-hagy-484664-unsplash.jpg"
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
	
		
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-12">
				<ul class="nav nav-tabs justify-content-center" id="myTab"
					role="tablist">
					
					<li class="nav-item"><a class="nav-link" id="profile-tab"
						data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
						aria-selected="true">資訊</a></li>
					<li class="nav-item"><a class="nav-link " id="home-tab"
						data-toggle="tab" href="#home" role="tab" aria-controls="home"
						aria-selected="false">訂位</a></li>
					<li class="nav-item"><a class="nav-link" id="contact-tab"
						data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
						aria-selected="false">評論</a></li>
				</ul>
			</div>
		</div>
	</div>

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
					<a href="#step-3" type="button" class="btn btn-default btn-circle">3</a>
					<p>Step3 結帳</p>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-12">
	
				<div class="tab-content" id="myTabContent">
				  <div class="tab-pane fade  " id="home" role="tabpanel" aria-labelledby="home-tab">...
				  	<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>	
<!-- 					  自動提交FORM開頭 -->
				<div>
					<form id="form1" action="<%=request.getContextPath()%>/ord/ord.do" method="get">
						<input type="hidden" name="action" id="action" value="updateDate">
						<input type="hidden" name="vendor_no" value="${param.vendor_no}">
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-8x">
										<div class="date" >	
											用餐日期<input name="booking_date" id="f_date1" type="text"
														value="${ordVO.booking_date}"  style="width: 400px;">
												
										</div>
									</div>
								</div>
							</div>
					<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-9">
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
					</div>
					</div>
						
				</form>
<!-- 				自動提交結束 -->
		</div>
		<div class="container" >
			<div class="row justify-content-center">
				<div class="col-md-8">
					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/ord/ord.do" id="form2">
						<input type="hidden" name="mem_no" value="M000004">
						<input type="hidden" name="vendor_no" value="${param.vendor_no}">
						<input type="hidden" name="tbl_no" value="T000001"> 
						<input type="hidden" name="share_mem_no1" value="${ordVO.share_mem_no1}"> 
						<input type="hidden" name="share_mem_no2" value="${ordVO.share_mem_no2}">
						<input type="hidden" name="share_amount" value="0">
<!-- 					<input type="hidden" name="booking_time" value="12:30">	  -->
						<input type="hidden" name="ord_time"
								value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>">
	
						
						<input type="hidden" name="arrival_time" value="${ordVO.arrival_time}"> 
						<input type="hidden" name="finish_time" value="${ordVO.finish_time}"> 
						<input type="hidden" name="status" value="0"> 
						<input type="hidden" name="booking_date" value="${param.booking_date}">		
						<input type="hidden" name="party_size" value="${param.party_size}">		
						
						<div class=" btn-group-toggle" data-toggle="buttons" id="btngp">
									
										<c:forEach var="exc" items="${lhs}">	
										<input class="btn2 btn-primary" type="button" id="xx${exc.booking_time}"  value="${exc.booking_time}" onclick="sendMessage();" >

										</c:forEach>	
								</div>
						
			 	 <input type="hidden" name="booking_time"  id="realyvalue"value="">  		 
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-md-10">			
								<table>	
									<tr>
										<td>備註:</td>
										<td><input type="TEXT" name="notes" size="70" value="" />
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-6">			
								<input type="hidden" name="action" value="sel_time"> 
<!-- 								<input type="submit" value="送出新增"> -->
								</div>
							</div>
						</div>
					</FORM>
<%-- 				<form id="form3" action="<%=request.getContextPath()%>/ord/ord.do" method="get">		 --%>
<!-- 				<input type="hidden" name="action" id="action" value="selected">	 -->
<!-- 					<div class="container" style="margin-top: 50px"> -->
<!-- 							<div class="col-md-12"> -->
<!-- 								<div class=" btn-group-toggle" data-toggle="buttons" id="btngp"> -->
									
<%-- 										<c:forEach var="exc" items="${lhs}">	 --%>
<%-- 											<input class="btn2 btn-primary" type="button" name="booking_time" value="${exc.booking_time}" > --%>
<%-- 										</c:forEach>	 --%>
<!-- 								</div> -->
<!-- 									<input type="hidden" name="mem_no" value="M000004"> -->
<%-- 						<input type="hidden" name="vendor_no" value="${param.vendor_no}"> --%>
<!-- 						<input type="hidden" name="tbl_no" value="T000001">  -->
<%-- 						<input type="hidden" name="share_mem_no1" value="${ordVO.share_mem_no1}">  --%>
<%-- 						<input type="hidden" name="share_mem_no2" value="${ordVO.share_mem_no2}"> --%>
<!-- 						<input type="hidden" name="share_amount" value="0"> -->
<!-- 					<input type="hidden" name="booking_time" value="12:30">	  -->
<!-- 						<input type="hidden" name="ord_time" -->
<%-- 								value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"> --%>
	
<!-- 						<input type="hidden" name="total" value="1000">  -->
<%-- 						<input type="hidden" name="arrival_time" value="${ordVO.arrival_time}">  --%>
<%-- 						<input type="hidden" name="finish_time" value="${ordVO.finish_time}">  --%>
<!-- 						<input type="hidden" name="verif_code" value="8JPXY6wQc5bvrN2y6h4h"> -->
<!-- 						<input type="hidden" name="status" value="0">  -->
<%-- 						<input type="hidden" name="booking_date" value="${param.booking_date}">		 --%>
<%-- 						<input type="hidden" name="party_size" value="${param.party_size}">	 --%>
<!-- 							</div> -->
<!-- 						</div>	 -->
<!-- 					</form> -->
								
				</div>
			</div>
		</div>	  
				  
				  
				  
				  
				  <jsp:useBean id="venSvc" scope="page" class="com.vendor.model.VendorService" />
				  
				  
				  </div>
				  <div class="tab-pane fade " id="profile" role="tabpanel" aria-labelledby="profile-tab">...\
				  	<div class="container">
						<div class="row justify-content-center">
							<div class="col-md-8">
								<div class="find-place-img_wrap">
									<div class="grid">
										<figure class="effect-ruby">
											<img src="<%= request.getContextPath()%>/ShowImg.do?vendor_no='${param.vendor_no}'&pic=1"/>
											<figcaption>
													<h5></h5>
	
											</figcaption>
										</figure>
									</div>
								</div>
								<c:set  var="vendorVO" value="${venSvc.findByPK(param.vendor_no)}" /> 
          
								<dl data-v-2ee1f21e="" class="info-list reset-list">
								<dt data-v-2ee1f21e="">餐廳位置</dt>
								<hr>
								<dd data-v-2ee1f21e="">${vendorVO.v_address1}${vendorVO.v_address2}${vendorVO.v_address3}</dd>
								<hr>
									<input type="hidden" id="addr" value=" ${vendorVO.v_address1}${vendorVO.v_address2}${vendorVO.v_address3}">
									      <div id="map" style="width:850px; height:400px; margin:0px auto;" ></div>
								<hr>
								<dt data-v-2ee1f21e="">餐廳E-mail</dt>
								<hr>
								<dd data-v-2ee1f21e="">${ vendorVO.v_mail}</dd>
								<!---->
								<hr>
								<dt data-v-2ee1f21e="">店家電話</dt>
								<hr>
								<dd data-v-2ee1f21e="">
									<a data-v-2ee1f21e="" href="tel:02-8786-7588">${vendorVO.v_tel}</a>
								</dd>
								<hr>
								<dt data-v-2ee1f21e="">營業時間</dt>
								<hr>
								<dd data-v-2ee1f21e="">${vendorVO.v_start_time}~${vendorVO.v_end_time}</dd>
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
				
				<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
			
				
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-12 ">
	
								
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
									
									
									
					<div class="container">
					${avgscore}
					<c:forEach var="cmapVO" items="${cMap}">
					  <div class="well">
					      <div class="media">
					      	<a class="pull-left" href="#">
					  <img src="<%= request.getContextPath()%>/ShowImg.do?mem_no='${cmapVO.key.mem_no}'"class="mr-3 memshow" alt="...">
					 
					  		</a>
					  		<div class="media-body">
					    		<h4 class="media-heading">標題</h4>
					          <h4><p class="text-left">${cmapVO.key.mem_name}</p></h4>
					         <h4> <p>${cmapVO.value.cmnt}</p></h4>
					          <ul class="list-inline list-unstyled">
					           <li>
					             <div class='starrr' id="s${cmapVO.value.cmnt_no}"></div>
					            </li>
					            <li>|</li>
					  			<li><span><i class="glyphicon glyphicon-calendar"></i> 
					  			<fmt:formatDate type="both"  value="${cmapVO.value.time}" /> </span></li>
					            <li>|</li>
					            <span><i class="glyphicon glyphicon-comment"></i> 2 comments</span>
					            <li>|</li>
					           
					            <li>
					            <!-- Use Font Awesome http://fortawesome.github.io/Font-Awesome/ -->
					              <span> <a href="https://www.facebook.com/search/top/?q=wang%20lee&epa=SEARCH_BOX" ><i class="fa fa-facebook-square"></i></a></span>	
					              <span><a href="https://www.instagram.com/?hl=zh-tw"><i class="fa fa-twitter-square"></i></a></span>
					              <span><a href="https://drive.google.com/drive/my-drive"><i class="fa fa-google-plus-square"></i></a></span>
					              
					            </li>
								</ul>
					       </div>
					    </div>
					  </div>
					  </c:forEach>
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
	
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
	<script>
		$.datetimepicker.setLocale('zh');
		$('#f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : false, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)       
			format : 'Y-m-d',
// 			value : 'new Date()',
// 			disabledDates: ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
// 	        startDate:	            '2017/07/10',  // 起始日
	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});
		
// 		 1.以下為某一天之前的日期無法選擇
	             var somedate1 = new Date();
	             $('#f_date1').datetimepicker({
	                 beforeShowDay: function(date) {
	               	  if (  date.getYear() <  somedate1.getYear() || 
	        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	                     ) {
	                          return [false, ""]
	                     }
	                     return [true, ""];
	             }});

	        
// 	             2.以下為某一天之後的日期無法選擇

					
				
// 	            var somedate2 = new Date();
	         
//              $('#f_date1').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() >  somedate2.getYear() || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});

	</script>
	



	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script
		src="http://www.5imoban.net/download/jquery/jquery-1.8.3.min.js"></script>
	<!-- 星星 -->
    <script src="../front-end/js/starrr.js"></script>
	
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

<c:forEach var="exc" items="${lhs}">
<script type="text/javascript">
$("#xx${exc.booking_time}").click(async function(event){
	
	const {value: file} = Swal.fire({
		  title: "您選擇的<br>日期：${param.booking_date}<br>人數：${param.party_size}人<br>時段：${exc.booking_time}",
		  width: 600,
		  padding: '3em',
		  background: '#fff url(https://sweetalert2.github.io/#examplesimages/trees.png)',
		  showConfirmButton:true,
		  backdrop: `
		    rgba(0,0,123,0.4)
		    url("https://sweetalert2.github.io/#examplesimages/nyan-cat.gif")
		    center left
		    no-repeat
		  `
		}).then(function(){
			var yyy=$("#xx${exc.booking_time}").val();
			$("#realyvalue").val(yyy);
			$('#form2').submit();
		})
})
 


</script>

</c:forEach>
<c:forEach var="exc" items="${lhs}">
<script>


var MyPoint = "/MyEchoServer/${booking_date}/${party_size}";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

var webSocket;
var myID;


function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	webSocket.onopen = function(event) {
// 		updateStatus("WebSocket 成功連線");
	}
	
	webSocket.onclose = function(event) {
// 		updateStatus("WebSocket 已離線");
	};
		
	webSocket.onmessage = function(event) {
		var data=event.data
		if(data=='open'){
			document.getElementById("xx${exc.booking_time}").disabled = true;
			document.getElementById("xx${exc.booking_time}").style.color="#ff0000";
			alert("${exc.booking_time}");
		}
		

	};
	};
	
	function sendMessage() {
		webSocket.send("open");
		
		
 	}
	function disconnect () {
		webSocket.close();
		
	}
	

</script>
</c:forEach>
<c:forEach var="cmapVO" items="${cMap}">
    <script type="text/javascript">
    $('#s${cmapVO.value.cmnt_no}').starrr({
    	
    	max: 5,
    	rating:${cmapVO.value.score},
    	readOnly: true,
    	emptyClass: 'fa fa-star-o',
        fullClass: 'fa fa-star'
        
      });
    
    </script>
</c:forEach>
</body>
</html>
