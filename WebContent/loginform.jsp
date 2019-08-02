<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login View</title>
</head>
<body>
	<!-- #에 꼭 넘어갈 페이지 넣어주333-->
	<div align="center" style="line-height: 2.3em">
		<form name="login" method="post" action="/Nabong_writer/LoginServlet">
			<!-- action값에 저거 말고 로그인 확인하는 폼(Login_ok.java) 만들어야됨-->
			<h3>게시판에 입장하기 위해 로그인이 필요합니다</h3>
			<fieldset style="width: 300px">
				<legend>로그인</legend>
				<a href="noticeboard.jsp">로그인 안하고 게시판 가기</a><br> <label
					for="insert_id">아이디 : </label> <input type="text" size="15"
					id="insert_id" name="insert_id"><br> <label
					for="insert_pw">비밀번호 :</label> <input type="password" size="15"
					id="insert_pw" name="insert_pw"><br> <input
					type="button" name="loginID" value="   로그인   "
					onclick="logincheck()"><br> 계정이 없으신가요?? <a
					href="joinform.jsp" id="join">가입하러 가기</a>
				<script type="text/javascript">
					function logincheck() {
						var form = document.login;

						if (!form['insert_id'].value) {
							alert("어!! 아이디를 빼먹으셨네!");
							form['insert_id'].focus();
							return false;
						} else if (!form['insert_pw'].value) {
							alert("어!! 비밀번호를 빼먹으셨네!");
							form['insert_pw'].focus();
							return false;
						} else {

							form.submit();

						}
					}
				</script>
			</fieldset>
		</form>
	</div>
</body>
</html>