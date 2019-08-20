<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String user_id = (String) session.getAttribute("user_id");
	String myname = (String) session.getAttribute("user_name");
	String mypw = (String) session.getAttribute("user_pw");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지</title>
</head>
<body>
	<div id="container">
		<div id="header" style="background-color: #FFFFFE; height: 50px">
			<!-- 로그아웃 action값에 저거 말고 로그인 확인하는 폼(logout_ok.java) 만들어야됨 세션 끊으면서 안녕히가세요! 라는 alter인가-->
			<b>회원 <%=user_id%></b>님 안녕하세요! <a href="myPage.jsp">내정보</a> 
			<input type="button" name="logout" value="로그아웃" onclick="javascript:logout();">
		</div>
		<script type="text/javascript">
			function logout() {
				var form = document.logout;
				var bool = confirm('로그아웃 하시겠습니까?');
				if (bool) {
					alert("안녕히가세요!");
					location.href = "loginform.jsp";
					session.invalidate();

				} else {
					location.replace("#");
				}
			}
		</script>
		<div align="center">
			<fieldset style="width: 550px" align="center">
				<legend>내 정보</legend>
				내 아이디 : <b> <%=user_id%>
				</b> <br> 내 이름 : <b> <%=myname%>
				</b><br> 내 비밀번호 : <b> <%=mypw%>
				</b><br>
				<button onClick="javascript:Update();">변경하기</button>
				<button onClick="">탈퇴하기</button>
				<a href="NoticeBoardServelet">메인으로</a>
			</fieldset>
		</div>


		<script type="text/javascript">
			function Update() { //미구현
				location.href = "changeform.jsp"; 
			}
		</script>
	</div>
</body>
</html>