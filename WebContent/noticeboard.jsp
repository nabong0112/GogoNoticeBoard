<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="main.dao.WriteDao"%>
<%@ page import="main.vo.WriteVo"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<style type="text/css">
a:link{color:black;}
a:visited{color:black;} 
a:hover{color:blue;}
a:active{color:yellow;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나봉게시판에 오신것을 환영합니다</title>
<%
	String user_id = (String) session.getAttribute("user_id");
%>
<%
	WriteVo vo = new WriteVo();
	WriteDao dao = new WriteDao(); //게시글 불러옴
	ArrayList<WriteVo> board = dao.selectText();
	int nowPage = 0;
	if (request.getParameter("nowPage") != null) {
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	} else {
		nowPage = 1;
	}
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
	function gowrite() { //글쓰기 버튼
		var bool = confirm('글을 작성하시겠습니까?');
		if (bool == true) {
			location.href = "writeform.jsp";
		} else {
			location.replace("noticeboard.jsp");
		}
	}
</script>
<script type="text/javascript">
	function logout() { //로그아웃 버튼
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
</head>
<body>
	<div id="container">
		<div id="header"
			style="background-color: #E2E2E2; height: 110px; text-align: right;">
			<div id="main"
				style="background-color: #E2E2E2; height: 80px; text-align: left;">
				<a href="noticeboard.jsp"><img src="file:///C:/Java/eGovFrameDev-3.8.0-32bit/workspace/Nabong_writer/WebContent/image/notice.png"
					width="200px" height="100px"></a>
			</div>
			<b>회원 <%=user_id%></b>님 안녕하세요! <a href="myPage.jsp"style="text-decoration: none;" >내정보</a> 
			<input type="button" name="logout" value="로그아웃"
				onclick="javascript:logout();">
		</div>

		<div id="content"
			style="background-color: #E3E3E3; height: 883px; width: 1900px; float: left;">
			<div id="menu"
				style="background-color: #E3E3E3; height: 883px; width: 145px; float: left;">
				<fieldset style="height: 95%;">
					<b>Menu</b> <br> HTML<br> CSS<br>
				</fieldset>
			</div>
			<!-- ----------------------------------------------- 검색 하는곳 --------------------------------------------------------- -->
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
						</select> <input type="text" name="search" size="40"
							placeholder="검색어를 입력하세요"> <input type="submit"
							name="gosearch" size="20" value="검색"><br> <br>
					</form>
				</fieldset>
			</div>
			<!-- -----------------------------------------------검색 하는곳 끝 --------------------------------------------------------- -->
			<%
				int countPage = 5; //한번에 출력될 페이지 수
				int totalCount = board.size(); //총 게시글 수
				int countList = 7; //한 페이지에 출력될 게시글 수
				int totalPage = totalCount / countList; //총 몇페이지인지 알기위해
				int startPage = ((nowPage - 1) / countPage) * countPage + 1; //첫 페이지 구하기
				//현재페이지에서 1을 뺀 수를 countPage로 나눈뒤 그 값을 다시 countPage로 곱한뒤 1을 더해준다
				int endPage = startPage + countPage - 1; //마지막 페이지 구하기
				int beginNum = (nowPage - 1) * countList; //현재페이지에서 1을 뺀 수에서 한 페이지에 출력될 게시글 수를 곱하면
				//예를들어 현재페이지가 1이면 1-1*7 이므로 0 그리고 0 + 7 하면 7 그러니까 0번글부터 7번글까지 나오게 함
				int endNum = beginNum + countList;
				if (totalCount % countList > 0) {

					totalPage++;

				} //여기까지 하면 총 페이지 수를 알 수 있게 됨
				if (totalPage < nowPage) { //총 페이지보다 페이지가 수가 더 클때 치환

					nowPage = totalPage;

				}
				if (endPage > totalPage) { //총 페이지보다 마지막 페이지 수가 더 클때 치환

					endPage = totalPage;

				}
				if(endNum > totalCount){
					endNum = totalCount;
				}
			%>
			<!-- ----------------------------------------------- 게시판 글 보여주는곳 --------------------------------------------------------- -->
			<div id="border"
				style="background-color: #EEEEEF; height: 733px; align-content: center;">
				<fieldset
					style="height: 100%; line-height: 2.3em; align-content: center; text-align: center;">
					<b>[총 게시판 갯수 : <%=board.size()%>개]
					</b><br>
					<table style="line-height: 2.3em;" align="center">
						<tr align="center" bgcolor="gray">
							<th width="80"><b>번호</b></th>
							<th width="640"><b>제목</b></th>
							<th width="120"><b>작성자</b></th>
							<th width="180"><b>작성 일자</b></th>
							<th width="80"><b>조회수</b></th>
						</tr>

						<%
							int boardsize = board.size();
							if (!board.isEmpty()) {
								for (int i = beginNum; i < endNum; i++) { 
								
						%>
						<tr align="center">
							<td><a style="text-decoration: none;"
								href="/Nabong_writer/ReadServlet?board_no=<%=board.get(i).getBoard_no()%>"><%= i + 1 %></a></td>
							<td><a style="text-decoration: none;"
								href="/Nabong_writer/ReadServlet?board_no=<%=board.get(i).getBoard_no()%>"><%=board.get(i).getBoard_title()%></a></td>
							<td><%=board.get(i).getBoard_user()%></td>
							<td><%=board.get(i).getBoard_time()%></td>
							<td><%=board.get(i).getBoard_view()%></td>

							<%
								}
									} else {
							%>
						
						<tr align="center">
							<td colspan="5"><b> 등록된 글이 없습니다. 아래 글쓰기를 눌러 첫 게시글의 주인공이
									되어보세요!</b></td>

							<%
								}
							%>
						</tr>
					</table>

					<!-- ----------------------------------------------- 게시판 글 보여주는곳 끝--------------------------------------------------------- -->
					<div id="Page" align="center">
						<table>
							<tr>
								<% 
								if(nowPage != 1) {%>
								<td align="center"><b><a href="noticeboard.jsp?nowPage=<%=nowPage - 1%>" style="text-decoration: none;"> ◀ </a></b></td>
								
								<%}
									for (int Count = startPage; Count <= endPage; Count++) { //페이징 페이지 번호를 출력
										
										if (Count == nowPage) { //현재 페이지에는 굵은 표시
								%>

								<td align="center"><b><a href="noticeboard.jsp?nowPage=<%=Count%>" style="text-decoration: none;"> [<%=Count%>]
									</a></b></td>

								<%
									} else {
								%>
								<!-- 현재 페이지가 아닌 경우 아무표시 없음 -->

								<td align="center">
								<a href="noticeboard.jsp?nowPage=<%=Count%>" style="text-decoration: none;"> <%=Count%></a>
								</td>
								

								<%}
										

								}  %>
								<% if(nowPage <= totalPage) {%>
										<td align="center"><b><a href="noticeboard.jsp?nowPage=<%=nowPage + 1 %>" style="text-decoration: none; text-shadow: none;"> ▶ </a></b></td>
								
								<% 	} %>



							</tr>
						</table>
					</div>
					<!-- ----------------------------------------------- 글 쓰러 가는곳 --------------------------------------------------------- -->
					<div id="gowrite" align="center">
						<br>
						<button style="height: 50px; width: 100px;"
							onClick="javascript:gowrite();">글쓰기</button>
					</div>
					<!-- <input type="button" name="write" style="height: 50px; width: 100px;"  onClick= "javascript:write();">글쓰기</ -->

				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>