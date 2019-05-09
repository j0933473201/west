
<%@page import="com.employee.model.EmployeeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.employee.model.EmployeeService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>桌位管理_列表</title>
<!-- Side Nav -->
<style type="text/css">
#sidenavOverlay {
	display: none;
	position: fixed;
	bottom: 0;
	left: 0;
	right: 0;
	top: 0;
	z-index: 998;
	background: rgba(0, 0, 0, 0.5);
}

#sidenavOverlay.active {
	display: block;
}

#sidenav {
	position: fixed;
	top: 0;
	bottom: 0;
	width: 280px;
	left: -280px;
	z-index: 999;
	background: #fff;
	color: #000;
}

.fullbar {
	animation-name: full;
	animation-duration: 0.5s;
	animation-fill-mode: both;
}

.invibar {
	animation-name: invi;
	animation-duration: 0.5s;
	animation-fill-mode: forwards;
}

@keyframes full {
from { 
left:-280px;
	
}

to {
	left: 0px;
}

}
@keyframes invi {from { left:0px;
	
}

to {
	left: -280px;
}

}
.nav .text-white:hover {
	background-color: red;
}

#backImg {
	width: 50%;
	height: 50%;
}

#loginButton {
	margin-top: 15px;
}

.disp {
	display: none;
}
.modal-title{
	margin-left: 100px;
}

    	#service{
			width: 60px;
			height: 60px;
			border-radius: 30px;
			border: 2px #fff solid;
			background-color: #f36;
			line-height: 60px;
			font-size: 14px;
			color: #fff;
			text-align: center;
			position: fixed;
			top:85%;/*top用負的,因為每個div都被加上了margin,為求在所有裝置都能順利顯示不會被吃掉*/
			left: 88%;/*也可以把margin調成0,就可以正常的用top調整*/
			z-index: 999;/*設定顯示層級*/
			margin: 0;
			cursor: pointer;
		}
		 #chatSpace{
			width: 300px;
			height: 380px;
			border-radius:10px;
			border: 2px #fff solid;
			background-color: #fff;
			line-height: 60px;
			font-size: 14px;
			color: #fff;
			text-align: center;
			position: fixed;
			top:45%;/*top用負的,因為每個div都被加上了margin,為求在所有裝置都能順利顯示不會被吃掉*/
			left: 80%;/*也可以把margin調成0,就可以正常的用top調整*/
			z-index: 999;/*設定顯示層級*/
			margin: 0;
		}
		 #chatList{
			width: 300px;
			height: 380px;
			border-radius:10px;
			border: 2px #fff solid;
			background-color: #fff;
			line-height: 60px;
			font-size: 14px;
			color: #fff;
			text-align: center;
			position: fixed;
			top:45%;/*top用負的,因為每個div都被加上了margin,為求在所有裝置都能順利顯示不會被吃掉*/
			left: 80%;/*也可以把margin調成0,就可以正常的用top調整*/
			z-index: 999;/*設定顯示層級*/
			margin: 0;
		}
		#service:hover{
			background-color: orange;
		}
		#chatTopic{
			border-bottom: 2px #000 solid;
			height:60px;
			margin-bottom:10px;
		}
		#chatBody{
			border-bottom: 2px #000 solid;
			height:220px;
			resize: none;
    		box-sizing: border-box;
   			overflow: auto;
		}
		#chatInput{
			border-bottom: 2px #000 solid;
			height:50px;
			overflow:hidden;
			resize:none;
		}
		#chatPannel{
			border: 2px #000 solid;
 			height:25px; 
		}
		#message{
			color: #000;
			background-color: orange;
			border-bottom: 2px orange solid;
			border-radius: 15px;
			padding:5px;
			line-height:25px;
			width: 120px;
			word-break: break-all;
		}
		.img-group {
			width: 100% ;
			height: 45px;
			width: 45px;
			border-radius: 999em;
		}
		.chatInner:hover{
			background-color: orange;
			cursor: pointer;
		}



/*.side-nav__section-title {
            color: #202124;
            display: block;
            font-size: 18px;
            padding-bottom: 12px;
            padding-left: 24px;
            padding-top: 40px;
        }       */
</style>
</head>
<!-- ============================================================================= -->

