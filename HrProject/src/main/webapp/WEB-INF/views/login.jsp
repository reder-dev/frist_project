<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HR Genie - 로그인</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
    <div class="login-container">
        <div class="login-form">
            <h2>HR Genie</h2>
            <form action="<c:url value='/login' />" method="post">
                <div class="form-group">
                    <label for="username">아이디</label>
                    <input type="text" id="username" name="username" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn-login">로그인</button>
            </form>
        </div>
    </div>
</body>
</html>