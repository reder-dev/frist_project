<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>연차 신청</title></head>
<body>
<h2>연차 신청</h2>
<form method="post" action="/leave/request">
    시작일: <input type="date" name="startDate" required><br/>
    종료일: <input type="date" name="endDate" required><br/>
    사유: <textarea name="reason" rows="4" cols="30"></textarea><br/>
    <button type="submit">신청</button>
</form>
</body>
</html>
