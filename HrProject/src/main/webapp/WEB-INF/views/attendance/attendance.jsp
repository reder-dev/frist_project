<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>출근/퇴근 기록</title>
    <script>
        function checkIn() {
            fetch("/attendance/check-in", { method: "POST" })
                .then(response = response.json())
                .then(data = alert("출근 처리 완료: " + data.checkIn));
        }

        function checkOut() {
            fetch("/attendance/check-out", { method: "POST" })
                .then(response = response.json())
                .then(data = alert("퇴근 처리 완료: " + data.checkOut));
        }
    </script>
</head>
<body>
    <h2>출퇴근 기록</h2>
    <button onclick="checkIn()">출근</button>
    <button onclick="checkOut()">퇴근</button>
</body>
</html>
