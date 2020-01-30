<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연산 결과</title>
</head>
<body>
<h1>연산 요청 결과</h1>
<hr>
<h2>결과 :
	<span style="color:magenta">${requestScope.result}</span></h2>
	<br>
	<a href="${header.referer}">입력화면</a>
</body>
</html>