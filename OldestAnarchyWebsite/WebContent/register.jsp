<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="./script/register.js"></script>
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
		<input type="text" name="nickname" id="nickname" required >
		<br>
		<label for="password">סיסמה:</label>
		<input type="password" name="password" id="password" required>
		<br>
		<label for="verify">סיסמה:</label>
		<input type="password" name="verify" id="verify" required>
		<br>
		
		<br>
		<input type="submit" value="הרשם  " formaction="MyServlet?cmd=register" onclick="approve();">
	</form>
	</div>
<%@ include file="post-common.jsp" %>
</body>
</html>