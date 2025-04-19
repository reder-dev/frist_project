<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="resignation" />
</jsp:include>

<div class="content container mt-4">
    <h2>퇴사 신청 상세</h2>

    <div class="card p-4 shadow-sm mt-3">
        <h5 class="mb-3">신청자 정보</h5>
        <table class="table table-bordered">
            <tr>
                <th>사원명</th>
                <td>${employee.empName}</td>
                <th>사원번호</th>
                <td>${employee.empId}</td>
            </tr>
            <tr>
                <th>부서</th>
                <td>${employee.depName}</td>
                <th>직급</th>
                <td>${employee.rankId}</td>
            </tr>
            <tr>
                <th>입사일</th>
                <td><fmt:formatDate value="${employee.empJd}" pattern="yyyy-MM-dd" /></td>
                <th>연락처</th>
                <td>${resign.contactAfter}</td>
            </tr>
        </table>

        <h5 class="mt-4">퇴사 정보</h5>
        <table class="table table-bordered">
            <tr>
                <th>희망 퇴사일</th>
                <td><fmt:formatDate value="${resign.resignationDate}" pattern="yyyy-MM-dd" /></td>
                <th>유형</th>
                <td>${resign.resignationType}</td>
            </tr>
            <tr>
                <th>신청일</th>
                <td colspan="3"><fmt:formatDate value="${resign.requestDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <tr>
                <th>상태</th>
                <td>${resign.status}</td>
                <th>승인자</th>
                <td>${resign.approver}</td>
            </tr>
            <tr>
                <th>승인일</th>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${not empty resign.approveDate}">
                            <fmt:formatDate value="${resign.approveDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>

        <div class="mt-4">
            <h6>퇴사 사유</h6>
            <p>${resign.resignationReason}</p>

            <h6 class="mt-3">업무 인수인계 계획</h6>
            <p>${resign.handoverPlan}</p>
        </div>

        <div class="actions mt-4">
            <button class="btn btn-success" onclick="handleDecision('승인')">승인</button>
            <button class="btn btn-danger" onclick="handleDecision('반려')">반려</button>
            <a href="<c:url value='/admin/resignation/manage' />" class="btn btn-secondary">목록</a>
        </div>
    </div>
</div>

<script>
function handleDecision(status) {
    const resignId = '${resign.resignId}';

    if (confirm(status + " 처리하시겠습니까?")) {
        fetch('/admin/resignation/update', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'resignId=' + encodeURIComponent(resignId) + '&status=' + encodeURIComponent(status)
        })
        .then(response => response.text())
        .then(result => {
            if (result === 'OK') {
                alert("퇴사 신청이 '" + status + "' 처리되었습니다.");
                // ✅ manage 페이지로 이동
                window.location.href = '/admin/resignation/manage';
            } else {
                alert('처리에 실패했습니다. 다시 시도해주세요.');
            }
        })
        .catch(() => {
            alert('서버 오류가 발생했습니다.');
        });
    }
}
</script>


<style>
.card {
    background: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}

.card h5 {
    font-weight: bold;
    color: #333;
}

.table th {
    background-color: #f8f9fa;
    color: #333;
    text-align: center;
    vertical-align: middle;
}

.table td {
    vertical-align: middle;
}

.actions {
    display: flex;
    gap: 10px;
}

.actions .btn {
    min-width: 100px;
}
</style>

<jsp:include page="../../common/footer.jsp" />
<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
