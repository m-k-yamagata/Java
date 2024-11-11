<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ユーザー登録画面</h1>
<div>
<p>
登録する方の氏名とパスワードを入力して下さい<br>
氏名は詰めて入力しても構いません
</p>
<form action="Registr" method="post">
氏名		<input type="text" name="name"><br>
パスワード	<input type="text" name="pass"><br>
<br>
<p>登録用パスワードを入力し登録ボタンを押してください</p>
登録用パスワード<input type="text" name="Master"><br>
<input type="submit" value="ユーザー登録">
</form>
</div>
<br>
<a href="index.jsp">トップへ戻る</a>
</body>
</html>