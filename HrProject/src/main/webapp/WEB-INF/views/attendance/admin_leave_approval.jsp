<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>연차 신청 승인</title>
    <script>
        function updateStatus(id, status) {
            fetch("/admin/leave/status/" + id + "?status=" + status, { method: "POST" })
                .then(() => location.reload());
        }
    </script>
</head>
<body>
    <h2>연차 신청 승인</h2>

    <table border="1">
        <tr>
            <th>사원 ID</th>
            <th>시작일</th>
            <th>종료일</th>
            <th>사유</th>
            <th>상태</th>
            <th>처리</th>
        </tr>
        <c:forEach var="leave" items="${leaveList}">
            <tr>
                <td>${leave.employeeId}</td>
                <td>${leave.startDate}</td>
                <td>${leave.endDate}</td>
                <td>${leave.reason}</td>
                <td>${leave.status}</td>
                <td>
                    <c:if test="${leave.status == '신청됨'}">
                        <button onclick="updateStatus(${leave.id}, '승인')">승인</button>
                        <button onclick="updateStatus(${leave.id}, '반려')">반려</button>
                    </c:if>
                    <c:if test="${leave.status != '신청됨'}">처리됨</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
