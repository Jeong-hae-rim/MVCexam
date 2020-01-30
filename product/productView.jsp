<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.ProductVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>선택된 상품 정보는 다음과 같습니다.</h1>
	<hr>
	<%
		ProductVO vo = (ProductVO)session.getAttribute("list");
		if (vo != null) {
	%>
	선택된 사과의 갯수 :
	<%=vo.getApple()%><br>
	선택된 바나나의 갯수 :
	<%=vo.getBanana()%><br>
	선택된 한라봉의 갯수 :
	<%=vo.getHanra()%><br>
	<%
		} else {
	%>
	정보가 없습니다.
	<%
		}
	%>
	<hr>
	<a href='http://localhost:8000/mvc/htmlexam/product.html'>
				상품선택화면</a>
</body>
</html>