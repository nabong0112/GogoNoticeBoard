<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join View</title>
</head>
<body>
<div align="center" style ="line-height:2.3em">
	<form method="post" action="#">
	<h3> 회원가입 </h3>
	<fieldset style="width: 550px">
		<legend>입력사항</legend>
			
				<label for ="loginNAME">이름 : </label>
				<input type="text" size = "15" id="loginNAME" name="login"><br>
				<label for ="loginID">아이디 : </label>
				<input type="text" size = "15" id="loginID" name="login"><br>
				<label for ="loginPW">비밀번호 :</label>
				<input type="password" size = "15" id="loginPW" name="login"><br>
				<label for ="loginPW_ok">비밀번호 확인 :</label>
				<input type="password" size = "15" id="loginPW_ok" name="login"><br>	
				<input type="submit" id="loginID" value="   가입하기   "><br>
				앗 ! 생각해보니 이미 계정이 있으신가요? <a href="loginform.jsp" name="login">로그인 하러 가기</a>
	</fieldset>		
	</form>
</div>
</body>
</html>