<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>공지 작성</h2>
		<form action="write" method="post">
  			  제목: <input type="text" name="not_ti" /><br/>
   			 내용: <textarea name="not_cn" rows="5" cols="40"></textarea><br/>
    		첨부파일: <input type="text" name="not_file" /><br/>
    		사원번호: <input type="text" name="emp_id" /><br/>
    		<input type="submit" value="등록" />
	</form>
	

</body>
</html>