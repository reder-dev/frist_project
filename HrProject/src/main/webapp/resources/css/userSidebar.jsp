<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <ul class="menu-list">
        <li class="menu-item ${param.menu == 'personnel' ? 'active' : ''} has-submenu">
            <a href="#" class="menu-toggle">
                <span>인사관리</span>
            </a>
            <ul class="submenu ${param.menu == 'personnel' ? 'open' : ''}">
                <li><a href="<c:url value='/user/personnel/info' />">인사조회</a></li>
                <li><a href="<c:url value='/user/personnel/appointment' />">발령조회</a></li>
                <li><a href="<c:url value='/user/personnel/organization' />">조직도</a></li>
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
</div>