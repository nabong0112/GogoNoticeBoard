<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>데이터가 들어가냐</title>
</head>
<body>
<form action="/Nabong_writer/TestServlet" method="post">
        <p>아이디 : <input type="text" name="user_id"></p>
        <p>비밀번호 : <input type="password" name="user_pw"></p>
        <p>이름 : <input type="text" name="user_name"></p>
        <p><input type="submit" value="회원가입"></p>
    </form>
</body>
</html>