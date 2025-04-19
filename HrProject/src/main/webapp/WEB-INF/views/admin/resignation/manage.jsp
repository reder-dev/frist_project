<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="resignation" />
</jsp:include>

<div class="content">
    <h2>퇴사 신청 이력 조회</h2>

    <table class="data-table">
        <thead>
            <tr>
                <th>신청번호</th>
                <th>사원번호</th>
                <th>퇴사일</th>
                <th>유형</th>
                <th>신청일</th>
                <th>상태</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="resign" items="${resignList}">
                <tr>
                    <td>${resign.resignId}</td>
                    <td>${resign.empId}</td>
                    <td><fmt:formatDate value="${resign.resignationDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${resign.resignationType}</td>
                    <td><fmt:formatDate value="${resign.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${resign.status}</td>
                    <td>
                        <a href="<c:url value='/admin/resignation/detail/${resign.resignId}' />" class="view-link">조회</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">&gt;</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />

<!-- 스크립트 -->
<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>

<!-- 스타일 -->
<style>
.view-link {
    display: inline-block;
    padding: 4px 10px;
    text-decoration: none;
    border-radius: 4px;
    font-size: 12px;
    font-weight: bold;
    background-color: #17a2b8;
    color: white;
    margin-right: 5px;
}
.view-link:hover {
    opacity: 0.85;
}
</style>

</body>
</html>
