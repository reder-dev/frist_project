<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>지각 사유 제출</title></head>
<body>
<h2>지각 사유 제출</h2>
<form method="post" action="/lateness/request">
    예정 출근 시간: <input type="time" name="expectedTime" required><br/>
    실제 출근 시간: <input type="time" name="actualTime" required><br/>
    사유: <textarea name="reason" rows="4" cols="30" required></textarea><br/>
    <button type="submit">제출</button>
</form>
</body>
</html>

