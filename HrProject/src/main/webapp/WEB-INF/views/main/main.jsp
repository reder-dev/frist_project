<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>HR Genie - 메인화면</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f5f9fc;
            margin: 0;
        }

        .sidebar {
            width: 200px;
            height: 100vh;
            background-color: #e6f2f5;
            float: left;
            padding-top: 30px;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }

        .sidebar ul li {
            padding: 15px 20px;
            cursor: pointer;
        }

        .sidebar ul li:hover {
            background-color: #b2e0db;
        }

        .active {
            background-color: #90e0b0;
            font-weight: bold;
        }

        .content {
            margin-left: 200px;
            padding: 20px;
        }

        .calendar {
            border: 1px solid #ccc;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
        }

        .user-box {
            margin-top: 20px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            text-align: center;
        }

        .user-box img {
            width: 100px;
            height: 100px;
            border: 2px solid green;
            border-radius: 10px;
        }

        .logout-btn {
            float: right;
            margin: 20px;
        }

        .memo {
            margin-top: 20px;
            height: 100px;
            border: 1px solid #ddd;
            padding: 10px;
        }
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