<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 상세</h2>
    
    <div class="notice-detail">
        <div class="notice-header">
            <h3>공지사항1</h3>
            <div class="notice-info">
                <span>작성자: 홍길동</span>
                <span>작성일: 2025-03-27</span>
                <span>조회수: 45</span>
            </div>
        </div>
        
        <div class="notice-content">
            <p>안녕하세요, HR Genie 시스템을 사용해 주셔서 감사합니다.</p>
            <p>이번 공지사항에서는 다음과 같은 내용을 안내해 드립니다.</p>
            <br>
            <p>1. 시스템 업데이트 안내</p>
            <p>2. 새로운 기능 소개</p>
            <p>3. 사용자 매뉴얼 업데이트</p>
            <br>
            <p>자세한 내용은 첨부파일을 참고해 주시기 바랍니다.</p>
            <p>감사합니다.</p>
        </div>
        
        <div class="notice-attachments">
            <h4>첨부파일</h4>
            <ul>
                <li><a href="#">시스템_업데이트_안내.pdf</a></li>
                <li><a href="#">사용자_매뉴얼_v2.0.pdf</a></li>
            </ul>
        </div>
    </div>
    
    <div class="button-group" style="margin-top: 20px; text-align: right;">
        <a href="<c:url value='/user/notice/list' />" class="btn btn-secondary">목록으로</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />