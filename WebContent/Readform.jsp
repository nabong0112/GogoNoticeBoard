<%@page import="java.sql.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.dao.WriteDao"%>
<%@ page import="main.vo.WriteVo"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<% WriteDao dao = new WriteDao();
	%>
<body>
<% 	
	dao.getBoard(Integer.parseInt(request.getParameter("board_no")));
	String user_id = (String)session.getAttribute("user_id"); 
	int num = Integer.parseInt(request.getParameter("board_no")); 
	String title = (String)request.getAttribute("title");
	String text = (String)request.getAttribute("text");
	String user = (String)request.getAttribute("user");
	String time = (String)request.getAttribute("time");
	int view = (Integer)request.getAttribute("view");
	
	%>
   	<% if(user_id == null) {%>
    	
    		<script type="text/javascript">
    		alert("비회원은 글을 읽을 수 없습니다!");
    		location.href = "loginform.jsp";
			</script>	
    		
    	 <% } %>
	<div class="container">
		<div id ="header" style="background-color:#FFFFFE;height: 50px">  <!-- 로그아웃 action값에 저거 말고 로그인 확인하는 폼(logout_ok.java) 만들어야됨 세션 끊으면서 안녕히가세요! 라는 alter인가-->
		<a href="noticeboard.jsp"><img alt="메인으로" src="/image/notice.png" width="200px" height="100px"></a>
	<b>회원 <%= user_id %></b>님 안녕하세요! <a href="myPage.jsp">내 정보</a> 
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
			location.replace("#");
		}
	}
	
	</script>
		<div class="row" align="center">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd; line-height:2.3em;">
					<thead>
						<tr>
							<th colspan="7" style="background-color: #eeeeee; text-align: center;"><%= num %>번째 글 </th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%; border: 1px solid #dddddd;"> 글 제목 </td>
							<td colspan="2" style="width: 400px; border: 1px solid #dddddd;"> <%= title %></td>
							
							<td style="width: 10%; border: 1px solid #dddddd;"> 조회수 </td>
							<td colspan="2" style="width: 300px; border: 1px solid #dddddd;"><%= view %> </td>
						</tr>
						<tr>
							<td style="width: 20%; border: 1px solid #dddddd;">작성자</td>	
							<td colspan="2" style="width: 300px; border: 1px solid #dddddd;"><%= user %></td>
							<td style="width: 20%; border: 1px solid #dddddd;">작성일</td>	
							<td colspan="2" style="width: 300px; border: 1px solid #dddddd;"><%= time %></td>
						</tr>
						<tr>
							<td colspan="5" style="border: 1px solid #dddddd;">내용</td>
						</tr>
						<tr>
						<td colspan="5" style="height: 200px; text-align: left; border: 1px solid #dddddd;">
						<%= text.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") %></td>
						<!-- 뒤에.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") -->
						</tr>
					</tbody>
				</table>	

				<a href = "noticeboard.jsp" class="btn btn-primary">목록</a>
				  
				  
				  
				  
				  
				  <%
					//글작성자 본인일시 수정 삭제 가능 
					if(user_id != null){
						if(user_id.equals(user)){

				  %>
				<a href="/Nabong_writer/UpdateServlet?board_no=<%= num %>" class="btn btn-primary">수정하기</a>
				<a onclick="return confirm('글을 삭제하시겠습니까? 삭제하시면 복구할 수 없습니다!')" href="/Nabong_writer/DeleteServlet?board_no=<%= num %>" class="btn btn-primary">삭제하기</a>
					<% }
					}
				%>
		</div>

	</div>
</body>
</html>