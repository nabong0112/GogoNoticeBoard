<%@page import="java.sql.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="main.dao.WriteDao"%>
<%@ page import="main.vo.WriteVo"%>
<%@ page import="main.vo.CommentVo"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
a:link{color:black;}
a:visited{color:black;} 
a:hover{color:blue;}
a:active{color:yellow;}
</style>
<%
	WriteDao dao = new WriteDao();
	WriteVo vo = new WriteVo();
	CommentVo cvo = new CommentVo();
%>
<%
	String user_id = (String) session.getAttribute("user_id");
	int board_no = Integer.parseInt(request.getParameter("board_no"));
	String title = (String) request.getAttribute("title");
	String text = (String) request.getAttribute("text");
	String user = (String) request.getAttribute("user");
	String time = (String) request.getAttribute("time");
	int view = (Integer) request.getAttribute("view");
	ArrayList<CommentVo> commentboard = dao.SelectComment(board_no);
	ArrayList<WriteVo> board = dao.selectText();
	int board_back = board_no - 1;
	int board_front = board_no + 1;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=user%>님의 글입니다</title>
</head>
<body>

	<%
		if (user_id == null) {
	%>

	<script type="text/javascript">
		alert("비회원은 글을 읽을 수 없습니다!");
		location.href = "loginform.jsp";
	</script>

	<%
		}
	%>
	<div class="container" style="background-color: #E2E2E2;">
		<div id="header"
			style=" height: 110px; text-align: right;">
			<div id="main"
				style=" height: 80px; text-align: left;">
				<a href="noticeboard.jsp"><img src="image/notice.png"
					width="200px" height="100px"></a>
			</div>
			<b>회원 <%=user_id%></b>님 안녕하세요! <a href="myPage.jsp"style="text-decoration: none;" >내정보</a> 
			<input type="button" name="logout" value="로그아웃"
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
					location.replace("#");
				}
			}
		</script>
		<!-- ----------------------------------------------- 글 보여주느곳--------------------------------------------------------- -->

		<div align="center" style="border: 1px solid #dddddd; overflow: auto;">
			<div>
				<%
					if (board_no != 1) {
				%>
				<a id="back" style="text-decoration: none;"
					href='/Nabong_writer/ReadServlet?board_no=<%=board_back%>'>이전글</a>
				<%
					}
				%>
				<%
					if (board.size() != board_no) {
				%>
				<a id="front" style="text-decoration: none;"
					href='/Nabong_writer/ReadServlet?board_no=<%=board_front%>'>다음글</a>
				<%
					}
				%>
			</div>
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd; line-height: 2.3em;">
				<thead>
					<tr>
						<th colspan="7" style="background-color: #eeeeee; text-align: center;"><%=board_no%>번째글</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%; border: 1px solid #dddddd;">글 제목</td>
						<td colspan="2" style="width: 400px; border: 1px solid #dddddd;">
							<%=title%></td>

						<td style="width: 10%; border: 1px solid #dddddd;">조회수</td>
						<td colspan="2" style="width: 200px; border: 1px solid #dddddd;"><%=view%>
						</td>
					</tr>
					<tr>
						<td style="width: 20%; border: 1px solid #dddddd;">작성자</td>
						<td colspan="2" style="width: 300px; border: 1px solid #dddddd;"><%=user%></td>
						<td style="width: 20%; border: 1px solid #dddddd;">작성일</td>
						<td colspan="2" style="width: 200px; border: 1px solid #dddddd;"><%=time%></td>
					</tr>
					<tr>
						<td colspan="5" style="border: 1px solid #dddddd;">내용</td>
					</tr>
					<tr>
						<td colspan="5"
							style="height: 200px; text-align: left; border: 1px solid #dddddd;">
							<%=text.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br/>")%></td>
						<!-- 뒤에.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") -->
					</tr>
				</tbody>
			</table>
			<!-- ----------------------------------------------- 글 보여주는곳 끝 --------------------------------------------------------- -->
			<div align="center">
				<a href="noticeboard.jsp" class="btn btn-primary" style="text-decoration: none;">목록으로</a>
				<%
					//글작성자 본인일시 수정 삭제 가능 
					if (user_id != null) {
						if (user_id.equals(user)) {
				%>
				<a href="/Nabong_writer/UpdateServlet?board_no=<%=board_no%>"
					class="btn btn-primary" style="text-decoration: none;">수정하기</a> <a
					onclick="return confirm('글을 삭제하시겠습니까? 삭제하시면 복구할 수 없습니다!')"
					href="/Nabong_writer/DeleteServlet?board_no=<%=board_no%>"
					class="btn btn-primary" style="text-decoration: none;">삭제하기</a>
				<%
						}
					}
				%>
			</div>
			<br>
			<!-- --------------------------------------------------댓글 보여주는곳---------------------------------------------------------- -->
			<script type="text/javascript">
				//답글 안보였다가 누르면 보이고 또누르면 안보임
				function doDisplay() {
					var re = document.getElementById("recomment");
					var co = document.getElementById("comment");
					if (re.style.visibility == 'hidden') {
						re.style.visibility = 'visible';
						co.style.visibility = 'hidden';
					} else {
						re.style.visibility = 'hidden';
						co.style.visibility = 'visible';
					}
				}
			</script>

			<div id="comment-border" style="align-content: center; border: 1px solid #dddddd;">
				<h3 align="center" style="text-align: center; height: 10px;">댓글창</h3>
				<form method="post" action="/Nabong_writer/CommentServlet?board_no=<%=board_no%>"
					style="margin: 0">
					<table style="text-align: center; border: 1px solid #dddddd; line-height: 2.4em;">
						<tr align="right">
							<th style="background-color: #eeeeee;" colspan="3">댓글 : <%=commentboard.size()%>개
							</th>
						</tr>
						<tr align="center">
							<th width="120px"><b>작성자</b></th>
							<th width="720px">내용</th>
							<th width="180px"><b>작성 일자</b></th>
						</tr>
						<%
							//dao를 선언하고 배열을 불러와서 값이 있으면

							if (!commentboard.isEmpty()) {
								for (/*WriteVo i: board*/ int i = 0; i < commentboard.size(); i++) {
						%>
						<tr align="center">
							<!-- href를 서블릿으로 바꿔야ㅚㄹ거같은데? -->
							<td><%=commentboard.get(i).getComment_user()%></td>
							<td><%=commentboard.get(i).getComment_text()%></td>
							<td><%=commentboard.get(i).getComment_time()%><br>
 						 	<% if (user_id.equals(commentboard.get(i).getComment_user())) {
 								%>
								<!-- <a href="javascript:update_comment();">수정하기</a>  -->
								<a href="/Nabong_writer/DeleteCommentServelt?board_no=<%=board_no%>&comment_no=<%=commentboard.get(i).getComment_no()%>">
									삭제하기</a><br> <%
 								} //바로위 if문 끝
 								%>  <a href="javascript:doDisplay();">답글달기</a></td>
							<%
									}//for문 끝
								} else {
							%>
						
						<tr align="center">
							<td colspan="4"><b> 댓글이 없습니다! </b></td>

							<%
								}
							%>
						</tr>
						<tr align="center" id="comment">
							<td><%=user_id%></td>
							<td><textarea name="comment" placeholder="소중한 댓글을 입력해주세요." style="resize: none; width: 500px; height: 30px;"></textarea></td>
							<td><button type="submit">댓글달기</button></td>
						</tr>
						<tr align="center" id="recomment" style="visibility: hidden;">
							<td><%=user_id%></td>
							<td><textarea name="recomment" placeholder="답글을 입력해주세요." style="resize: none; width: 500px; height: 30px;"></textarea></td>
							<td><button type="submit">답글달기</button></td>
							
						</tr>
					</table>
				</form>
			</div>
			<!-- ----------------------------------------------- 댓글 작성하는 곳 --------------------------------------------------------- -->
		</div>
		<!-- ----------------------------------------------- 댓글 작성하는 곳 끝--------------------------------------------------------- -->
	</div>
</body>
</html>