<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 관리</h2>

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
                    <td><input type="checkbox" class="notice-select" value="${notice.notId}"></td>
                    <td>${notice.notId}</td>
                    <td>
                        <a href="<c:url value='/admin/notice/detail/${notice.notId}' />">
                            ${notice.notTi}
                            <c:if test="${not empty notice.notFile}">
                                <i class="fas fa-paperclip"></i>
                            </c:if>
                        </a>
                    </td>
                    <td>${notice.notRegister}</td>
                    <td><fmt:formatDate value="${notice.notWd}" pattern="yyyy-MM-dd" /></td>
                    <td>${notice.viewCount}</td>
                    <td>
					    <a class="btn btn-sm btn-warning edit-btn" 
							   href="/admin/notice/edit?notId=${notice.notId}" 
							   style="display: inline-block; padding: 5px 10px; color: white; background-color: #f0ad4e; border: 1px solid #eea236; border-radius: 4px; text-decoration: none;">
							   수정
							</a>
					    <button type="button" class="btn btn-sm btn-danger delete-btn" data-id="${notice.notId}">
						    삭제
						</button>
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

<!-- 공지 작성 플로팅 버튼 -->
<a href="<c:url value='/admin/notice/write' />" class="floating-btn" title="공지 작성">
    <i class="fas fa-plus"></i>공지 작성
</a>
<script>


document.querySelectorAll(".edit-btn").forEach(btn => {
    btn.addEventListener("click", () => {
        const id = btn.dataset.id;
        location.href = `/admin/notice/edit?notId=${id}`;
    });
});


document.querySelectorAll(".delete-btn").forEach(btn => {
    btn.addEventListener("click", () => {
        const id = btn.getAttribute("data-id");

        if (!id) {
            alert("삭제할 ID가 없습니다.");
            return;
        }

        if (confirm("정말 삭제하시겠습니까?")) {
            fetch("/admin/notice/delete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "notId=" + encodeURIComponent(id)  // 정확히 notId로!
            })
            .then(res => {
                if (res.redirected) {
                    alert("삭제되었습니다.");
                    location.href = res.url;
                } else {
                    alert("삭제에 실패했습니다.");
                }
            })
            .catch(err => {
                console.error(err);
                alert("오류가 발생했습니다.");
            });
        }
    });
});



</script>
<style>
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

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html> 