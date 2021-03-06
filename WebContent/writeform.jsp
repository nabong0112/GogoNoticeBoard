<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성하기</title>
<% String user_id = (String)request.getAttribute("user_id");
if(user_id == null){

%>
<script type="text/javascript">
	alert("비회원은 글을 작성할 수 없습니다!");
	location.href = "login";
</script>
<% } %>
</head>
<body>
	<div id="container">
		<div id="header" style="height: 150px; text-align: right;">
			<a href="/bbs/main"><img src="image/Oh_main.gif" width="200px"
				height="150px" align="left"></a>
				<% if(user_id == null) { %>
				<b>로그인해주세요!</b> <input type="button" class="dropbtn" name="login" value="로그인" onclick="javascript:login();">
				<% } else { %>
					<b>회원 <%= user_id %></b>님 안녕하세요! 
					<div class="dropdown">
						<button id="myMenu" class="dropbtn">내 메뉴</button>
							<span id ="myDropdown" class="dropdown-content">
								<a href="/bbs/myPage">내 정보</a>
								<a href="/bbs/myPage">내 정보</a>
								<a href="/bbs/myWriteView">내가 작성한 글</a>
							</span>
					</div>
			<input type="button" class="dropbtn" name="myPage" value="내 정보" onclick="javascript:myPage();"> 
			<input type="button" class="dropbtn" name="logout" value="로그아웃" onclick="javascript:logout();">
			<% } %>
		</div>
		<script type="text/javascript">
			function logout() {
				var form = document.logout;
				var bool = confirm('로그아웃 하시겠습니까?');
				if (bool == true) {
					alert("안녕히가세요!");
					location.href = "loginform.jsp";
				} else {
					location.replace("#");
				}
			}
		</script>
		<div align="center" style="line-height: 2.3em">
			<form name="write" method="post" action="/Nabong_writer/WriteServlet">
				<!-- 거 게시판 서블릿 만들어서 보내야됨ㅁㄹㄴㄴㅇㄹ-->
				<h2>게시글 작성</h2>
				<fieldset style="width: 70%; height: 90%;">
					제목 : <input type="text" size="30px" id="title" name="title"
						placeholder="제목을 입력하세요" required><br> 작성자 :
					<%=user_id%>
					<br> 내용 <br>
					<textarea name="text" id="text"
						style="resize: none; width: 70%; height: 500px; text-align: left;"
						placeholder="내용을 입력하세요"></textarea>
					<br> <input type="file" name="file"><br> <input
						type="button" name="write" value="등록하기"
						onclick="javascript:update();"> <input type="button"
						name="back" value="돌아가기" onclick="javascript:Back();">
					<script type="text/javascript">
						function update() {
							var form = document.write;
							var bool = confirm('등록 하시겠습니까?');
							if (bool == true) {
								if (!form['title'].value) {
									alert('제목을 입력해 주세요!');
									form['title'].focus();
									return false;
								} else if (!form['text'].value) {
									alert('글을 입력해 주세요!');
									form['text'].focus();
									return false;
								} else {
									form.submit();
								}
							} else {
								return false;
							}
						}
					</script>
					<script type="text/javascript">
						function Back() {
							var bool = confirm('돌아가시겠습니까? 지금까지 작성한 글은 저장되지 않습니다!');
							if (bool == true) {
								location.href = "NoticeBoardServelet"
							} else {
								return false;
							}

						}
					</script>

				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>