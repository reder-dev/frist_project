<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="appointment" />
</jsp:include>

<div class="content">
    <h2>발령 이력 조회</h2>

    <div class="admin-actions">
        <a href="<c:url value='/admin/appointment/register' />" class="btn btn-primary">
            <i class="fas fa-plus"></i> 발령 등록
        </a>
    </div>

    <table class="data-table">
        <thead>
            <tr>
                <th>발령번호</th>
                <th>사원번호</th>
                <th>발령종류</th>
                <th>발령내용</th>
                <th>발령일자</th>
                <th>등록자</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="app" items="${appointments}">
                <tr>
                    <td>${app.appId}</td>
                    <td>${app.empId}</td>
                    <td>${app.appCa}</td>
                    <td>${app.appDt}</td>
                    <td><fmt:formatDate value="${app.appRegistdate}" pattern="yyyy-MM-dd" /></td>
                    <td>${app.appRegister}</td>
                    <td>
                        <a href="/admin/appointment/detail/${app.appId}" class="view-link">조회</a>
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
