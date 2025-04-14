<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>인사관리 - 퇴사신청</h2>
    
    <form action="<c:url value='/personnel/resignation/submit' />" method="post">
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="form-control" value="홍길동" readonly>
        </div>
        
        <div class="form-group">
            <label for="department">사번</label>
            <input type="text" id="department" name="department" class="form-control" value="2510035" readonly>
        </div>
        
        <div class="form-group">
            <label for="position">직급</label>
            <input type="text" id="position" name="position" class="form-control" value="사원 / 개발자" readonly>
        </div>
        
        <div class="form-group">
            <label for="resignationDate">희망 퇴사일</label>
            <input type="date" id="resignationDate" name="resignationDate" class="form-control" required>
        </div>
        
        <div class="form-group">
            <label for="reason">퇴사사유</label>
            <textarea id="reason" name="reason" class="form-control" rows="5" required></textarea>
        </div>
        
        <div class="button-group" style="margin-top: 20px; text-align: right;">
            <button type="submit" class="btn btn-primary">퇴사신청</button>
            <a href="<c:url value='/personnel/detail' />" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />