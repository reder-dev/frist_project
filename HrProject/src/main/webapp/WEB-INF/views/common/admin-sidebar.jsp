<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <ul class="menu-list">
        <li class="menu-item ${param.menu == 'personnel' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>인사관리</span>
            </a>
            <ul class="submenu ${param.menu == 'personnel' ? 'open' : ''}">
                <li><a href="<c:url value='/admin/employee/info' />">인사조회</a></li>
                <li><a href="<c:url value='/admin/employee/appointment' />">발령조회</a></li>
                <li><a href="<c:url value='/admin/employee/organization' />">조직도 조회</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'attendance' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>근태관리</span>
            </a>
            <ul class="submenu ${param.menu == 'attendance' ? 'open' : ''}">
                <li><a href="<c:url value='/admin/attendance/manage' />">근태관리</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'salary' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>급여관리</span>
            </a>
            <ul class="submenu ${param.menu == 'salary' ? 'open' : ''}">
                <li><a href="<c:url value='/salary' />">급여명세서 조회</a></li>
                <li><a href="<c:url value='/salary/admin' />">급여 지급 확정 처리</a></li>
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
                <li><a href="<c:url value='/admin/notice/manage' />">공지사항 관리</a></li>
            </ul>
        </li>
    </ul>
    
    <!-- 사용자 모드 전환 버튼 -->
    <div class="admin-switch">
        <a href="<c:url value='/user/main' />" class="admin-switch-btn user-mode">
            <i class="fas fa-user"></i> 사용자 모드로 전환
        </a>
    </div>
</div>