<body style="background-color: #3E444A;">
	<!-- Navbar -->
	<nav class="navbar  bg-dark navbar-dark justify-content-between">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar" id="btnSidenav" >
			<span class="navbar-toggler-icon"></span>
		</button>
		<form class="form-inline" action="<%=request.getContextPath()%>/employee/Employee.do">
			<span class="navbar-brand">Seek Food Table</span>
			<input type="hidden" name="action" value="logout">
			<button type="submit" class="btn btn-danger navbar-brand disp" id="logoutButton">登出</button>
		</form>
	</nav>
	<!-- Side Nav -->
	<div id="sidenavOverlay"></div>
	<nav class="nav navbar-nav bg-dark" id="sidenav">
		<a class="nav-link text-white p-3 disp" id="F002"
			href="${pageContext.request.contextPath}/employee/Employee.do?action=listAllEmp">員工管理</a>
		<a class="nav-link text-white p-3 disp" id="F001"
			href="${pageContext.request.contextPath}/employee/Employee.do?action=listAllMem">用戶管理</a>
		<a class="nav-link text-white p-3 disp" id="F003" href="#">店家檢舉審核</a> <a
			class="nav-link text-white p-3 disp" id="F004" href="#">文章檢舉審核</a> <a
			class="nav-link text-white p-3 disp" id="F005" href="#">評論檢舉審核</a>
			 <a
			class="nav-link text-white p-3 disp" id="F006" href="#">撥款作業</a>
 			<a
			class="nav-link text-white p-3 disp" id="F008" href="${pageContext.request.contextPath}/employee/Employee.do?action=listAllNews">最新消息管理</a>
			<a
			class="nav-link text-white p-3 disp" id="F007" href="#">退訂作業</a>
	</nav>

	<div class="backImgContainer disp">
		<div class="col-12">
			<div class="row justify-content-center col-12" id="backImgContainer">
				<img id="backImg" alt=""
					src="${pageContext.request.contextPath}/back-end/images/SeeKFoodA.png">
			</div>
			<div class="row justify-content-center col-12">
				<button type="button" class="btn btn-warning" id="loginButton"
					data-toggle="modal" data-target="#loginBar">登入 login</button>
			</div>
		</div>
	</div>

	<div class="modal fade" id="loginBar" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			<form method="post" action="<%=request.getContextPath()%>/employee/Employee.do" >
			<input type="hidden" name="action" value="login">
				<div class="modal-header">
					<h5 class="modal-title">SeakFoodTable
						員工登入</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
						<div class="containerForLog">
							<div class="row justify-content-center">
								<div class="col-10">
									<div class="form-group">
										<input type="text" name="emp_acc" id="mem_account"
											class="form-control" placeholder="請輸入帳號" required>
									</div>
									<div class="form-group">
										<input type="password" name="emp_pwd" id="mem_pwd"
											class="form-control" id="exampleInputPassword1"
											placeholder="請輸入密碼" required> <small
											id="passwordHelp" class="form-text text-muted">英文字母需區分大小寫</small>
									</div>
									<div class="form-row justify-content-between">
										<div class="col-2">
											<input type="button" id="fillmsg">
										</div>
									</div>
								</div>
							</div>
						</div>
				</div>
				<div class="modal-footer">
					
					<button type="submit" class="btn btn-primary col-12" id="submmit">登入Login</button>
				</div>
			</form>
		</div>
			
		</div>
	</div>

	<%
		if (request.getAttribute("listAllEmp") != null || request.getParameter("listAllEmpSwapPage") != null
				|| request.getAttribute("updateForEmp") != null || request.getAttribute("errorPage") != null) {
	%>
		<jsp:include page="/employeePage/listAllEmp.jsp" />
	<%
		}
	%>


	<%
		if (request.getAttribute("addEmp") != null) {
	%>
		<jsp:include page="/employeePage/addEmployee.jsp" />
	<%
		}
	%>

	<%
		if (request.getAttribute("updateEmp") != null) {
	%>
		<jsp:include page="/employeePage/updateEmp.jsp" />
	<%
		}
	%>

	<%
		if (request.getAttribute("updateMem") != null || request.getAttribute("errorPageForUpdateMem") != null) {
	%>
		<jsp:include page="/employeePage/updateMember.jsp" />
	<%
		}
	%>

	<%
		if (request.getAttribute("listAllMem") != null || request.getParameter("listAllMemSwapPage") != null
				|| request.getAttribute("errorPageForMem") != null) {
	%>
		<jsp:include page="/employeePage/listAllMember.jsp" />
	<%
		}
	%>
	
	<%
		if (request.getAttribute("listAllNews") != null||request.getParameter("listAllNewsSwapPage") != null) {
	%>
		<jsp:include page="/employeePage/listAllNews.jsp" />
	<%
		}
	%>
	
	<div id="service" style="display:none">客服訊息</div>
	
	<div id="chatContainer"></div>
