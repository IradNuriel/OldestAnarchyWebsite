<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Chat</title>
		<script src="./script/chat.js"></script>
		<link rel="stylesheet" type="text/css" href="./style/chat.css">
	</head>
	<body onload="listenToNewMessages()" style="height: 100%; width: 100%;text-align:center;">
	<%@ include file="pre-common.jsp" %>
		<p style="text-align:center">Welcome to chat!</p>
		<br>
		<div style="height: 60%; width: 100%;text-align:center;padding:70px 450px;" id="chat">
		</div>
		<div style=" height: 10%; text-align:center">
			<input type="button"onclick="sendMessage()" />
			<input id="text" type="text" />
		</div>
		<%@ include file="post-common.jsp" %>
	</body>
</html>










