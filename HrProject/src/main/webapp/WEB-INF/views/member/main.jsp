	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>	
		<h1> 메인페이지 입니다.</h1>
		<c:if test="${not empty message}">
	   		<p>${message}</p>
		</c:if>
		
	</body>	
	</html>