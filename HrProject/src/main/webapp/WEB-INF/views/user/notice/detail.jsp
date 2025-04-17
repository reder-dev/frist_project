<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<style>
.notice-container {
    background-color: #fff;
    padding: 30px;
    border-radius: 16px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.05);
    margin-top: 20px;
}
.notice-title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 15px;
    border-bottom: 1px solid #ccc;
    padding-bottom: 10px;
}
.notice-meta {
    font-size: 14px;
    color: #666;
    margin-bottom: 20px;
}
.notice-meta span {
    margin-right: 20px;
}
.notice-content {
    font-size: 16px;
    line-height: 1.8;
    color: #333;
    margin-bottom: 30px;
}
.notice-attachments {
    background-color: #f9f9f9;
    border: 1px solid #ddd;
    padding: 15px;
    border-radius: 10px;
    margin-bottom: 20px;
}
.notice-actions {
    text-align: right;
}
</style>

<div class="content">
    <h2>공지사항 상세</h2>

    <div class="notice-container">
        <div class="notice-title">${notice.not_ti}</div>

        <div class="notice-meta">
            <span><i class="fas fa-user"></i> 작성자: ${notice.not_register}</span>
            <span><i class="fas fa-calendar-alt"></i> 작성일: 
                <fmt:formatDate value="${notice.not_wd}" pattern="yyyy-MM-dd HH:mm" />
            </span>
            <span><i class="fas fa-eye"></i> 조회수: ${notice.view_count}</span>
        </div>

        <div class="notice-content">
            ${notice.not_cn}
        </div>

        <c:if test="${not empty notice.not_file}">
            <div class="notice-attachments">
                <strong>첨부파일:</strong>
                <a href="<c:url value='/user/notice/download?fileId=${notice.not_file}' />">
                    ${notice.not_file}
                </a>
            </div>
        </c:if>

        <div class="notice-actions">
            <a href="<c:url value='/user/notice/list' />" class="btn btn-secondary">목록으로</a>
        </div>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />
