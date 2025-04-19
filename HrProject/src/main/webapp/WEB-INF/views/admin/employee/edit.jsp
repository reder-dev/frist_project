<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<fmt:formatDate value="${employee.empJd}" pattern="yyyy-MM-dd" var="empJdFormatted" />
<c:if test="${not empty employee.empQd}">
    <fmt:formatDate value="${employee.empQd}" pattern="yyyy-MM-dd" var="empQdFormatted" />
</c:if>

<div class="content">
    <h2>인사 정보 수정</h2>

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
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[readonly] {
            background-color: #f2f2f2;
            color: #777;
            cursor: not-allowed;
        }
        .btn-primary {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>

    <form action="${pageContext.request.contextPath}/admin/employee/edit" method="post" class="employee-form">
        <input type="hidden" name="empId" value="${employee.empId}" />

        <div class="form-grid">
            <!-- 수정 불가 항목 -->
            <label>사번:</label>
            <input type="text" name="empId" value="${employee.empId}" readonly />

            <label>이름:</label>
            <input type="text" name="empName" value="${employee.empName}" readonly />

            <label>성별:</label>
            <input type="text" name="empGender" value="${employee.empGender}" readonly />

            <label>주민번호:</label>
            <input type="text" name="empJm" value="${employee.empJm}" readonly />

            <label>전화번호:</label>
            <input type="text" name="empPhone" value="${employee.empPhone}" readonly />

            <label>이메일:</label>
            <input type="text" name="empEmail" value="${employee.empEmail}" readonly />

            <label>주소:</label>
            <input type="text" name="empAddress" value="${employee.empAddress}" readonly />

            <input type="hidden" name="empRegistdate" value="${employee.empRegistdate}" />

            <label>등록자:</label>
            <input type="text" name="empRegister" value="${employee.empRegister}" readonly />

            <label>사진:</label>
            <input type="text" name="empPht" value="${employee.empPht}" readonly />

            <!-- 수정 가능 항목 -->
            <label>부서번호:</label>
            <input type="text" name="depId" value="${employee.depId}" />

            <label>부서명:</label>
            <input type="text" name="depName" value="${employee.depName}" />

            <label>직책:</label>
            <input type="text" name="posId" value="${employee.posId}" />

            <label>직급:</label>
            <input type="text" name="rankId" value="${employee.rankId}" />

            <label>회사명:</label>
            <input type="text" name="empCn" value="${employee.empCn}" />

            <label>입사일자:</label>
            <input type="date" name="empJd" value="${empJdFormatted}" />

            <label>퇴사일자:</label>
            <input type="date" name="empQd" value="${empQdFormatted != null ? empQdFormatted : ''}" />
        </div>

        <button type="submit" class="btn-primary">수정 완료</button>
    </form>
</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