<!-- 	<div class="" id="chatSpace" style="display:none"> -->
<!-- 		<div class="row"> -->
<!-- 			<div id="chatTopic" class="col-2"> -->
<%-- 				<img class="img-group" src="<%= request.getContextPath() %>/tool/OutImg.do?mem_no='${memberVO.mem_no}'"> --%>
<!-- 			</div> -->
<!-- 			<div id="chatTopic" class="col-10 text-left"> -->
<%-- 				<p style="color:black;font-weight:bold">${memberVO.mem_name}</p> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 			<div id="chatBody" class="col-12"> -->
<!-- 			</div> -->
<!-- 		<div class="row"> -->
<!-- 			<textarea id="chatInput" class="col" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();" ></textarea> -->
<!-- 		</div> -->
<!-- 		<div class="row"> -->
<!-- 			<div id="chatPannel" class="col"></div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
	<div class="" id="chatList" style="display:none">
<!-- 		<div class="row"> -->
<!-- 			<div id="chatTopic" class="col-2"> -->
<%-- 				<img class="img-group" src="<%= request.getContextPath() %>/tool/OutImg.do?mem_no='${memberVO.mem_no}'"> --%>
<!-- 			</div> -->
<!-- 			<div id="chatTopic" class="col-10 text-left"> -->
<%-- 				<p style="color:black;font-weight:bold">${memberVO.mem_name}</p> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 				<div class="row"> -->
<!-- 			<div id="chatTopic" class="col-2"> -->
<%-- 				<img class="img-group" src="<%= request.getContextPath() %>/tool/OutImg.do?mem_no='${memberVO.mem_no}'"> --%>
<!-- 			</div> -->
<!-- 			<div id="chatTopic" class="col-10 text-left"> -->
<%-- 				<p style="color:black;font-weight:bold">${memberVO.mem_name}</p> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 				<div class="row"> -->
<!-- 			<div id="chatTopic" class="col-2"> -->
<%-- 				<img class="img-group" src="<%= request.getContextPath() %>/tool/OutImg.do?mem_no='${memberVO.mem_no}'"> --%>
<!-- 			</div> -->
<!-- 			<div id="chatTopic" class="col-10 text-left"> -->
<%-- 				<p style="color:black;font-weight:bold">${memberVO.mem_name}</p> --%>
<!-- 			</div> -->
<!-- 		</div> -->
	</div>

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
	<!-- Input type=number -->
	<script src="bootstrap-input-spinner.js"></script>
	<script>
		$("input[type='number']").inputSpinner();
	</script>
	<!-- Side Nav -->
	<script type="text/javascript">
		$(document).ready(function() {
			if(${reLogin!=null}){
				alert("請登入後再開始使用");
			}
			
			$('#btnSidenav').on('click', function(e) {
				$('#sidenav').removeClass('invibar');
				$('#sidenavOverlay').addClass('active');
				$('#sidenav').addClass('fullbar');
			});

			jQuery('#sidenavOverlay').on('click', function() {
				$('#sidenavOverlay').removeClass('active');
				$('#sidenav').removeClass('fullbar');
				$('#sidenav').addClass('invibar');
			});
			if (!$(".container").length) {
				$(".backImgContainer").removeClass('disp');
			}
			if (${account!=null}) {
				$(".navbar-toggler").removeClass('disp');
				$("#loginButton").addClass('disp');
				$("#logoutButton").removeClass('disp');
				$("#chatSpace").addClass('container');
				$("#chatList").addClass('container');
				$("#service").css('display','');
				connect();
			}else{
				$(".navbar-toggler").addClass('disp');
				$("#loginButton").removeClass('disp');
				$("#logoutButton").addClass('disp');
			}
    		$("#service").on('click',function(){
    			$("#service").css('display','none');
    			$("#chatSpace").css('display','');
    			$("#chatList").css('display','');
    		});


			<c:forEach var="fealist" items="${featureList}">
				$("#${fealist.fea_no}").removeClass('disp');
			</c:forEach>
		});
	</script>
