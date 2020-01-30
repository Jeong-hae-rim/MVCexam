<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연산 결과(에러)</title>
</head>
<body>
<h1>요청을 처리하는 동안 오류가 발생했습니다.</h1>
<h2>오류의 원인 : <span style="color:magenta">${requestScope.result}</span></h2>
<br><br>
<a href="${header.referer}">입력화면</a>
</body>
</html>