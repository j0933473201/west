<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*" %>
<html>
<head>


<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<h3>�D��@�a�t�Ӷ}�l�ާ@</h3>

<%--   <jsp:useBean id="ordSvc" scope="page" class="com.OrdService" /> --%>
  <jsp:useBean id="vSvc" scope="page" class="com.vendor.model.VendorService" />
     <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/ord/ord.do" >
       <b>�t�ӽs��:</b>
       <select size="1" name="vendor_no">
         <c:forEach var="vVO" items="${vSvc.all}" > 
<%--           <option value="${vVO.vendor_no}">${vVO.vendor_no} --%>
<!-- EL�ǰѼƨ���  -->
          <option value="${vVO.vendor_no}">${vSvc.findByPK(vVO.vendor_no).v_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="selected">
<%--        <input type="hidden" name="vendor_no" value="${vVO.vendor_no}"> --%>
       <input type="submit" value="�e�X">
    </FORM>
<br><br><br><br><br>






<table id="table-1">
   
</table>



<h3>��Ƭd��:</h3>
	
<ul>
  <li><a href='addOrd.jsp'>��^�s�W��</a> </li>
</ul>	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllOrd.jsp'>List</a> all Ords.  <br><br></li>
  
  
  <li>
    <FORM METHOD="get" ACTION="<%= request.getContextPath() %>/ord/ord.do">
        <b>��J�q��s�� </b>
        <input type="text" name="ord_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
   
  <li>
     <FORM METHOD="get" ACTION="<%= request.getContextPath() %>/ord/ord.do" >
       <b>��ܭq��s��:</b>
       <select size="1" name="ord_no">
         <c:forEach var="ordVO" items="${ordSvc.all}" > 
          <option value="${ordVO.ord_no}">${ordVO.ord_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <%-- <li>
     <FORM METHOD="post" ACTION="emp.do" >
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="empno">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.empno}">${empVO.ename}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li> --%>
</ul>


<h3>�q��޲z</h3>

<ul>
  <li><a href='<%= request.getContextPath() %>/ord/ord/addOrd2.jsp'>Add</a> a new Ord.</li>
</ul>

</body>
</html>