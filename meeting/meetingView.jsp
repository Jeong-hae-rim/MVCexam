<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.MeetingVO, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meeting Schedule</title>
<style>
td {
	border-bottom: 1px dotted green;
}

tr:hover {
	background-color: pink;
	font-weight: bold;
}

td:nth-child(3) {
	width: 400px;
}
</style>
</head>
<Script>
function f1(id){
	if (confirm("정말 삭제하시겠습니까??") == true){ 

		location.href="/mvc/meeting?id="+id;

	}else{ 

	    return;
}
}
</Script>
<body>

	<%
		List<MeetingVO> list = (List<MeetingVO>) request.getAttribute("list");
	    String msg = (String) request.getAttribute("msg");
		if (list != null) {
	%>
	<h2>미팅스케줄</h2>
	<hr>
	<table>
		<%
			for (MeetingVO vo : list) {
		%>
		<tr>
			<td><%=vo.getName()%></td>
			<td><%=vo.getTitle()%></td>
			<td><%=vo.getMeetingDate()%></td>
			<td><img src="/mvc/images/trash.jpg" width=30
				onclick="f1(<%=vo.getId()%>)"></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} if(msg != null) {
	%>
	<h2><%=msg%></h2>
	<%
		}
	%>
	<hr>
	<a href="/mvc/htmlexam/meetingForm.html ">미팅 홈 화면으로 가기</a>

</body>
</html>

