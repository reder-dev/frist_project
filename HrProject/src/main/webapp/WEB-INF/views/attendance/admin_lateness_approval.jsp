<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>지각 승인 요청 목록</title>
    <script>
        function approve(id) {
            fetch("/lateness/approve/" + id, { method: "POST" })
                .then(() => location.reload());
        }

        function reject(id) {
            fetch("/lateness/reject/" + id, { method: "POST" })
                .then(() => location.reload());
        }
    </script>
</head>
<body>
    <h2>지각 사유 승인</h2>

    <table border="1">
        <tr>
            <th>사원 ID</th>
            <th>날짜</th>
            <th>출근 시간</th>
            <th>지각 시간</th>
            <th>사유</th>
            <th>상태</th>
            <th>처리</th>
        </tr>
        <c:forEach var="l" items="${latenessList}">
            <tr>
                <td>${l.employeeId}</td>
                <td>${l.date}</td>
                <td>${l.checkInTime}</td>
                <td>${l.latenessMinutes}분</td>
                <td>${l.reason}</td>
                <td>${l.status}</td>
                <td>
                    <c:if test="${l.status == '대기'}">
                        <button onclick="approve(${l.id})">승인</button>
                        <button onclick="reject(${l.id})">반려</button>
                    </c:if>
                    <c:if test="${l.status != '대기'}">처리됨</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
