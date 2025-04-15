<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 관리</h2>

    <%-- <div class="admin-actions">
        <a href="<c:url value='/admin/notice/write' />" class="btn btn-primary">
            <i class="fas fa-plus"></i> 공지사항 작성
        </a>
    </div> --%>

    <div class="search-box">
        <form action="<c:url value='/admin/notice/manage' />" method="get">
            <div class="form-row">
                <div class="form-group">
                    <label for="startDate">시작일</label>
                    <input type="date" id="startDate" name="startDate" class="form-control">
                </div>
                <div class="form-group">
                    <label for="endDate">종료일</label>
                    <input type="date" id="endDate" name="endDate" class="form-control">
                </div>
                <div class="form-group">
                    <label for="searchType">검색조건</label>
                    <select id="searchType" name="searchType" class="form-control">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writer">작성자</option>
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
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일자</th>
                <th>조회수</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty noticeList}">
                <tr>
                    <td colspan="7" class="text-center">등록된 공지사항이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="notice" items="${noticeList}">
                <tr>
                    <td><input type="checkbox" class="notice-select" value="${notice.not_id}"></td>
                    <td>${notice.not_id}</td>
                    <td>
                        <a href="<c:url value='/admin/notice/detail?not_id=${notice.not_id}' />">
                            ${notice.not_ti}
                            <c:if test="${not empty notice.not_file}">
                                <i class="fas fa-paperclip"></i>
                            </c:if>
                        </a>
                    </td>
                    <td>${notice.not_register}</td>
                    <td><fmt:formatDate value="${notice.not_wd}" pattern="yyyy-MM-dd" /></td>
                    <td>${notice.view_count}</td>
                    <td>
                        
                        <button class="btn btn-sm btn-warning edit-btn" data-id="${notice.not_id}">수정</button>
                        <button class="btn btn-sm btn-danger delete-btn" data-id="${notice.not_id}">삭제</button>
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

<script>
    

    // 수정 버튼
    document.querySelectorAll(".edit-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const id = btn.dataset.id;
            location.href = `/admin/notice/edit?not_id=${id}`;
        });
    });

    // 삭제 버튼
     document.querySelectorAll(".delete-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const id = btn.dataset.id;

            if (!id) {
                alert("삭제할 ID가 존재하지 않습니다.");
                return;
            }

            if (confirm("정말 삭제하시겠습니까?")) {
                fetch("/admin/notice/delete", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: `not_id=${id}`
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
</script>

<!-- 공지 작성 플로팅 버튼 -->
<a href="<c:url value='/admin/notice/write' />" class="floating-btn" title="공지 작성">
    <i class="fas fa-plus"></i>공지 작성
</a>

<style>
/* 플로팅 버튼 스타일 */
.floating-btn {
    position: fixed;
    bottom: 30px;
    right: 30px;
    width: 60px;
    height: 60px;
    background-color: #006064;
    color: white;
    border-radius: 50%;
    text-align: center;
    line-height: 60px;
    font-size: 12px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.3);
    transition: background-color 0.3s ease;
    z-index: 1000;
    text-decoration: none;
}

.floating-btn:hover {
    background-color: #00838f;
}
</style>

<jsp:include page="../../common/footer.jsp" />
