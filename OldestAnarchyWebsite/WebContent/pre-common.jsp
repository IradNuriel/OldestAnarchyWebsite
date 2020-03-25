<%@page import="communication.MyServlet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="logic.Context"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="./style/mystyle.css">
<title>Insert title here</title>
</head>
<body>
	<%
		Context ctx = null;
		try {
			ctx = new Context(pageContext);
		} catch (Exception e) {
		}
	%>
	<div class="common top">
		<ul>
			<li><a href="home.jsp">ראשי</a></li>

			<%
				if (!ctx.isLoggedIn()) {
					out.write("<li><a href='login.jsp'>כניסה</a></li>");
					out.write("<li><a href='register.jsp'>הרשמה</a></li>");
					
				} else {
					out.write("<li><a href='MyServlet?cmd=logout'>יציאה</a></li>");

					out.write("<li><a href='chat.jsp?name=" + ctx.getFieldFromRequest("name") + "'>צאט</a></li>");
				}
			%>
		</ul>
	</div>
	<div class="common right">
		<p>איזור הודעות ודיווחים</p>
	</div>
</body>
</html>