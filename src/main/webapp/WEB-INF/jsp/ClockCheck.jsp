<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.time.LocalDateTime,model.User"%>
    <%String mess = (String)request.getAttribute("mess");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>ユーザー：${user.name}</p>
<h1>出退勤時間確認</h1>
<br>
<p>
年月は必ず選択してください。<br>
<%if(mess != null && mess.length() > 0){%><%=mess%><%;}%>
</p>
<form action="Chack" method="post">
<select name="year">
		<option value="">年を選択</option>
		<%for(int i = 0;i<3;i++){%>
		<%
		LocalDateTime time = LocalDateTime.now();
		int year = time.getYear()-1;
		year = year + i;
		String s = Integer.toString(year);
		%>
		<option value=<%=s%>><%=s%>年</option>
		<%}%>
</select>
<select name="month">
		<option value="">月を選択</option>
		<%for(int i = 0;i<12;i++){%>
		<%
		int month = i + 1;
		String s = Integer.toString(month);
		%>
		<option value=<%=s%>><%=s%>月</option>
		<%}%>
</select>
<input type="submit" value="確認">
</form>
<a href="Home">戻る</a>
</body>
</html>

<%--
<select name="prefecture">
		<option value="">月を選択</option>
		<option value="1">１月</option>
		<option value="2">２月</option>
		<option value="3">３月</option>
		<option value="4">４月</option>
</select>
--%>