<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항</h2>
    
    <div class="search-box">
        <form action="<c:url value='/user/notice/list' />" method="get">
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
                        <option value="not_ti">제목</option>
                        <option value="not_cn">내용</option>
                        <option value="not_register">작성자</option>
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
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일자</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty noticeList}">
                <tr>
                    <td colspan="5" class="text-center">등록된 공지사항이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="notice" items="${noticeList}" varStatus="status">
    <tr>
        <td>${notice.notId}</td>
        <td>
            <a href="<c:url value='/user/notice/detail?not_id=${notice.notId}' />">
                ${notice.notTi}
                <c:if test="${not empty notice.notFile}">
                    <i class="fas fa-paperclip"></i>
                </c:if>
            </a>
        </td>
        <td>${notice.notRegister}</td>
        <td><fmt:formatDate value="${notice.notWd}" pattern="yyyy-MM-dd" /></td>
        <td>${notice.viewCount}</td>
    </tr>
</c:forEach>
            
           
        </tbody>
    </table>
    
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

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
