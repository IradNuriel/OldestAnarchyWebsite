<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="pre-common.jsp" %>
<table>
<thead>
<tr>
<th>שם משתמש</th>
<th>זמן התחברות אחרון</th>
</tr>
</thead>
<tbody>
<%=ctx.getChattersDates() %>
</tbody>


</table>
<%@ include file="post-common.jsp" %>
</body>

</html>