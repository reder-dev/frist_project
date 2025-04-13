<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link ${currentMenu == 'dashboard' ? 'active' : ''}" href="<c:url value='/'/>">
                    <i class="bi bi-house-door"></i> 대시보드
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${currentMenu == 'employee' ? 'active' : ''}" href="<c:url value='/employee'/>">
                    <i class="bi bi-people"></i> 직원 관리
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${currentMenu == 'department' ? 'active' : ''}" href="<c:url value='/department'/>">
                    <i class="bi bi-diagram-3"></i> 부서 관리
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${currentMenu == 'attendance' ? 'active' : ''}" href="<c:url value='/attendance'/>">
                    <i class="bi bi-calendar-check"></i> 근태 관리
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${currentMenu == 'approval' ? 'active' : ''}" href="<c:url value='/approval'/>">
                    <i class="bi bi-file-earmark-text"></i> 결재 관리
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${currentMenu == 'notice' ? 'active' : ''}" href="<c:url value='/notice'/>">
                    <i class="bi bi-megaphone"></i> 공지사항
                </a>
            </li>
        </ul>

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            <span>관리자 메뉴</span>
        </h6>
        <ul class="nav flex-column mb-2">
            <li class="nav-item">
                <a class="nav-link ${currentMenu == 'settings' ? 'active' : ''}" href="<c:url value='/settings'/>">
                    <i class="bi bi-gear"></i> 시스템 설정
                </a>
            </li>
        </ul>
    </div>
</nav>