<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f8f8f8;
        font-family: Arial, sans-serif;
    }
    .container {
        background: white;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 500px;
    }
    .container h1 {
        margin-bottom: 20px;
    }
    .input-group {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }
    .input-group input {
        flex: 1;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-right: 10px;
    }
    .auth-button, .verify-button {
        padding: 10px 15px;
        background: #D3D3D3;
        color: black;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 12px;
    }
    .verify-button {
        background: #D3D3D3;
    }
    button {
        width: 100%;
        padding: 10px;
        background: black;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    
</style>
</head>
<body>
    <div class="container">
        <h1>아이디/비밀번호 찾기</h1>
        <form action="/member/find/phone" method="POST">
            <div class="input-group">
                <input type="text" name="emp_id" placeholder="사원번호(ID)">
            </div>
            <div class="input-group">
                <input type="tel" name="phone" placeholder="전화번호">
                <button type="button" class="auth-button">인증코드 보내기</button>
            </div>
            <div class="input-group">
                <input type="text" name="auth_code" placeholder="인증코드 입력">
                <button type="button" class="verify-button">인증코드 확인</button>
            </div>
            <div class="input-group">
                <input type="password" name="new_password" placeholder="새로운 비밀번호 입력">
            </div>
            <div class="input-group">
                <input type="password" name="confirm_password" placeholder="비밀번호 재확인">
            </div>
            <button type="submit">send</button>
        </form>
    </div>
</body>
</html>
