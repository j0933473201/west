<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>

<jsp:useBean id="vVO" scope="session" class="com.vendor.model.VendorVO" />

<!DOCTYPE html>


<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<!-- ===============================編輯區 開始====================================== -->


				<form class="needs-validation" novalidate METHOD="post" ACTION="Vendor.do" name="form1">
					<div class="container">

						<div class="col-12">
							<label id="topicBar" class="col-12">餐廳營業資訊設定</label>
							<div class="form-row">
								<div class="col-4 col-form-label">
									<label for="validationTooltip01">餐廳類型 :</label>
								</div>
								<div class="col-8">
<%-- 									<input type="text" name="v_type" value="${vVO.v_type}" class="form-control" --%>
										placeholder="請輸入餐廳類型" required>

								</div>
							</div>
							<div class="form-row">
								<div class="col-4 col-form-label">
									<label>開始營業時間 :</label>
								</div>
								<div class="col-8">
<%-- 									<input type="text" name="v_start_time" value="${vVO.v_start_time}" class="form-control" --%>
										placeholder="開店時間" required>
									<div class="invalid-tooltip">請輸入正確格式</div>
								</div>
							</div>
							<div class="form-row">
								<div class="col-4 col-form-label">
									<label>結束營業時間 :</label>
								</div>
								<div class="col-8">
<%-- 									<input type="text" name="v_end_time" value="${vVO.v_end_time}" class="form-control" id="pwd" --%>
										placeholder="打烊時間" maxlength="10" required
										pattern="^(?=.*\d)(?=.*[A-za-z]).{6,10}$">

								</div>
							</div>
							<div class="form-row">
								<div class="col-4 col-form-label">
									<label>定期休假 :</label>
								</div>
								<div class="col-8">
<%-- 									<input type="text" name="v_day" value="${vVO.v_day}" class="form-control" --%>
<!-- 										placeholder="選擇星期" maxlength="10" id="pwdconfirm" required -->
<!-- 										pattern="^(?=.*\d)(?=.*[A-za-z]).{6,10}$"> -->

								</div>
							</div>
							<div class="form-row">
								<div class="col-4 col-form-label">
									<label>店內總桌數 :</label>
								</div>
								<div class="col-8">
<%-- 									<input type="text" name="v_tables" value="${vVO.v_tables}" class="form-control" --%>
<!-- 										placeholder="數字" required> -->
									<div class="invalid-tooltip">請輸入有效信箱</div>
								</div>
							</div>
				
							<div class="form-row">
								<div class="col-4 col-form-label">
									<label>餐廳說明 :</label>
								</div>
								<div class="col-8">
<!-- 									<textarea rows="6" cols="50" name="v_text" class="form-control" -->
<%-- 										aria-label="With textarea">${vVO.v_text}</textarea> --%>


								</div>

							</div>

<input type="hidden" name="action"	value="Update">
<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
							<div>
								<button type="submit" class="btn btn-primary col-12">修改</button>
							</div>
						</div>
					</div>
			</div>
			
			</form>
			<div class="col-6">
				<div class="form-row">
					<div class="col-3 col-form-label">
						<label>店家形象</label>
					</div>
					<div class="col-9">
						<button id="V_PIC">更換形象</button>
					</div>
				</div>
				<img id="pic1" src="<%= request.getContextPath()%>/ShowImg.do?vendor_no='${vVO.vendor_no}'&pic=1">
<!-- 				<img id="pic1" src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder"> -->
				<div class="form-row">
					<div class="col-3 col-form-label">
						<label>目前宣傳</label>
					</div>
					<div class="col-9">
						<button id="V_AD">更換宣傳</button>
					</div>
				</div>
				<img id="ad1" src="<%= request.getContextPath()%>/ShowImg.do?vendor_no='${vVO.vendor_no}'&ad=1">
<!-- 				<img id="ad1" src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder"> -->
			</div>






















			<!-- =================================編輯區 結束==================================== -->
		</div>
	</div>

	</div>




</body>
</html>