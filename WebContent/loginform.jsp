<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <% String user_id = request.getParameter("user_id"); 
  	 session.setAttribute("user_id", "user_id");
  
  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login View</title>
</head>
<body>
	<!-- #에 꼭 넘어갈 페이지 넣어주333-->
<div align="center" style ="line-height:2.3em">
	<form method="post" action="/Nabong_writer/LoginServlet"> <!-- action값에 저거 말고 로그인 확인하는 폼(Login_ok.java) 만들어야됨-->
		<h3> 게시판에 입장하기 위해 로그인이 필요합니다 </h3>
	<fieldset style="width: 300px">
		<legend>로그인</legend>
			
				<label for ="user_id">아이디 : </label>
				<input type="text" size = "15" id="user_id" name="user_id"><br>
				<label for ="user_pw">비밀번호 :</label>
				<input type="password" size = "15" id="user_pw" name="user_pw"><br>	
				<input type="submit" name="loginID" value="   로그인   "><br>
				계정이 없으신가요?? <a href="joinform.jsp" id="join">가입하러 가기</a>
				
	</fieldset>
	</form>
</div>
</body>
</html>