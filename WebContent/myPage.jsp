<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String myid = (String) session.getAttribute("user_id"); 
	String myname = (String)session.getAttribute("user_name");
	String mypw = (String)session.getAttribute("user_pw");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지</title>
</head>
<body>
<div align="center">
<fieldset style="width: 550px" align="center">
<legend>내 정보</legend>
	내 아이디 : <b> <%= myid %> </b>  <br>
	내 이름 : <b> <%= myname %> </b><br>
	내 비밀번호 : <b> <%= mypw %> </b><br>
	 <button onClick="javascript:Update();">변경하기</button> <button onClick="">탈퇴하기</button>
	<a href="noticeboard.jsp">메인으로</a>
</fieldset>
</div>

	
	<script type="text/javascript">
		function Update(){
			location.href = "changeform.jsp";
		}
	</script>
</body>
</html>