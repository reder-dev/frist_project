<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>인사조회</h2>
    
    <div class="profile-card">
        <img src="<c:url value='/resources/img/profile.jpeg' />" alt="프로필 이미지" class="profile-image">
        <div class="profile-info">
            <table class="info-table">
                <tr>
                    <td>이름</td>
                    <td>${employee.empName}</td>
                    <td>사원번호</td>
                    <td>${employee.empId}</td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td>${employee.empGender}</td>
                    <td>연락처</td>
                    <td>${employee.empPhone}</td>
                </tr>
                <tr>
                    <td>생년월일</td>
                    <td>${employee.empJm}</td>
                    <td>이메일</td>
                    <td>${employee.empEmail}</td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>${employee.empAddress}</td>
                    <td>부서명/부서번호</td>
                    <td>${employee.depName} / ${employee.depId}</td>
                </tr>
                <tr>
                    <td>직급 및 직책</td>
                    <td>${employee.rankId} / ${employee.empCn}</td>
                    <td>입사일자</td>
                    <td><fmt:formatDate value="${employee.empJd}" pattern="yyyy-MM-dd" /></td>
                </tr>
            </table>
        </div>
    </div>

    <div class="employee-action-buttons">
        <a href="<c:url value='/user/employee/edit' />" class="btn btn-primary">
            <i class="fas fa-edit"></i> 정보 수정
        </a>
        <a href="<c:url value='/user/employee/resignation' />" class="btn btn-danger">
            <i class="fas fa-user-times"></i> 퇴사 신청
        </a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
