<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>관리자 출결 현황</title></head>
<body>
<h2>전체 사원 출결 현황</h2>

<form method="get" action="/admin/attendance">
    날짜 선택: <input type="date" name="date" required>
    <button type="submit">조회</button>
</form>

<table border="1">
    <tr>
        <th>사원 ID</th>
        <th>날짜</th>
        <th>출근</th>
        <th>퇴근</th>
        <th>근무 형태</th>
    </tr>
    <c:forEach var="a" items="${attendanceList}">
        <tr>
            <td>${a.employeeId}</td>
            <td>${a.date}</td>
            <td>${a.checkIn}</td>
            <td>${a.checkOut}</td>
            <td>${a.workType}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
