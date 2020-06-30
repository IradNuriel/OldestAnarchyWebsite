<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
<style>
.table1 {
	border: 1px;
	border-color: black;
	border-style: solid;
}

tr.table11 {
	background-color: darkgray;
}

td.table1 {
	width: 2cm;
	border: 1px;
	border-color: black;
	border-style: solid;
}

th.table1 {
	width: 2cm;
	border: 1px;
	border-color: black;
	border-style: solid;
}
</style>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="pre-common.jsp"%>
	<%
		String msg = null;
		if ((msg = (String) request.getAttribute("errorMsg")) != null) {
			ctx.insertAlertDlg(msg, null);
		}
	%>
	<table class="table1">
		<thead>
			<tr class="table11">
				<th class="table1">שם משתמש</th>
				<th class="table1">זמן התחברות אחרון</th>
			</tr>
		</thead>
		<tbody>
			<%=ctx.getChattersDates()%>
		</tbody>
	</table>
	<form id="deleteUserForm" method="post">
		<input type="text" name="nickname" required> <input
			type="submit" value="מחק משתמש   " formaction="MyServlet?cmd=delete">
	</form>
	<%@ include file="post-common.jsp"%>
</body>

</html>