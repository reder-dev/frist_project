<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="currentMenu" value="dashboard" scope="request"/>

<jsp:include page="common/header.jsp" />

<div class="container-fluid">
    <div class="row">
        <jsp:include page="common/sidebar.jsp" />
        
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">대시보드</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group me-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">공유</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">내보내기</button>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <i class="bi bi-calendar"></i> 이번 주
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="bi bi-people"></i> 직원 현황
                        </div>
                        <div class="card-body">
                            <canvas id="employeeChart" width="100%" height="50"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="bi bi-calendar-check"></i> 근태 현황
                        </div>
                        <div class="card-body">
                            <canvas id="attendanceChart" width="100%" height="50"></canvas>
                        </div>
                    </div>
                </div>
            </div>

            <h2>최근 공지사항</h2>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성자</th>
                            <th scope="col">작성일</th>
                            <th scope="col">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="notice" items="${recentNotices}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td><a href="<c:url value='/notice/${notice.notId}'/>">${notice.notTi}</a></td>
                                <td>${notice.empId}</td>
                                <td>${notice.notWd}</td>
                                <td>0</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty recentNotices}">
                            <tr>
                                <td colspan="5" class="text-center">최근 공지사항이 없습니다.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

<jsp:include page="common/footer.jsp" />