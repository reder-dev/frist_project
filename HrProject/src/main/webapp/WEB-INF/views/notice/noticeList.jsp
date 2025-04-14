<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>공지사항 목록</h2>
	<table border="1">
   		 <tr>
        	<th>번호</th><th>제목</th><th>작성자</th><th>등록일</th>
    	</tr>
    	<c:forEach var="notice" items="${noticeList}">
        	<tr>
            	<td>${notice.not_id}</td>
            	<td><a href="detail?not_id=${notice.not_id}">${notice.not_ti}</a></td>
            	<td>${notice.emp_id}</td>
            	<td>${notice.not_registdate}</td>
       		</tr>
    	</c:forEach>
	</table>
	<a href="write">[공지 작성]</a>
	
</body>
</html>