<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="main.dao.WriteDao"%>
<%@ page import="main.vo.WriteVo"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나봉게시판에 오신것을 환영합니다</title>
<%
	String user_id = (String) session.getAttribute("user_id");
%>
<%
	if (user_id == null) {
%>

<script type="text/javascript">
	alert("엥! 누구세요!");
	location.href = "loginform.jsp";
</script>


<%
	}
%>
<script type="text/javascript">
	function gowrite() {
		var bool = confirm('글을 작성하시겠습니까?');
		if (bool == true) {
			location.href = "writeform.jsp";
		} else {
			location.replace("noticeboard.jsp");
		}
	}
</script>
</head>
<body>
	<div id="container">
		<div id="header"
			style="background-color: #E2E2E2; height: 110px; text-align: right;">
			<div id="main"
				style="background-color: #E2E2E2; height: 80px; text-align: left;">
				<a href="noticeboard.jsp"><img
					src="/Nabong_writer/WebContent/image/notice.png" width="200px"
					height="100px"></a>
			</div>
			<b>회원 <%=user_id%></b>님 안녕하세요! <a href="myPage.jsp">내정보</a> <input
				type="button" name="logout" value="로그아웃"
				onclick="javascript:logout();">
		</div>
		<script type="text/javascript">
			function logout() {
				var form = document.logout;
				var bool = confirm('로그아웃 하시겠습니까?');
				if (bool == true) {
					alert("안녕히가세요!");
					location.href = "loginform.jsp";
					session.invalidate();

				} else {
					location.replace("noticeboard.jsp");
				}
			}
		</script>
		<div id="content"
			style="background-color: #E3E3E3; height: 883px; width: 1900px; float: left;">
			<div id="menu"
				style="background-color: #E3E3E3; height: 883px; width: 145px; float: left;">
				<fieldset style="height: 95%;">
					<b>Menu</b> <br> HTML<br> CSS<br>
				</fieldset>
			</div>
			<div id="searchForm"
				style="background-color: #EEEEEF; height: 130px;">
				<fieldset align="center">
					<form action="/Nabong_writer/SearchServlet">
						<br> <b>게시글 검색 : </b> <select name="searchType"
							style="height: 23px;">
							<option value="0">제목</option>
							<option value="1">내용</option>
							<option value="2">제목+내용</option>
							<option value="3">글쓴이</option>
						</select> <input type="text" name="search" size="40"> <input
							type="submit" name="gosearch" size="20" value="검색"><br>
						<br>
					</form>
				</fieldset>

			</div>
			<div id="border"
				style="background-color: #EEEEEF; height: 733px; align-content: center;">
				<fieldset
					style="height: 700px; line-height: 2.3em; align-content: center;">
					<br>
					<table style="line-height: 2.3em;" align="center">
						<tr align="center" bgcolor="gray">
							<th width="80"><b>번호</b></th>
							<th width="640"><b>제목</b></th>
							<th width="100"><b>작성자</b></th>
							<th width="130"><b>작성 일자</b></th>
							<th width="80"><b>조회수</b></th>
						</tr>
						<%
							//dao를 선언하고 배열을 불러와서 값이 있으면
							WriteVo vo = new WriteVo();
							WriteDao dao = new WriteDao();
							String searchString = request.getParameter("searchType");
							int searchInt = Integer.parseInt(searchString);
							String searchName = request.getParameter("search");
							ArrayList<WriteVo> search = dao.search(searchInt, searchName);
							if (!search.isEmpty()) {
								for (/*WriteVo i: board*/ int i = 0; i < search.size(); i++) {
						%>
						<tr align="center">
							<!-- href를 서블릿으로 바꿔야ㅚㄹ거같은데? -->
							<td><a
								href="/Nabong_writer/ReadServlet?board_no=<%=search.get(i).getBoard_no()%>"><%=search.get(i).getBoard_no()%></a></td>
							<td><a
								href="/Nabong_writer/ReadServlet?board_no=<%=search.get(i).getBoard_no()%>"><%=search.get(i).getBoard_title()%></a></td>
							<td><%=search.get(i).getBoard_user()%></td>
							<td><%=search.get(i).getBoard_time()%></td>
							<td><%=search.get(i).getBoard_view()%></td>

							<%
								}
								} else {
							%>
						
						<tr align="center">
							<td colspan="5"><b> 검색된 글이 없습니다!</b></td>

							<%
								}
							%>
						</tr>
					</table>
					<div align="center">
						<br>
						<button style="height: 50px; width: 100px;"
							onClick="javascript:gowrite();">글쓰기</button>
						<button style="height: 50px; width: 100px;"
							onClick="javascript:goboard();">목록으로</button>
					</div>
					<script type="text/javascript">
						function goboard() {
							location.href = "/Nabong_writer/noticeboard.jsp";
						}
					</script>
					<!-- <input type="button" name="write" style="height: 50px; width: 100px;"  onClick= "javascript:write();">글쓰기</ -->

				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>