<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>HR Genie - 메인화면</title>
    <style>
        
    </style>
</head>
<body>

<div class="sidebar">
    <ul>
        <li class="active">인사관리</li>
        <li>근태관리</li>
        <li>급여관리</li>
        <li>전자결재</li>
        <li>공지사항</li>
    </ul>
</div>

<div class="content">
    <div class="logout-btn">
        <span>자동 로그인 남은시간 <b>59:59</b></span>
        <button>로그아웃</button>
    </div>

    <h2>이번달 일정 캘린더</h2>
    <div class="calendar">
        <!-- 캘린더 생략 -->
    </div>

    <div class="user-box">
        <img src="/resources/images/user_placeholder.png" alt="사용자 이미지">
        <h3>${sessionScope.loginUser.emp_name} 님</h3>
        <p>${sessionScope.loginUser.dep_name} / ${sessionScope.loginUser.rank_id}</p>
    </div>

    <div class="memo">
        <strong>MEMO</strong>
        <p>여기에 메모를 입력하세요...</p>
    </div>
</div>

</body>
</html>