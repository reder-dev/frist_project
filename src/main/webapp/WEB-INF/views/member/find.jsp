<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f8f8;
        text-align: center;
    }
    .container {
        width: 60%;
        margin: 50px auto;
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        font-size: 24px;
        margin-bottom: 20px;
    }
    .option-container {
        display: flex;
        justify-content: center;
        gap: 50px;
        margin-top: 20px;
    }
    .option {
        width: 250px;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        cursor: pointer;
        transition: 0.3s;
    }
    .option:hover {
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }
    .option img {
        width: 100%;
        height: auto;
        border-radius: 5px;
    }
</style>
</head>
<body>

<div class="container">
    <h1>아이디/비밀번호 찾기</h1>
    <div class="option-container">
        <div class="option" onclick="location.href='/member/find/email'">
            <img src="email-icon.png" alt="이메일 인증">
            <p>등록한 e-mail 주소 인증 후 재발급</p>
        </div>
        <!-- <div class="option" onclick="location.href='/member/find/phone'">
            <img src="phone-icon.png" alt="휴대폰 인증">
            <p>등록한 휴대폰 번호 인증 후 재발급</p>
        </div> -->
    </div>
</div>

</body>
</html>