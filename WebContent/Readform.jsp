<%@page import="java.sql.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.dao.WriteDao"%>
<%@ page import="main.vo.WriteVo"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<% WriteDao dao = new WriteDao();
	WriteVo vo = new WriteVo();
	%>
<body>
<% 	String user_id = (String)session.getAttribute("user_id"); 
	int num = Integer.parseInt(request.getParameter("board_no")); 
	String title = request.getParameter("title");
	String text = request.getParameter("text");
	String user = request.getParameter("user");
	String time = request.getParameter("time");
	%>
   	<% if(user_id == null) {%>
    	
    		<script type="text/javascript">
    		alert("비회원은 글을 읽을 수 없습니다!");
    		location.href = "loginform.jsp";
			</script>	
    		
    	 <% }  %> 
	<div class="container">
		<div id ="header" style="background-color:#FFFFFE;height: 50px">  <!-- 로그아웃 action값에 저거 말고 로그인 확인하는 폼(logout_ok.java) 만들어야됨 세션 끊으면서 안녕히가세요! 라는 alter인가-->
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
			location.replace("#");
		}
	}
	
	</script>
		<div class="row" align="center">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd; line-height:2.3em;">
					<thead>
						<tr>
							<th colspan="7" style="background-color: #eeeeee; text-align: center;">글 보기 </th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%; border: 1px solid #dddddd;"> 글 제목 </td>
							<td colspan="2" style="width: 300px; border: 1px solid #dddddd;"> <%= title %> </td>
							
							<td style="width: 20%; border: 1px solid #dddddd;"> 조회수 </td>
							<td colspan="2" style="width: 300px; border: 1px solid #dddddd;"> </td>
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
						<%= text %></td>
						<!-- 뒤에.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") -->
						</tr>
					</tbody>
				</table>	

				<a href = "noticeboard.jsp" class="btn btn-primary">목록</a>
				  <%

				//글작성자 본인일시 수정 삭제 가능 
					if(user_id != null){
					if(user_id.equals(vo.getBoard_user())){

				%>

						<a href="update.jsp?bbsID=" class="btn btn-primary">수정</a>

						<a href="delete.jsp?bbsID=" class="btn btn-primary">삭제</a>

				<%					

					}

				%>  
				<% 
				}
				
				%>
		</div>

	</div>
</body>
</html>