<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HR Genie 회원가입</title>
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
        width: 350px;
    }
    h1 {
        margin-bottom: 20px;
        font-size: 24px;
        font-weight: bold;
    }
    h2 {
        margin-bottom: 10px;
        font-size: 18px;
        font-weight: normal;
        color: #888;
    }
    input {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .links {
        display: flex;
        justify-content: space-between;
        font-size: 14px;
        margin-bottom: 20px;
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
    <div class="container" id="register">
        <h1>HR Genie</h1>
        <h2>회원가입</h2>
        <form method="POST" action="/member/join">
            <input type="text" name="firstname" placeholder="First Name">
            <input type="text" name="lastname" placeholder="Last Name"> <br>
            <input type="text" name="emp_id" placeholder="사원번호(ID)"> <br>
            <input type="email" name="email" placeholder="Email">
            <input type="text" name="phone" placeholder="전화번호"> <br>
            <input type="text" name="rank" placeholder="직급">
            <input type="text" name="position" placeholder="직책"> <br>
            <input type="text" name="department" placeholder="부서"> <br>
            <input type="password" name="emp_pw" placeholder="비밀번호">
            <input type="password" name="emp_pw_confirm" placeholder="비밀번호 재확인"> <br>
            <input type="text" name="emp_address" placeholder="주소"> <br>
            <button type="submit">회원가입!</button>
        </form>
    </div>
</body>
</html>
