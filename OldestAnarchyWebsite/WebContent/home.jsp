<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
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