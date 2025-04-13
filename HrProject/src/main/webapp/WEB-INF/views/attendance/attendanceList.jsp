<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>근태 목록</title>
</head>
<body>
    <h2>근태 목록</h2>
    <table border="1">
        <tr>
            <th>날짜</th>
            <th>출근 시간</th>
            <th>퇴근 시간</th>
            <th>상태</th>
        </tr>
        <c:forEach var="att" items="${attendanceList}">
            <tr>
                <td>${att.date}</td>
                <td>${att.startTime}</td>
                <td>${att.endTime}</td>
                <td>${att.status}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
