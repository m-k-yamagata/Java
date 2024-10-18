<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String mess = (String)request.getAttribute("mess");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤務管理</title>
</head>

<body>
<%if(mess != null){%>
<p><%=mess%></p>
<%}else{%><p>ユーザー：${user.name}</p><%}%>
<h1>勤務管理</h1>
<p>
<a href="ClockIn">勤怠打刻</a>
<br>
<br>
<a href="Chack">勤怠確認</a>
</p>
<br>
<br>
<a href="Login">ログアウト</a>
</body>
</html>