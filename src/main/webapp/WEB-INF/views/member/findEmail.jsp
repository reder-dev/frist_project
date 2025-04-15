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
        <form action="/member/find/email" method="POST">
            <div class="input-group">
                <input type="text" name="emp_id" placeholder="사원번호(ID)">
            </div>
            <div class="input-group">
                <input type="email" name="email" id="email-input" placeholder="Email">
                <button type="button" class="auth-button" id="send-code">인증코드 보내기</button>
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
    
    <script>
    document.addEventListener("DOMContentLoaded", function () {

        // 인증코드 보내기 버튼
        document.querySelector("#send-code").addEventListener("click", function () {
            const empId = document.querySelector("input[name='emp_id']").value;
            const email = document.querySelector("input[name='email']").value;

            // 값 확인
            console.log("empId:", empId, "emp_email:", email);

            fetch("/auth/sendCode", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Accept': 'application/json'
                },
                body: JSON.stringify({
                    emp_id: empId,
                    email: email
                })
            })
            .then(function(res){return res.json();})
            .then(function(data){alert(data.result.message);})
            //.then(function(msg){console.log(JSON.parse(msg).result.song2)})
            .catch(function(err) {
            	console.error(err);
                alert("인증코드 전송 실패");
            });
        });

        // 인증코드 확인 버튼
        document.querySelector(".verify-button").addEventListener("click", function () {
            const code = document.querySelector("input[name='auth_code']").value;

            fetch("/auth/verifyCode", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Accept': 'application/json'
                },
                body: JSON.stringify({ code: code })
            })
            .then(function(res){return res.json();})
            .then(function(data){alert(data.result.message);})
            .catch(function(err){
            	console.error(err);
                alert("인증코드 확인 실패");
            });
        });
        
     // send 버튼
        document.querySelector("form").addEventListener("submit", function (e) {
            e.preventDefault(); // 폼 전송 막기

            const newPassword = document.querySelector("input[name='new_password']").value;
            const confirmPassword = document.querySelector("input[name='confirm_password']").value;

            if (newPassword !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다.");
                return;
            }

            fetch("/auth/resetPassword", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Accept': 'application/json'
                },
                body: JSON.stringify({
                    newPassword: newPassword
                })
            })
            .then(function(res){return res.json();})
            .then(function(data){alert(data.result.message);
                if (data.result.status === "SUCCESS") {
                    // 비밀번호 변경 성공 시 로그인 페이지로 이동
                    window.location.href = "/member/login";
                }
            })
            .catch(function(err){
            	console.error(err);
                alert("비밀번호 변경 실패");
            });
        });
        
        
    });
    </script>
</body>
</html>
