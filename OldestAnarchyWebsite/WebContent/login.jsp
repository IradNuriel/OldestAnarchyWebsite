<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="./script/login.js"></script>
<title>Insert title here</title>
</head>
<body style="direction: rtl">
<%@ include file="pre-common.jsp" %>
<%
	String msg=null;
	if ((msg = (String)request.getAttribute("errorMsg"))!= null) {
		ctx.insertAlertDlg(msg, null);
	}
%>
<div class="center">
	<form id="form" method="post" >
		<label for="nickname">כנוי:</label>
		<input type="text" name="nickname" required >
		<br>
		<label for="password">סיסמה:</label>
		<input type="password" name="password" required>
		<br>
		
		<br>
		<input type="submit" value="הכנס  " formaction="MyServlet?cmd=login">
	</form>
	</div>
<%@ include file="post-common.jsp" %>
</body>
</html>