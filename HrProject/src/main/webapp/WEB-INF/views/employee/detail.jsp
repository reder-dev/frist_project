<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>인사 관리</h2>
    
    <div class="profile-card">
        <img src="<c:url value='/resources/images/profile.png' />" alt="프로필 이미지" class="profile-image">
        <div class="profile-info">
            <table class="info-table">
                <tr>
                    <td>이름</td>
                    <td>홍길동</td>
                    <td>사원번호</td>
                    <td>2510035</td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td>남성</td>
                    <td>연락처</td>
                    <td>010-1111-1111</td>
                </tr>
                <tr>
                    <td>생년월일</td>
                    <td>1988-08-08</td>
                    <td>이메일</td>
                    <td>hrgenie@naver.com</td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>부산시 진구 부전동</td>
                    <td>부서명/부서번호</td>
                    <td>인사팀 / 35</td>
                </tr>
                <tr>
                    <td>직급 및 직책</td>
                    <td>사원 / 개발자</td>
                    <td>입사일자</td>
                    <td>2025-3-27</td>
                </tr>
            </table>
        </div>
    </div>
    
    <div class="button-group" style="margin-top: 20px; text-align: right;">
        <button class="btn btn-primary">인사 정보 수정</button>
        <a href="<c:url value='/personnel/resignation' />" class="btn btn-danger">퇴사 신청서 작성</a>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />