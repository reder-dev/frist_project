<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HR Genie 로그인</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f8f8f8;
        font-family: Arial, sans-serif;
    }
    .login-container {
        background: white;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 350px;
    }
    .login-container h1 {
        margin-bottom: 20px;
    }
    .login-container h3{
    	font-weight:normal;	
    }
    .login-container input {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .login-container .links {
        display: flex;
        justify-content: space-between;
        font-size: 14px;
        margin-bottom: 20px;
    }
    .login-container button {
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
    <div class="login-container">
        <h3>HR Genie</h3>
        <h1>LOGIN</h1>
        <p>아이디 : 사원번호</p>
        <form method="POST">
            <input type="text" name="emp_id" placeholder="ID"> <br>
            <input type="password" name="emp_pw" placeholder="password"> <br>
            <div class="links">
                <!-- <a href="/member/join">회원가입</a> -->
                <a href="/member/find">아이디 / 비밀번호 찾기</a>
            </div>
            <button type="submit">로그인</button>
        </form>    
    </div>
</body>
</html>
