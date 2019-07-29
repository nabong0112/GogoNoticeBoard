<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.dao.WriteDao"%>
<%@ page import="main.vo.WriteVo"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<% WriteDao dao = new WriteDao();
	%>
<body>
<% 	
	String user_id = (String)session.getAttribute("user_id"); 

	int board_no = Integer.parseInt(request.getParameter("board_no")); 
	request.setAttribute("board_no2", board_no);
	String title =(String)request.getAttribute("title");
	String text = (String)request.getAttribute("text");
	String user = (String)request.getAttribute("user");
	dao.getBoard(board_no);
	%>
	
   	<% if(user_id == null) {%>
    	
    		<script type="text/javascript">
    		alert("비회원은 글을 읽을 수 없습니다!");
    		location.href = "loginform.jsp";
			</script>	
    		
    	 <% }  else if(!user_id.equals(user)){ %> 
    	 
    	 <script type="text/javascript">
    		alert("글 작성하신 본인만 수정 가능합니다!");
    		history.back();
			</script>	
			
		<%	} %>
	<div class="container">
		<div id ="header" style="background-color:#FFFFFE;height: 50px">  <!-- 로그아웃 action값에 저거 말고 로그인 확인하는 폼(logout_ok.java) 만들어야됨 세션 끊으면서 안녕히가세요! 라는 alter인가-->
	<b>회원 <%= user_id %></b>님 안녕하세요! <a href="myPage.jsp">내 정보</a> 
	<input type="button" name="logout" value ="로그아웃" onclick="javascript:logout();">
		</div>
	<script type="text/javascript">
	function logout(){
		var form = document.logout;
		var bool = confirm('로그아웃 하시겠습니까?');
		if(bool == true){
			alert("안녕히가세요!");
			location.href = "loginform.jsp";
			session.invalidate();
	
		} else{
			location.replace("#");
		}
	}
	
	</script>
		<div align="center" style ="line-height:2.3em">
	<form name ="write" method="post" action="/Nabong_writer/UpdateOkServelt?board_no=<%= board_no %>"> <!-- 거 수정하는 서블렛으로 글 보내서 수정해야됨 -->
		<h2><%= board_no %>번째 게시글 수정 </h2>
		
			<fieldset style= "width: 70%; height: 90%;">		
				제목 : <input type="text" size="30px" id="title" name ="title" value= "<%= title %>"><br>
				작성자 : <%= user_id %> <br>
				내용 <br>
				<textarea name ="text" id="text" style="resize: none; width: 70%; height: 500px; text-align: left;"><%= text %></textarea><br>
				<input type="button" name="write" value="수정하기" onclick="javascript:update();">
				<input type="button" name="back" value="돌아가기" onclick="javascript:Back();">
				<script type="text/javascript">
				function update(){
					var form = document.write;
					var bool = confirm('수정 하시겠습니까? 문자 앞 공백은 삭제 후 등록됩니다');
					if(bool == true){ 
						if(!form['title'].value){
							
							alert('제목을 입력해 주세요!');
							form['title'].focus();
							 return false;
						}		
						else if(!form['text'].value){
							alert('글을 입력해 주세요!');
							form['text'].focus();
							 return false;
						}
						else{
							form.submit();
							}	
					}else{
						return false;
					}
				}
				</script>	
				<script type="text/javascript">
				function Back(){
					var bool = confirm('돌아가시겠습니까? 지금까지 작성한 글은 저장되지 않습니다!');
					if(bool == true){
						location.href="noticeboard.jsp"
					} else{
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