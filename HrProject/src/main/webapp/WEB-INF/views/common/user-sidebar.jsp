<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <ul class="menu-list">
        <li class="menu-item ${param.menu == 'personnel' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>인사관리</span>
            </a>
            <ul class="submenu ${param.menu == 'personnel' ? 'open' : ''}">
                <li><a href="<c:url value='/user/employee/info' />">인사조회</a></li>
                <li><a href="<c:url value='/user/employee/appointment' />">발령조회</a></li>
                <li><a href="<c:url value='/user/employee/organization' />">조직도</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'attendance' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>근태관리</span>
            </a>
            <ul class="submenu ${param.menu == 'attendance' ? 'open' : ''}">
                <li><a href="<c:url value='/attendance/daily' />">일일 근태</a></li>
                <li><a href="<c:url value='/attendance/monthly' />">월간 근태</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'salary' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>급여관리</span>
            </a>
            <ul class="submenu ${param.menu == 'salary' ? 'open' : ''}">
                <li><a href="<c:url value='/salary' />">급여명세서 조회</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'approval' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>전자결재</span>
            </a>
            <ul class="submenu ${param.menu == 'approval' ? 'open' : ''}">
                <li><a href="<c:url value='/approval/apply' />">결재 신청</a></li>
                <li><a href="<c:url value='/approval/user' />">결재 신청 내역</a></li>
                <li><a href="<c:url value='/approval/admin' />">결재 승인/반려 처리</a></li>
            </ul>
        </li>
        
        <li class="menu-item ${param.menu == 'notice' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>공지사항</span>
            </a>
            <ul class="submenu ${param.menu == 'notice' ? 'open' : ''}">
                <li><a href="<c:url value='/user/notice/list' />">공지사항</a></li>
            </ul>
        </li>
    </ul>
    
    <!-- 관리자 전환 버튼 (관리자 권한이 있는 사용자에게만 표시) -->
    <c:if test="${sessionScope.user.isAdmin == true}">
        <div class="admin-switch">
            <a href="<c:url value='/admin/main' />" class="admin-switch-btn">
                <i class="fas fa-user-shield"></i> 관리자 모드로 전환
            </a>
        </div>
    </c:if>
</div>