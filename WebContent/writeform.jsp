<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String user_id = (String)session.getAttribute("user_id");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
    	if(user_id == null) {%>
    	
    		<script type="text/javascript">
    		alert("엥! 누구세요!");
    		location.href = "loginform.jsp";
			</script>
    		
    		
    	 <% } %>
</head>
<body>
<div align="center" style ="line-height:2.3em">
	<form name ="write" method="post" action="/Nabong_writer/WriteServlet"> <!-- 거 게시판 서블릿 만들어서 보내야됨ㅁㄹㄴㄴㅇㄹ-->
		<h2> 게시글 작성 </h2>
			<fieldset style= "width: 70%; height: 90%;">		
				제목 : <input type="text" size="30px" id="title" name ="title" placeholder="제목을 입력하세요" required ><br>
				작성자 : <%= user_id %> <br>
				내용 <br>
				<textarea name ="text" id="text" style="resize: none; width: 70%; height: 500px; text-align: left;" placeholder="내용을 입력하세요"></textarea><br>
				
				<input type="button" name="write" value="등록하기" onclick="javascript:update();">
				<input type="button" name="back" value="돌아가기" onclick="javascript:back();">
				<script type="text/javascript">
				function update(){
					var form = document.write;
					var bool = confirm('등록 하시겠습니까? 문자 앞 공백은 삭제 후 등록됩니다');
					if(bool == true){ 
						if(!form['title'].value){
							
							alert('제목을 입력해 주세요!')
							form['title'].focus();
							 return false;
						}		
						else if(!form['text'].value){
							alert('글을 입력해 주세요!')
							form['text'].focus();
							 return false;
						}
						else{
							form.submit();
							}	
					}else{
						location.replace("writeform.jsp");
					}
				}
				</script>	
				<script type="text/javascript">
				function back(){
					location.href="noticeboard.jsp"
				}
				</script>
				
	</fieldset>
	</form>
</div>
</body>
</html>