<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>조직도</h2>

    <div class="org-chart-container">
        <div class="org-chart">
            <div class="org-level level-1">
                <div class="org-node">
                    <div class="node-content">
                        <h4>대표이사</h4>
                        <p>김대표</p> <!-- 고정 대표이사 -->
                    </div>
                </div>
            </div>

            <div class="org-level level-2">
                <c:forEach var="dept" items="${departments}">
                    <div class="org-node">
                        <div class="node-content">
                            <h4>${dept.dep_name}</h4>
                            <p>관리자 미정</p> <!-- 담당자 지정 가능 시 나중에 추가 -->
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <div class="department-list">
        <h3>부서 목록</h3>

        <table class="data-table">
            <thead>
                <tr>
                    <th>부서번호</th>
                    <th>부서명</th>
                    <th>등록자</th>
                    <th>등록일</th>
                    <th>수정자</th>
                    <th>수정일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dept" items="${departments}">
                    <tr>
                        <td>${dept.dep_id}</td>
                        <td>${dept.dep_name}</td>
                        <td>${dept.dep_register}</td>
                        <td><fmt:formatDate value="${dept.dep_registdate}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty dept.dep_modifier}">${dept.dep_modifier}</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty dept.dep_modifydate}">
                                    <fmt:formatDate value="${dept.dep_modifydate}" pattern="yyyy-MM-dd" />
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty departments}">
                    <tr>
                        <td colspan="6" style="text-align: center;">등록된 부서가 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />
