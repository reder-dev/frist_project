<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 수정</h2>

    <form action="<c:url value='/admin/notice/update' />" method="post" enctype="multipart/form-data">
        <input type="hidden" name="not_id" value="${notice.not_id}" />

        <div class="form-group">
            <label for="not_ti">제목</label>
            <input type="text" id="not_ti" name="not_ti" class="form-control" value="${notice.not_ti}" required />
        </div>

        <div class="form-group">
            <label for="not_cn">내용</label>
            <textarea id="not_cn" name="not_cn" class="form-control" rows="10" required>${notice.not_cn}</textarea>
        </div>

        <div class="form-group">
            <label for="not_file">첨부파일</label>
            <input type="file" id="not_file" name="not_file" class="form-control" />
            <c:if test="${not empty notice.not_file}">
                <p>현재 파일: ${notice.not_file}</p>
            </c:if>
        </div>

        <div class="form-group">
            <label for="not_register">작성자</label>
            <input type="text" id="not_register" name="not_register" class="form-control" value="${notice.not_register}" readonly />
        </div>

        <div class="form-group text-center">
            <button type="submit" class="btn btn-primary">수정 완료</button>
            <a href="<c:url value='/admin/notice/manage' />" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>

<jsp:include page="../../common/footer.jsp" />
