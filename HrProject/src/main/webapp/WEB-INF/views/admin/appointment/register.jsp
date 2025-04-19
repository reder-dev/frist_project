<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="appointment" />
</jsp:include>

<div class="content container">
    <h2 class="mb-4">발령 등록</h2>

    <form action="<c:url value='/admin/appointment/register' />" method="post" class="card p-4 shadow rounded-3">
        <div class="mb-3">
            <label for="empId" class="form-label">사원 선택</label>
            <select name="empId" id="empId" class="form-control" required>
                <option value="">사원 선택</option>
                <c:forEach var="emp" items="${employees}">
                    <option value="${emp.empId}">${emp.empId} - ${emp.empName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="appCa" class="form-label">발령 종류</label>
            <input type="text" name="appCa" id="appCa" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="appDt" class="form-label">발령 내용</label>
            <textarea name="appDt" id="appDt" rows="4" class="form-control" required></textarea>
        </div>

        <div class="text-end">
            <button type="submit" class="btn btn-primary">등록</button>
            <a href="<c:url value='/admin/appointment/list' />" class="btn btn-secondary">목록으로</a>
        </div>
    </form>
</div>

<jsp:include page="../../common/footer.jsp" />


<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
