<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>인사조회 관리</h2>
    
    <div class="admin-actions">
        <button class="btn btn-primary" id="addEmployeeBtn">
            <i class="fas fa-plus"></i> 직원 추가
        </button>
        <button class="btn btn-secondary" id="exportExcelBtn">
            <i class="fas fa-file-excel"></i> Excel 내보내기
        </button>
    </div>
    
    <div class="search-box">
        <form action="<c:url value='/admin/personnel/info' />" method="get">
            <div class="form-row">
                <div class="form-group">
                    <label for="department">부서</label>
                    <select id="department" name="department" class="form-control">
                        <option value="">전체</option>
                        <option value="인사팀">인사팀</option>
                        <option value="개발팀">개발팀</option>
                        <option value="영업팀">영업팀</option>
                        <option value="마케팅팀">마케팅팀</option>
                        <option value="경영지원팀">경영지원팀</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="position">직급</label>
                    <select id="position" name="position" class="form-control">
                        <option value="">전체</option>
                        <option value="사원">사원</option>
                        <option value="대리">대리</option>
                        <option value="과장">과장</option>
                        <option value="차장">차장</option>
                        <option value="부장">부장</option>
                        <option value="이사">이사</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="searchType">검색조건</label>
                    <select id="searchType" name="searchType" class="form-control">
                        <option value="name">이름</option>
                        <option value="empId">사원번호</option>
                        <option value="email">이메일</option>
                        <option value="phone">연락처</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="keyword">검색어</label>
                    <input type="text" id="keyword" name="keyword" class="form-control" placeholder="검색어를 입력하세요">
                </div>
                <div class="form-group">
                    <label>&nbsp;</label>
                    <button type="submit" class="btn btn-primary form-control">검색</button>
                </div>
            </div>
        </form>
    </div>
    
    <table class="data-table">
        <thead>
            <tr>
                <th><input type="checkbox" id="selectAll"></th>
                <th>사원번호</th>
                <th>이름</th>
                <th>부서</th>
                <th>직급</th>
                <th>이메일</th>
                <th>연락처</th>
                <th>입사일</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td><input type="checkbox" class="employee-select"></td>
                    <td>${employee.empId}</td>
                    <td>${employee.empName}</td>
                    <td>${employee.depName}</td>
                    <td>${employee.rankId}</td>
                    <td>${employee.empEmail}</td>
                    <td>${employee.empPhone}</td>
                    <td><fmt:formatDate value="${employee.empJd}" pattern="yyyy-MM-dd" /></td>
                    <td>
                        <a href="/admin/employee/detail/${employee.empId}" class="view-link">조회</a>
						<a href="/admin/employee/edit/${employee.empId}" class="edit-link">수정</a>
						<button type="button" class="delete-btn" data-id="${employee.empId}">삭제</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div class="table-actions">
        <button class="btn btn-danger" id="deleteSelectedBtn">선택 삭제</button>
    </div>
    
    <div class="pagination">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">&gt;</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />

<!-- JS 파일 + 기능 스크립트 -->
<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>

<!-- 버튼 동작 스크립트 추가 -->
<script>
document.addEventListener("DOMContentLoaded", () => {
    // 조회 버튼
    document.querySelectorAll(".view-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const empId = btn.dataset.id;
            location.href = `/admin/employee/detail/${empId}`;
        });
    });

    // 수정 버튼
    document.querySelectorAll(".edit-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const empId = btn.dataset.id;
            location.href = `/admin/employee/edit/${empId}`;
        });
    });

    // 삭제 버튼
    document.querySelectorAll(".delete-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const empId = btn.dataset.id;

            if (confirm("정말 삭제하시겠습니까?")) {
                fetch(`/admin/employee/delete`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: `empId=${empId}`
                })
                .then(res => {
                    if (res.ok) {
                        alert("삭제되었습니다.");
                        location.reload();
                    } else {
                        alert("삭제에 실패했습니다.");
                    }
                })
                .catch(() => {
                    alert("요청 중 오류가 발생했습니다.");
                });
            }
        });
    });
});
</script>

<style>
.view-link, .edit-link {
    display: inline-block;
    padding: 4px 10px;
    text-decoration: none;
    border-radius: 4px;
    font-size: 12px;
    font-weight: bold;
    color: white;
    margin-right: 5px;
}

.view-link {
    background-color: #17a2b8;
}

.edit-link {
    background-color: #ffc107;
    color: black;
}

.view-link:hover, .edit-link:hover {
    opacity: 0.85;
}
</style>



</body>
</html>
