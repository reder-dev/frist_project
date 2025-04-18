<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<fmt:formatDate value="${employee.empJd}" pattern="yyyy-MM-dd" var="empJdFormatted" />

<head>
    <meta charset="UTF-8">
    <title>내 정보 수정</title>
    <style>
        .employee-form {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .form-grid {
            display: grid;
            grid-template-columns: 150px 1fr;
            gap: 10px 20px;
        }
        label {
            font-weight: bold;
        }
        input {
            padding: 6px;
            width: 100%;
        }
        .btn-primary {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>

<div class="content">
    <h2>내 정보 수정</h2>

    <form action="${pageContext.request.contextPath}/user/employee/update" method="post" class="employee-form">
        <input type="hidden" name="empId" value="${employee.empId}" />

        <div class="form-grid">
            <!-- ❌ 수정 불가 -->
            <label>사번:</label>
            <input type="text" value="${employee.empId}" readonly />

            <label>이름:</label>
            <input type="text" value="${employee.empName}" readonly />

            <label>부서:</label>
            <input type="text" value="${employee.depName}" readonly />

            <label>직급:</label>
            <input type="text" value="${employee.rankId}" readonly />

            <label>입사일자:</label>
            <input type="text" value="${empJdFormatted}" readonly />

            <!-- ✅ 수정 가능 -->
            <label>전화번호:</label>
            <input type="text" name="empPhone" value="${employee.empPhone}" />

            <label>이메일:</label>
            <input type="text" name="empEmail" value="${employee.empEmail}" />

            <label>주소:</label>
            <input type="text" name="empAddress" value="${employee.empAddress}" />

            <label>사진 경로:</label>
            <input type="text" name="empPht" value="${employee.empPht}" />
        </div>

        <button type="submit" class="btn-primary">수정 완료</button>
    </form>
</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
