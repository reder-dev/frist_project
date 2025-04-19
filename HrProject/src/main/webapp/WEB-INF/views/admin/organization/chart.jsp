<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="organization" />
</jsp:include>

<div class="content container">
    <h2 class="mb-4">조직도</h2>

    <div class="card p-4 shadow rounded-3">
        <div class="mb-3">
            <label for="deptSelect" class="form-label">부서 선택</label>
            <select id="deptSelect" class="form-select">
                <option value="">부서를 선택하세요</option>
                <c:forEach var="dept" items="${departments}">
                    <option value="${dept.depId}">${dept.depName}</option>
                </c:forEach>
            </select>
        </div>

        <div id="employeeList" class="mt-4">
            <!-- 부서별 사원 정보가 여기에 AJAX로 로딩됨 -->
        </div>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('deptSelect').addEventListener('change', function () {
        const deptId = this.value;
        console.log("선택된 부서 ID:", deptId);  // 확인 로그
        const target = document.getElementById('employeeList');

        if (deptId) {
            fetch(`/admin/organization/department-members?deptId=${deptId}`)
                .then(response => response.text())
                .then(html => {
                    target.innerHTML = html;
                })
                .catch(err => {
                    target.innerHTML = '<p class="text-danger">사원 정보를 불러오는 데 실패했습니다.</p>';
                });
        } else {
            target.innerHTML = '';
        }
    });
});
</script>


<jsp:include page="../../common/footer.jsp" />


<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>