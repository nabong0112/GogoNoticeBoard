<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% String user_id = (String)session.getAttribute("user_id");
    String user_name = (String)session.getAttribute("user_name"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나봉게시판에 오신것을 환영합니다</title>
</head>
<body>
<div id="container">
	<div id ="header" style="background-color:#FFA777;height: 50px">  <!-- 로그아웃 action값에 저거 말고 로그인 확인하는 폼(logout_ok.java) 만들어야됨 세션 끊으면서 안녕히가세요! 라는 alter인가-->
	<%= user_name %> , <%= user_id %>님 안녕하세요! <a href="#" id="mymenu">내 정보</a> <a href="loginform.jsp" id="logout">로그아웃</a>
	</div>
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
	<div id="border" style="background-color: #EEEEEF;height: 733px;">
		<fieldset style="height: 700px">
			<br><table align="center" >
				<tr align="center" bgcolor="gray">
				<th width="80"><b>번호</b></th>
				<th width="640"><b>제목</b></th>
				<th width="100"><b>작성자</b></th>
				<th width="80"><b>조회수</b></th> 
				</tr>
				<tr align="center">
					<td>border_no</td>
					<td>border_name</td>
					<td>user_id</td>
					<td>view</td>
				</tr>
			</table>
		</fieldset>
	</div>
	</div>
</div>
</body>
</html>