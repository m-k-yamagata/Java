<%@ 
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="model.User" %>
<%
User user = (User)request.getAttribute("user");
String massage = (String)request.getAttribute("mas");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h><%=massage%></h>
<p>
<%if(user != null){%>
登録者名　：<%=user.getName()%><br>
ID　　　　：<%=user.getId()%><br>
パスワード：<%=user.getPass()%><br>
<%}%>
</p>
<br>
<a href="index.jsp">トップへ戻る</a>
</body>
</html>