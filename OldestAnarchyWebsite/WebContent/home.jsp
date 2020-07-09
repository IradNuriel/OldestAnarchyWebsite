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
#instructions {
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
			String name = ctx.getUserNickName();
			out.write("<p id='welcome-p'>ברוך הבא  " + (name.equals("") ? "אורח" : name));
		%>
		<p id='instructions'>
			אתר זה הינו צ'אט אנרכי, באופן כללי אין חוקים. אם אינך בעל חשבון באתר,
			לחץ על הרשם ותירשם לאתר. <br>אם בבעלותך חשבון אך אינך מחובר, לחץ
			על כניסה והכנס את פרטי החשבון שלך.<br>אם הינך כבר מחובר, לחץ על
			צ'אט בכדי להתחיל לצ'טט
		</p>
	</div>
	<%@ include file="post-common.jsp"%>
</body>
</html>