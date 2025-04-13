<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HR Genie - 인사관리 시스템</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>
    <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
        <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="<c:url value='/'/>">HR Genie</a>
        <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <input class="form-control form-control-dark w-100" type="text" placeholder="검색" aria-label="Search">
        <div class="navbar-nav">
            <div class="nav-item text-nowrap">
                <c:choose>
                    <c:when test="${empty sessionScope.empId}">
                        <a class="nav-link px-3" href="<c:url value='/login'/>">로그인</a>
                    </c:when>
                    <c:otherwise>
                        <span class="nav-link px-3 text-white">${sessionScope.empName}님</span>
                        <a class="nav-link px-3" href="<c:url value='/logout'/>">로그아웃</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>