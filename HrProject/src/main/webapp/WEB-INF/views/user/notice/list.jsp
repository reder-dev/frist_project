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
                <tr class="${notice.isFixed ? 'notice-fixed' : ''}">
                    <td>${notice.not_id}</td>
                    <td>
                        <a href="<c:url value='/user/notice/detail?not_id=${notice.not_id}' />">
                            ${notice.not_ti}
                            <c:if test="${not empty notice.not_file}">
                                <i class="fas fa-paperclip"></i>
                            </c:if>
                        </a>
                    </td>
                    <td>${notice.not_register}</td>
                    <td>
                        <fmt:formatDate value="${notice.not_wd}" pattern="yyyy-MM-dd" />
                    </td>
                    <td>${notice.viewCount}</td>
                </tr>
            </c:forEach>
            
            <!-- 데이터가 없을 경우 샘플 데이터 표시 -->
            <c:if test="${empty noticeList}">
                <tr class="notice-fixed">
                    <td>1</td>
                    <td>
                        <a href="<c:url value='/user/notice/detail?not_id=1' />">
                            2025년 상반기 인사발령 안내
                            <i class="fas fa-paperclip"></i>
                        </a>
                    </td>
                    <td>관리자</td>
                    <td>2025-03-27</td>
                    <td>45</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>
                        <a href="<c:url value='/user/notice/detail?not_id=2' />">
                            사내 시스템 업데이트 안내
                        </a>
                    </td>
                    <td>관리자</td>
                    <td>2025-03-25</td>
                    <td>38</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>
                        <a href="<c:url value='/user/notice/detail?not_id=3' />">
                            4월 회식 일정 안내
                        </a>
                    </td>
                    <td>관리자</td>
                    <td>2025-03-23</td>
                    <td>42</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>
                        <a href="<c:url value='/user/notice/detail?not_id=4' />">
                            신입사원 교육 일정 안내
                            <i class="fas fa-paperclip"></i>
                        </a>
                    </td>
                    <td>관리자</td>
                    <td>2025-03-20</td>
                    <td>35</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>
                        <a href="<c:url value='/user/notice/detail?not_id=5' />">
                            연차 사용 안내
                        </a>
                    </td>
                    <td>관리자</td>
                    <td>2025-03-18</td>
                    <td>40</td>
                </tr>
            </c:if>
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