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
		
		<!-- 메뉴 영역 -->
	    <h2>메뉴</h2>
	    
	    <!-- 세션에서 전달된 role_id 값을 이용하여 메뉴 선택 -->
	    <c:choose>
	        <%-- 최고 관리자 메뉴 (role_id == 1) --%>
	        <c:when test="${role_id == '1'}">
	            <ul>
	                <li><a href="/admin/dashboard">대시보드</a></li>
	                <li><a href="/admin/manage-users">사용자 관리</a></li>
	                <li><a href="/admin/reports">보고서</a></li>
	                <li><a href="/admin/settings">설정</a></li>
	            </ul>
	        </c:when>
	        
	        <%-- 직원 메뉴 (role_id == 4) --%>
	        <c:when test="${role_id == '4'}">
	            <ul>
	                <li><a href="/employee/dashboard">대시보드</a></li>
	                <li><a href="/employee/profile">내 프로필</a></li>
	                <li><a href="/employee/tasks">내 업무</a></li>
	            </ul>
	        </c:when>
	        
	        <%-- 권한이 없는 경우 (role_id가 설정되지 않거나 다른 값일 경우) --%>
	        <c:otherwise>
	            <ul>
	                <li><a href="../login">로그인</a></li>
	            </ul>
	        </c:otherwise>
	    </c:choose>
		
	</body>	
	</html>