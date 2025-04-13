<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>근태 관리</title>
    <script>
        function punch(action) {
            fetch("/attendance/" + action, {
                method: "POST"
            })
            .then(response => {
                if(response.ok) {
                    alert(action + " 기록 완료");
                    location.reload();
                } else {
                    alert("기록 실패");
                }
            });
        }
    </script>
</head>
<body>
    <h2>근태 관리</h2>
    <button onclick="punch('start')">출근</button>
    <button onclick="punch('end')">퇴근</button>
</body>
</html>
