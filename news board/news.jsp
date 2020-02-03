<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.NewsVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
	text-align: center;
}

td {
	border-bottom: 2px dotted green;
}

tr:hover {
	font-weight: bold;
}

td:nth-child(3) {
	width: 400px;
}
</style>
<title>News Board</title>
</head>
<body>
	<%
		ArrayList<NewsVO> list = (ArrayList<NewsVO>) request.getAttribute("list");
		if (list != null) {
	%>
	<h1 style="text-align: center;">뉴스 게시판</h1>
	<br>
	<br>
	<table style="margin: auto;">
		<tr>
			<td style="width: 50px; text-align: center;">번호</td>
			<td style="width: 300px; text-align: center;">제목</td>
			<td style="width: 100px; text-align: center;">작성자</td>
			<td style="width: 200px; text-align: center;">작성일</td>
			<td style="width: 50px; text-align: center;">조회수</td>
		</tr>
	</table>
	<table style="margin: auto;">
		<%
			for (NewsVO vo : list) {
		%>
		<tr onclick="location.href='/mvc/news?action=read&id=<%=vo.getId()%>'">
			<td style="width: 50px; text-align: center;"
				class='<%=vo.getId()%>'><%=vo.getId()%></td>
			<td style="width: 300px; text-align: center;"
				class='<%=vo.getId()%>'><%=vo.getTitle()%></td>
			<td style="width: 100px; text-align: center;"
				class='<%=vo.getId()%>'><%=vo.getWriter()%></td>
			<td style="width: 200px; text-align: center;"
				class='<%=vo.getId()%>'><%=vo.getWritedate()%></td>
			<td style="width: 50px; text-align: center;"
				class='<%=vo.getId()%>'><%=vo.getCnt()%></td>
		</tr>
		<%
			}
		%>
	</table>

	<%
		}
	%>

	<DIV class='aside_menu'>
		<FORM name='frm' method='GET' action='/mvc/news'>
				<SELECT name='searchtype'>
					<OPTION value='writer'>작성자</OPTION>
					<OPTION value='title'>제목</OPTION>
					<OPTION value='content'>내용</OPTION>
				</SELECT> 
				<button type='submit' onclick="displayDiv(2);">검색</button>
		  </FORM>
	</DIV>
				<br>
				<button onclick="displayDiv();">뉴스 작성</button>
				<script>
					function displayDiv() {
						document.getElementById("write").style.display = 'block';
					}
					function back() {
						document.getElementById("write").style.display = 'none';
					}

					function back2() {
						document.getElementById("update").style.display = 'none';
					}

					function del() {
						location.href = "/mvc/news?action=delete&id=" + id;
					}

					function displayUpdateForm(cv) {
						var doms = document.getElementsByClassName(cv)
						document.getElementById("update").style.display = 'block';
					}
				</script>
				<div id="write" style="display: none">
					<hr style="width: 50%;">
					<h2 id="divT">뉴스 작성</h2>
					<form method="post" action="/mvc/news">
						<input type="hidden" name="action" value="insert"> <input
							id="n_writer" style="width: 300px" type="text" name="writer"
							placeholder="작성자명을 입력하세요"> <br> <input id="n_title"
							style="width: 300px" type="text" name="title"
							placeholder="제목을 입력하세요"> <br>
						<textarea id="n_content" style="width: 300px; height: 200px"
							name="content" placeholder="내용을 입력하세요"> </textarea>
						<br> <input type="submit" value="저장"> <input
							type="reset" value="재작성"> <input onclick="back();"
							type="button" value="취소">
					</form>
				</div>

				<div id="update" style="display: none">
					<hr style="width: 50%;">
					<h2 id="divT">뉴스 내용</h2>
					<form method="post" action="/mvc/news">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="id" value="${read.id}"> <input
							id="writer" style="width: 300px" type="text" name="writer"
							value="${read.writer}"> <br> <input id="title"
							style="width: 300px" type="text" name="title"
							value="${read.title}"> <br>
						<textarea id="content" style="width: 300px; height: 200px"
							name="content">${read.content}</textarea>
						<br> <input type="submit" value="수정"> <input
							onclick="del();" type="button" value="삭제"> <input
							onclick="back2();" type="button" value="닫기">
					</form>
				</div>
</body>
<script>
	var id =
<%=request.getParameter("id")%>
	if (id != null) {
		document.getElementById("update").style.display = 'block';
	}
</script>
</html>