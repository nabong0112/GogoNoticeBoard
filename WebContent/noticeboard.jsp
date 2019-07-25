<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="main.dao.WriteDao"%>
<%@ page import="main.vo.WriteVo"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나봉게시판에 오신것을 환영합니다</title>
<% String user_id = (String)session.getAttribute("user_id"); %>
    	<%
    	if(user_id == null) {%>
    	
    		<script type="text/javascript">
    		alert("엥! 누구세요!");
    		location.href = "loginform.jsp";
			</script>
    		
    		
    	 <% }  %>
<script type="text/javascript">
function gowrite(){
	var bool = confirm('글을 작성하시겠습니까?');
	if(bool == true){
	location.href = "writeform.jsp";
	}else{
	location.replace("noticeboard.jsp");
	}
}
</script>
</head>
<body>
<div id="container">
	<div id ="header" style="background-color:#FFA777;height: 50px">  <!-- 로그아웃 action값에 저거 말고 로그인 확인하는 폼(logout_ok.java) 만들어야됨 세션 끊으면서 안녕히가세요! 라는 alter인가-->
	<b>회원 <%= user_id %></b>님 안녕하세요! <a href="myPage.jsp" name="mymenu">내 정보</a> 
	<input type="button" name="logout" value ="로그아웃" onclick="javascript:logout();">
	</div>
	<script type="text/javascript">
	function logout(){
		var form = document.logout;
		var bool = confirm('로그아웃 하시겠습니까?');
		if(bool == true){
			alert("안녕히가세요!");
			location.href = "loginform.jsp";
			session.invalidate();
			
			
			
		} else{
			location.replace("noticeboard.jsp");
		}
	}
	
	</script>
	<div id="content" style="background-color: #EEEEEE;height: 883px;width: 1900px;float: left;">
	<div id ="menu" style="background-color: #FFD700;height: 883px;width: 145px;float: left;">
	<b>Menu</b> <br>
	HTML<br>
	CSS<br>
	</div>
	<div id="serch" style="background-color: #EEEEEF;height: 130px;">
		<fieldset align="center">
			<br><b>게시글 검색 : </b> 
		<input type="text" name="serch" size="40">
		<input type="submit" name="serch" size="20" value="검색"><br><br>
		</fieldset>
		
	</div>
	<div id="border" style="background-color: #EEEEEF; height: 733px; align-content:center;">
		<fieldset style="height: 700px; line-height:2.3em; align-content: center;">
			<br><table style="line-height:2.3em;" align="center">
				<tr align="center" bgcolor="gray">
					<th width="80"><b>번호</b></th>
					<th width="640"><b>제목</b></th>
					<th width="100"><b>작성자</b></th>
					<th width="130"><b>작성 일자</b></th>
					<th width="80"><b>조회수</b></th> 
				</tr>
				<% //dao를 선언하고 배열을 불러와서 값이 있으면
				WriteVo vo = new WriteVo();
				WriteDao dao = new WriteDao(); 
				ArrayList<WriteVo>board = dao.selectText();
				if(!board.isEmpty()){ 
					for(/*WriteVo i: board*/ int i = 0;i < board.size();i++) {
						%>
						<tr align="center">
							<!-- href를 서블릿으로 바꿔야ㅚㄹ거같은데? -->
							<td><a href ="#" onClick="javascript:goservlet();"><%= board.get(i).getBoard_no()%></a></td>
							<td><a href ="Readform.jsp?board_no=<%= board.get(i).getBoard_no() %>"><%= board.get(i).getBoard_title()%></a></td>
							<td><%= board.get(i).getBoard_user()%></td>
							<td><%= board.get(i).getBoard_time()%></td>
							<td><%= board.get(i).getBoard_view()%></td>
							 <% int a = board.get(i).getBoard_no();
							 	System.out.println(a);
							 	System.out.println("-----------------------------");
							 %>
						
						<%  } 
				 } else { %>
				
					<tr align="center">
						<td colspan="5"><b> 등록된 글이 없습니다. 아래 글쓰기를 눌러 첫 게시글의 주인공이 되어보세요!</b> </td>
					
				<% } %>
				<script type="text/javascript">
							function goservlet(){
								
								<% 
								if(vo.getBoard_no() != 2){
								int board_no = board.get(0).getBoard_no();
								System.out.println(board_no);
								}
								%>
								
								location.href = "/Nabong_writer/ReadServlet";
							}
							</script>
			</tr>
			</table>
			<center>
			<br><br><button style="height: 50px; width: 100px;" onClick="javascript:gowrite();">글쓰기</button>
			</center>
			<!-- <input type="button" name="write" style="height: 50px; width: 100px;"  onClick= "javascript:write();">글쓰기</ -->

		</fieldset>
	</div>
	</div>
</div>
</body>
</html>