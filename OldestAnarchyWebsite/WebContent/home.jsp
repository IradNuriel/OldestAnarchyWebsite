<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<style type="text/css">
#welcome-p {
	text-align: center;
	margin: 200px 0;
	font-size: xx-large;
}
</style>
</head>

<body>
	<%@ include file="pre-common.jsp"%>
	<div class="center">

		<%
			String name = ctx.getFieldFromRequest("name");
			out.write("<p id='welcome-p'>ברוך הבא  " + (name.equals("") ? "אורח" : name));
		%>

	</div>
	<%@ include file="post-common.jsp"%>
</body>
</html>