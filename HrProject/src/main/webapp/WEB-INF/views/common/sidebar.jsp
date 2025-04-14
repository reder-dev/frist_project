<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <div class="menu-header">메뉴</div>
    <ul class="menu-list">
        <li class="menu-item ${param.menu == 'personnel' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <i class="icon user-icon"></i>
                <span>인사관리</span>
                <i class="arrow-icon"></i>
            </a>
            <ul class="submenu ${param.menu == 'personnel' ? 'open' : ''}">
                <li><a href="<c:url value='/personnel/list' />">직원 목록</a></li>
                <li><a href="<c:url value='/personnel/register' />">직원 등록</a></li>
                <li><a href="<c:url value='/personnel/department' />">부서 관리</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'attendance' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <i class="icon clock-icon"></i>
                <span>근태관리</span>
                <i class="arrow-icon"></i>
            </a>
            <ul class="submenu ${param.menu == 'attendance' ? 'open' : ''}">
                <li><a href="<c:url value='/attendance/daily' />">일일 근태</a></li>
                <li><a href="<c:url value='/attendance/monthly' />">월간 근태</a></li>
                <li><a href="<c:url value='/attendance/vacation' />">휴가 관리</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'salary' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <i class="icon dollar-icon"></i>
                <span>급여관리</span>
                <i class="arrow-icon"></i>
            </a>
            <ul class="submenu ${param.menu == 'salary' ? 'open' : ''}">
                <li><a href="<c:url value='/salary/payroll' />">급여 대장</a></li>
                <li><a href="<c:url value='/salary/tax' />">세금 관리</a></li>
                <li><a href="<c:url value='/salary/bonus' />">상여금 관리</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'approval' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <i class="icon document-icon"></i>
                <span>전자결재</span>
                <i class="arrow-icon"></i>
            </a>
            <ul class="submenu ${param.menu == 'approval' ? 'open' : ''}">
                <li><a href="<c:url value='/approval/draft' />">기안 작성</a></li>
                <li><a href="<c:url value='/approval/inbox' />">결재 대기함</a></li>
                <li><a href="<c:url value='/approval/outbox' />">결재 완료함</a></li>
            </ul>
        </li>
        <li class="menu-item ${param.menu == 'notice' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <i class="icon bell-icon"></i>
                <span>공지사항</span>
                <i class="arrow-icon"></i>
            </a>
            <ul class="submenu ${param.menu == 'notice' ? 'open' : ''}">
                <li><a href="<c:url value='/notice/list' />">공지 목록</a></li>
                <li><a href="<c:url value='/notice/write' />">공지 작성</a></li>
            </ul>
        </li>
    </ul>
</div>