<!-- =============webSocket=============================================================== -->
	<script>
    
    var MyPoint = "/MyEchoServer/${employeeVO.emp_name}/employee";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	var memberSet = new Set();
	
	
	function connect() {
		alert(endPointURL);
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
		
		};

		webSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        var mem_no = jsonObj.mem_no;
	        var mem_name= jsonObj.userName;
// 	        alert("dynamic");
// 	        alert("mem_no="+mem_no);
// 	        alert(jsonObj.message);
	        if(!memberSet.has(mem_no)){
	        	memberSet.add(mem_no);
		        alert("mem_no="+mem_no);
	        	$("#chatList").append($('<div/>').addClass('row').addClass('chatInner').attr('id',mem_no).append($('<div/>').attr('id','chatTopic').addClass('col-2').append($('<img>').addClass('img-group').attr('src',"<%= request.getContextPath() %>/tool/OutImg.do?mem_no="+'\''+mem_no+'\'')
	        	)).append($('<div/>').attr('id','chatTopic').addClass('col-10').addClass('text-left').append($('<p/>').css('color','black').css('font-weight','bold').html('來自'+mem_name+'的訊息')))
	        			);
	        	$("#chatContainer").append($('<div/>').addClass('container').addClass('chatContainer'+mem_no).attr('id','chatSpace').css('display','none')
	        								.append($('<div/>').addClass('row')
	        										.append($('<div/>').addClass('col-2').attr('id','chatTopic')
	        												.append($('<img>').addClass('img-group').attr('src',"<%= request.getContextPath() %>/tool/OutImg.do?mem_no="+'\''+mem_no+'\''))		
	        										)
	        										.append($('<div/>').attr('id','chatTopic').addClass('col-10').addClass('text-left')
	        												.append($('<p/>').css('color','black').css('font-weight','bold').html(mem_name))
	        										)
	        								)
	        								.append($('<div/>').attr('id','chatBody').addClass('chatBody'+mem_no).addClass('col-12')
	        								
	        								)
	        								.append($('<div/>').addClass('row')
	        										.append($('<textarea/>').attr('id','chatInput').addClass('col').attr('placeholder','訊息').attr('onkeydown','if(event.keyCode==13) sendMessage();'))
	        								)
	        								.append($('<div/>').addClass('row')
	        										.append($('<div/>').attr('id','chatPannel').addClass('col'))
	        								)
	        							);
	        	
	        }
	        if(jsonObj.userName==='${employeeVO.emp_no}'){
		        $(".chatBody"+mem_no).append(
		        		$('<div/>').addClass('row justify-content-end message_container').append($('<div/>').append($('<p/>').attr('id','message').html(jsonObj.message)))
		        		);
	        }else{
	        	 $(".chatBody"+mem_no).append(
	 	        		$('<div/>').addClass('row justify-content-start message_container').append($('<div/>').append($('<p/>').attr('id','message').html(jsonObj.message)))
	 	        		);
	        }
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
// 	var inputUserName = ;
	
	function sendMessage() {
		alert("sendmsg");
		alert("message");
// 	    var userName = inputUserName.value.trim();

	    
	    var inputMessage = document.getElementById("chatInput");
	    var message = inputMessage.value.trim();
	    alert("message");
	    

	        var jsonObj = {"userName" : "${employeeVO.emp_no}", "message" : message,"toUser" : "default"};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    
	}

	
	function disconnect () {
		webSocket.close();
	}
	
	$("#chatList").on('click','.chatInner',function(){
		alert('5555555555555');
		var whichMem = $(this).attr('id');
// 		alert($(this).attr('id'));
// 		document.getElementsByClassName($(this).attr('id'))[0].display="";
		$(".chatContainer"+whichMem).css('display','');
		$("#chatList").css('display','none');
	});

    
</script>

</body>

</html>