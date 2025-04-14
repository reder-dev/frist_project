<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 관리</h2>
    
    <div class="admin-actions">
        <button class="btn btn-primary" id="addNoticeBtn">
            <i class="fas fa-plus"></i> 공지사항 작성
        </button>
        <button class="btn btn-secondary" id="exportExcelBtn">
            <i class="fas fa-file-excel"></i> Excel 내보내기
        </button>
    </div>
    
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
                <th>상단고정</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <tr class="notice-fixed">
                <td><input type="checkbox" class="notice-select"></td>
                <td>1</td>
                <td><a href="<c:url value='/admin/notice/detail?id=1' />">2025년 상반기 인사발령 안내</a></td>
                <td>관리자</td>
                <td>2025-03-27</td>
                <td>45</td>
                <td><span class="badge badge-primary">고정</span></td>
                <td>
                    <button class="btn btn-sm btn-info view-btn" data-id="1">조회</button>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="1">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="1">삭제</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="notice-select"></td>
                <td>2</td>
                <td><a href="<c:url value='/admin/notice/detail?id=2' />">사내 시스템 업데이트 안내</a></td>
                <td>관리자</td>
                <td>2025-03-25</td>
                <td>38</td>
                <td><button class="btn btn-sm btn-outline-primary fix-btn" data-id="2">고정</button></td>
                <td>
                    <button class="btn btn-sm btn-info view-btn" data-id="2">조회</button>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="2">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="2">삭제</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="notice-select"></td>
                <td>3</td>
                <td><a href="<c:url value='/admin/notice/detail?id=3' />">4월 회식 일정 안내</a></td>
                <td>관리자</td>
                <td>2025-03-23</td>
                <td>42</td>
                <td><button class="btn btn-sm btn-outline-primary fix-btn" data-id="3">고정</button></td>
                <td>
                    <button class="btn btn-sm btn-info view-btn" data-id="3">조회</button>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="3">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="3">삭제</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="notice-select"></td>
                <td>4</td>
                <td><a href="<c:url value='/admin/notice/detail?id=4' />">신입사원 교육 일정 안내</a></td>
                <td>관리자</td>
                <td>2025-03-20</td>
                <td>35</td>
                <td><button class="btn btn-sm btn-outline-primary fix-btn" data-id="4">고정</button></td>
                <td>
                    <button class="btn btn-sm btn-info view-btn" data-id="4">조회</button>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="4">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="4">삭제</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="notice-select"></td>
                <td>5</td>
                <td><a href="<c:url value='/admin/notice/detail?id=5' />">연차 사용 안내</a></td>
                <td>관리자</td>
                <td>2025-03-18</td>
                <td>40</td>
                <td><button class="btn btn-sm btn-outline-primary fix-btn" data-id="5">고정</button></td>
                <td>
                    <button class="btn btn-sm btn-info view-btn" data-id="5">조회</button>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="5">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="5">삭제</button>
                </td>
            </tr>
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