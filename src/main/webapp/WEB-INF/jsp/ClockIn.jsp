ｗ<%@
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="model.Dete,model.User"
%>
<%
Dete dete = (Dete)session.getAttribute("dete");
Dete in_ = (Dete)session.getAttribute("in");
Dete out_ = (Dete)session.getAttribute("out");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤務管理</title>
</head>
<body>
<p>ユーザー：${user.name}</p>
<h1>
${dete.year}年 ${dete.month}月 ${dete.day}日<br>
${dete.hour}：${dete.minute}
</h1>
<a href="ClockIn">更新</a>
<br>
打刻ボタンを押すと表示されている時間で打刻します。<br>
押し間違えると修正が必要となりますのでご注意下さい。
<br>
<br>
出勤
<%if(in_ == null){%>
<form action="ClockIn" method="post">
<input type="submit" value="打刻">
<input type="hidden" name="inout" value="出勤"> 
</form>
<%}else{%>
出勤時刻　<%=in_.getHour()%>： <%=in_.getMinute()%>
<%}%>
<br>
<br>
退勤
<%if(in_ != null){
	if(out_ == null){%>
		<form action="ClockIn" method="post">
		<input type="submit" value="打刻">
		<input type="hidden" name="inout" value="退勤"> 
		</form>
	<%}else{%>
		退勤時刻　<%=out_.getHour()%>： <%=out_.getMinute()%>
	<%}
	}else{%>
出勤の打刻をしてください。
<%}%>
</body>
<br>
<br>
<a href="Home">戻る</a>
</html>
