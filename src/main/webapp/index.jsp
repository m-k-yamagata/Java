<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String mess = (String)request.getAttribute("mess");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>
</head>
<body>
<h1>勤怠管理システム</h1>
<%if(mess != null){%>
<p><%=mess%></p>
<%}%>
<div>
<form action="Login" method="post">
ID			<input type="text" name="id"><br>
パスワード	<input type="text" name="pass"><br>
<input type="submit" value="ログイン">
</form>
<br>
<a href="Registr">ユーザー登録</a>
</div>
</body>
</html>