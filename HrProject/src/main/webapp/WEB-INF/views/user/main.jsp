<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/user-sidebar.jsp">
    <jsp:param name="menu" value="dashboard" />
</jsp:include>

<div class="content">
    <div class="main-column">
        <!-- 프로필 카드 -->
        <div class="profile-card">
            <div class="profile-header">
                <img src="<c:url value='/resources/images/profile.jpeg' />" alt="프로필 이미지" class="profile-image">
                <div class="profile-info">
                    <h3>${employee.emp_name}</h3>
                    <p>${employee.dep_name} / ${employee.rank_id}</p>
                    <p>사원번호: ${employee.emp_id}</p>
                </div>
            </div>
            <div class="profile-details">
                <div class="detail-item">
                    <span class="label">이메일:</span>
                    <span class="value">${employee.emp_email}</span>
                </div>
                <div class="detail-item">
                    <span class="label">연락처:</span>
                    <span class="value">${employee.emp_phone}</span>
                </div>
                <div class="detail-item">
                    <span class="label">입사일:</span>
                    <span class="value"><fmt:formatDate value="${employee.emp_jd}" pattern="yyyy-MM-dd" /></span>
                </div>
                <div class="detail-item">
                    <span class="label">근무일수:</span>
                    <span class="value">${workDays}일</span>
                </div>
            </div>
            <div class="profile-actions">
                <a href="<c:url value='/user/employee/info' />" class="btn btn-primary btn-sm">인사정보 상세보기</a>
            </div>
        </div>

        <!-- 캘린더 -->
        <div class="calendar-container">
            <div class="calendar-header">
                <h3>이번달 일정 캘린더</h3>
            </div>
            <table class="calendar-table">
                <thead>
                    <tr>
                        <th class="sun">일 SUN</th>
                        <th>월 MON</th>
                        <th>화 TUE</th>
                        <th>수 WED</th>
                        <th>목 THU</th>
                        <th>금 FRI</th>
                        <th class="sat">토 SAT</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 일정 하드코딩 유지 또는 동적 처리 -->
                    <c:forEach begin="1" end="31" var="day" step="7">
                        <tr>
                            <c:forEach begin="${day}" end="${day+6}" var="d">
                                <td><c:if test="${d <= 31}">${d}</c:if></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- TO DO & MEMO -->
        <div class="dashboard-row">
            <div class="todo-list-container">
                <h4>TO DO LIST</h4>
                <c:forEach var="todo" items="${todoList}">
                    <div class="todo-item">
                        <input type="checkbox" id="todo_${todo.id}">
                        <label for="todo_${todo.id}">${todo.task}</label>
                    </div>
                </c:forEach>
            </div>

            <div class="memo-container">
                <h4>MEMO</h4>
                <textarea placeholder="메모를 입력하세요...">${memoContent}</textarea>
            </div>
        </div>
    </div>

    <!-- 우측 사이드바 -->
    <div class="side-column">
        <div class="company-info">
            <h3>회사 정보</h3>
            <p>회사 소개 및 주요 안내사항을 여기에 표시합니다.</p>
        </div>

        <div class="quick-links">
            <h3>바로가기</h3>
            <div class="links-grid">
                <a href="<c:url value='/user/employee/info' />" class="quick-link">
                    <i class="fas fa-user"></i>
                    <span>인사조회</span>
                </a>
                <a href="<c:url value='/user/employee/appointment' />" class="quick-link">
                    <i class="fas fa-exchange-alt"></i>
                    <span>발령조회</span>
                </a>
                <a href="<c:url value='/user/employee/organization' />" class="quick-link">
                    <i class="fas fa-sitemap"></i>
                    <span>조직도</span>
                </a>
                <a href="<c:url value='/user/notice/list' />" class="quick-link">
                    <i class="fas fa-bullhorn"></i>
                    <span>공지사항</span>
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
