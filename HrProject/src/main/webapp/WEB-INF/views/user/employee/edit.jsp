<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<fmt:formatDate value="${employee.emp_jd}" pattern="yyyy-MM-dd" var="empJdFormatted" />

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
        }
    </style>
</head>

<div class="content">
    <h2>내 정보 수정</h2>

    <form action="${pageContext.request.contextPath}/user/employee/update" method="post" class="employee-form">
        <input type="hidden" name="emp_id" value="${employee.emp_id}" />

        <div class="form-grid">
            <!-- ❌ 수정 불가 -->
            <label>사번:</label>
            <input type="text" value="${employee.emp_id}" readonly />

            <label>이름:</label>
            <input type="text" value="${employee.emp_name}" readonly />

            <label>부서:</label>
            <input type="text" value="${employee.dep_name}" readonly />

            <label>직급:</label>
            <input type="text" value="${employee.rank_id}" readonly />

            <label>입사일자:</label>
            <input type="text" value="${empJdFormatted}" readonly />

            <!-- ✅ 수정 가능 -->
            <label>전화번호:</label>
            <input type="text" name="emp_phone" value="${employee.emp_phone}" />

            <label>이메일:</label>
            <input type="text" name="emp_email" value="${employee.emp_email}" />

            <label>주소:</label>
            <input type="text" name="emp_address" value="${employee.emp_address}" />

            <label>사진 경로:</label>
            <input type="text" name="emp_pht" value="${employee.emp_pht}" />
        </div>

        <button type="submit" class="btn-primary">수정 완료</button>
    </form>
</div>

<jsp:include page="../../common/footer.jsp" />
