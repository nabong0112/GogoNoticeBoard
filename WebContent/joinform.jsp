<%@page import="com.sun.org.apache.xpath.internal.functions.Function"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		String checkpw = request.getParameter("check_pw");
		String name = request.getParameter("user_name");
	%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join View</title>
</head>
<body>
<div align="center" style ="line-height:2.3em">
	<form action="/Nabong_writer/TestServlet" method="post">
	<h3> 회원가입 </h3>
	<fieldset style="width: 550px">
		<legend>입력사항</legend>
			
				<label for ="loginNAME">아이디 : </label>
				<input type="text" size = "15" id="loginNAME" name="user_id"><br>
				<label for ="loginID">이름 : </label>
				<input type="text" size = "15" id="loginID" name="user_name"><br>
				<label for ="loginPW">비밀번호 :</label>
				<input type="password" size = "15" id="loginPW" name="user_pw"><br>
				<label for ="loginPW_ok">비밀번호 확인 :</label>
				<input type="password" size = "15" id="loginPW_ok" name="check_pw"><br>
				<script type="text/javascript">
				
				if(<%= pw %> != <%= checkpw %>){
				
					alert("비밀번호가 틀립니다 다시 입력해주세요!");
					return false;
				}
				
				
				</script>
				<input type="submit" value="   가입하기   "><br>
				<b>앗 ! </b>생각해보니 이미 계정이 있으신가요? <a href="loginform.jsp"><b>로그인 하러 가기</b></a>
				
	</fieldset>		
	</form>
</div>
</body>
</html>