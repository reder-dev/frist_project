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
                    <select name="searchType" class="form-control">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writer">작성자</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" name="keyword" class="form-control" placeholder="검색어를 입력하세요">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">검색</button>
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
            <tr>
                <td>1</td>
                <td><a href="<c:url value='/user/notice/detail?id=1' />">공지사항1</a></td>
                <td>홍길동</td>
                <td>2025-03-27</td>
                <td>45</td>
            </tr>
            <tr>
                <td>2</td>
                <td><a href="<c:url value='/user/notice/detail?id=2' />">공지사항2</a></td>
                <td>홍길동</td>
                <td>2025-03-25</td>
                <td>32</td>
            </tr>
            <tr>
                <td>3</td>
                <td><a href="<c:url value='/user/notice/detail?id=3' />">공지사항3</a></td>
                <td>홍길동</td>
                <td>2025-03-21</td>
                <td>28</td>
            </tr>
            <tr>
                <td>4</td>
                <td><a href="<c:url value='/user/notice/detail?id=4' />">공지사항4</a></td>
                <td>홍길동</td>
                <td>2025-03-19</td>
                <td>19</td>
            </tr>
            <tr>
                <td>5</td>
                <td><a href="<c:url value='/user/notice/detail?id=5' />">공지사항5</a></td>
                <td>홍길동</td>
                <td>2025-03-14</td>
                <td>24</td>
            </tr>
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