<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 작성</h2>

    <form action="<c:url value='/admin/notice/write' />" method="post" class="notice-form">
        <div class="form-group">
            <label for="not_ti">제목</label>
            <input type="text" id="not_ti" name="not_ti" required class="form-control" />
        </div>

        <div class="form-group">
            <label for="not_cn">내용</label>
            <textarea id="not_cn" name="not_cn" rows="8" class="form-control" required></textarea>
        </div>

        <div class="form-group">
            <label for="not_file">첨부파일명</label>
            <input type="text" id="not_file" name="not_file" class="form-control" placeholder="예: 1234abcd-uuid.pdf" />
        </div>

        <div class="form-group">
            <label for="emp_id">사원번호</label>
            <input type="number" id="emp_id" name="emp_id" required class="form-control" />
        </div>

        <div class="form-actions">
            <input type="submit" value="등록" class="btn btn-primary" />
            <a href="<c:url value='/admin/notice/manage' />" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>

<jsp:include page="../../common/footer.jsp" />
