<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h5 class="fw-bold">사원 목록</h5>

<c:choose>
    <c:when test="${not empty employees}">
        <ul class="list-group">
            <c:forEach var="emp" items="${employees}">
                <li class="list-group-item">
                    ${emp.empName} (${emp.empId})
                </li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning mt-2">
            해당 부서에는 사원이 없습니다.
        </div>
    </c:otherwise>
</c:choose>
