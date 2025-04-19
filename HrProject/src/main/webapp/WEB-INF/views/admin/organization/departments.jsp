<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="organization" />
</jsp:include>

<div class="content container">
    <h2 class="mb-4">부서 관리</h2>

    <div class="card shadow rounded-3 p-4 mb-4">
        <table class="table table-bordered align-middle">
            <thead class="table-light">
                <tr>
                    <th>부서 ID</th>
                    <th>부서명</th>
                    <th>등록자</th>
                    <th>등록일</th>
                    <th class="text-center">수정</th>
                    <th class="text-center">삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dept" items="${departments}">
                    <tr>
                        <td>${dept.depId}</td>
                        <td>
                            <input type="text" class="form-control dep-name" data-id="${dept.depId}" value="${dept.depName}" />
                        </td>
                        <td>${dept.depRegister}</td>
                        <td>${dept.depRegistdate}</td>
                        <td class="text-center">
                            <button class="btn btn-sm btn-outline-success update-btn" data-id="${dept.depId}">수정</button>
                        </td>
                        <td class="text-center">
                            <button class="btn btn-sm btn-outline-danger delete-btn" data-id="${dept.depId}">삭제</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="card shadow rounded-3 p-4">
        <h5 class="mb-3">새 부서 추가</h5>
        <div class="input-group">
            <input type="text" id="newDeptName" class="form-control" placeholder="부서명을 입력하세요" />
            <button id="addDeptBtn" class="btn btn-primary">추가</button>
        </div>
    </div>
</div>

<script>
document.getElementById('addDeptBtn').addEventListener('click', function () {
    const name = document.getElementById('newDeptName').value;
    if (!name) return alert('부서명을 입력하세요.');

    fetch('/admin/organization/department/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'depName=' + encodeURIComponent(name)
    }).then(res => res.json())
      .then(success => {
        if (success) location.reload();
        else alert('추가 실패');
    });
});

document.querySelectorAll('.update-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        const id = this.dataset.id;
        const name = document.querySelector(`.dep-name[data-id="${id}"]`).value;

        fetch('/admin/organization/department/update', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `depId=${id}&depName=${encodeURIComponent(name)}`
        }).then(res => res.json())
          .then(success => {
            if (success) alert('수정 완료');
            else alert('수정 실패');
        });
    });
});

document.querySelectorAll('.delete-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        const id = this.dataset.id;
        if (!confirm('정말 삭제하시겠습니까?')) return;

        fetch('/admin/organization/department/delete', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'deptId=' + encodeURIComponent(id)
        }).then(res => res.json())
          .then(success => {
            if (success) location.reload();
            else alert('삭제 실패');
        });
    });
});
</script>

<jsp:include page="../../common/footer.jsp" />


<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
