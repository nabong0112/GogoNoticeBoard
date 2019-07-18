<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String myid = (String) session.getAttribute("insert_id"); 
	String myname = (String)session.getAttribute("user_name");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>-- My Page --</title>
</head>
<body>
	내 아이디 : <%= myid %> <br>
	내 이름 : <%= myname %><br>
	<a href="noticeboard.jsp">메인으로</a>
</body>
</html>