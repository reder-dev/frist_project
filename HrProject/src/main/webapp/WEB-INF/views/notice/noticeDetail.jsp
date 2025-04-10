<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>공지 상세</h2>
		제목: ${notice.not_ti}<br/>
		내용: ${notice.not_cn}<br/>
		작성자: ${notice.emp_id}<br/>
		등록일: ${notice.not_registdate}<br/>
		수정일: ${notice.not_modifydate}<br/>
		<br/>
		<a href="edit?not_id=${notice.not_id}">[수정]</a>
		<form action="delete" method="post" style="display:inline;">
    		<input type="hidden" name="not_id" value="${notice.not_id}" />
    		<input type="submit" value="삭제" />
		</form>
		<a href="list">[목록으로]</a>

</body>
</html>