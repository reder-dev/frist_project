<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HR Genie</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    <script src="<c:url value='/resources/js/script.js' />"></script>
</head>
<body>
    <header class="main-header">
        <div class="logo">
            <a href="<c:url value='/user/main' />">
                <h1>HR Genie</h1>
            </a>
        </div>
        <div class="user-info">
            <div class="notification">
                <a href="<c:url value='/notifications' />">
                    <img src="<c:url value='/resources/img/bell.png' />" alt="알림">
                </a>
            </div>
            <span class="login-time">로그인시간 59:59</span>
            <a href="<c:url value='/logout' />" class="logout-btn">로그아웃</a>
        </div>
    </header>
    <div class="container">