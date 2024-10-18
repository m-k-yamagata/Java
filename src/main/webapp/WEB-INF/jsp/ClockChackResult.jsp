<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.Dete,model.User"%>
<%
List<Dete> inList = (List<Dete>) session.getAttribute("inList");
List<Dete> outList = (List<Dete>) session.getAttribute("outList");
String year = (String) request.getAttribute("year");
String month = (String) request.getAttribute("month");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>
</head>
<p>ユーザー：${user.name}</p>
<body <h1><%=year%>年 <%=month%>月の出退勤リスト</h1>
	<div>
<h2>出勤</h2>
<%if (inList.size()>0) {%>
<%for (Dete d : inList) {%>
<p><%=d.getDay()%>日 <%=d.getHour()%>時 <%=d.getMinute()%>分</p>
<%}%>
<%}else{%><p>出勤データがありません</p><%}%>
</div>
	<div>
<h2>退勤</h2>
<%if (outList.size()>0) {%>
<%for (Dete d : outList) {%>
<p><%=d.getDay()%>日 <%=d.getHour()%>時 <%=d.getMinute()%>分</p>
<%}%>
<%}else{%><p>退勤データがありません</p><%}%>
</div>
	<a href="Chack">戻る</a></body>
</html>