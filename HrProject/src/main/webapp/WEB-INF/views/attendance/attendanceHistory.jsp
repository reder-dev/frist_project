<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>출결 이력</title></head>
<body>
<h2>내 출결 이력</h2>

<table border="1">
    <tr>
        <th>날짜</th>
        <th>출근</th>
        <th>퇴근</th>
        <th>근무 형태</th>
        <th>지각 사유</th>
        <th>승인 상태</th>
    </tr>
    <c:forEach var="a" items="${attendanceList}">
        <tr>
            <td>${a.date}</td>
            <td>${a.checkIn}</td>
            <td>${a.checkOut}</td>
            <td>${a.workType}</td>
            <td>${a.reason}</td>
            <td>${a.approvalStatus}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
