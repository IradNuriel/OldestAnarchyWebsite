<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
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