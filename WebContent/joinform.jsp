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
	<form name = "join" action="/Nabong_writer/TestServlet" method="post" >
	<h3> 회원가입 </h3>
	<fieldset style="width: 550px">
		<legend>입력사항</legend>
			
				<label for ="user_id">아이디 : </label>
				<input type="text" size = "20" id="user_id" name="user_id" placeholder="4글자 이상 10글자 이하"><br>
				<label for ="loginID">이름 : </label>
				<input type="text" size = "20" id="user_name" name="user_name" placeholder="2글자 이상 6글자 이하"><br>
				<label for ="loginPW">비밀번호 :</label>
				<input type="password" size = "20" id="user_pw" name="user_pw" placeholder="5글자 이상 12글자 이하"><br>
				<label for ="loginPW_ok">비밀번호 확인 :</label>
				<input type="password" size = "20" id="check_pw" name="check_pw" placeholder="비밀번호와 똑같이 입력"><br>
				<input type="button" value="   가입하기   " onclick="check()"><br>
				<b>앗 ! </b>생각해보니 이미 계정이 있으신가요? <a href="loginform.jsp"><b>로그인 하러 가기</b></a>
				
				<script type="text/javascript">
				function check() {
				    var form = document.join;

				    if(!form['user_id'].value) {
				        alert("어!! 아이디를 빼먹으셨네!");
				        form['user_id'].focus();
				        return false;
				    } else if(!form['user_pw'].value){
				    	alert("어!! 비밀번호를 빼먹으셨네!");
				        form['user_pw'].focus();
				        return false;
				    } else if(!form['check_pw'].value){
				    	alert("어!! 비밀번호 한번더 입력하셔야죠!");
				        form['check_pw'].focus();
				        return false;
				    } else if(!form['user_name'].value){
				    	alert("어!! 이름을 빼먹으셨네!");
				        form['user_name'].focus();
				        return false;
				    } else{
				    	form.submit();
				    }		 				    
				}
				
				</script>
	</fieldset>		
	</form>
</div>
</body>
</html>