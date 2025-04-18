<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/user-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>발령조회</h2>

    <div class="search-box">
        <form action="<c:url value='/user/employee/appointment' />" method="get">
            <div class="form-row">
                <div class="form-group">
                    <label for="startDate">시작일</label>
                    <input type="date" id="startDate" name="startDate" class="form-control">
                </div>
                <div class="form-group">
                    <label for="endDate">종료일</label>
                    <input type="date" id="endDate" name="endDate" class="form-control">
                </div>
                <div class="form-group">
                    <label for="appointmentType">발령구분</label>
                    <select id="appointmentType" name="appointmentType" class="form-control">
                        <option value="">전체</option>
                        <option value="입사">입사</option>
                        <option value="승진">승진</option>
                        <option value="전보">전보</option>
                        <option value="퇴직">퇴직</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">검색</button>
                </div>
            </div>
        </form>
    </div>

    <table class="data-table">
        <thead>
            <tr>
                <th>발령일자</th>
                <th>발령구분</th>
                <th>발령내용</th>
                <th>발령자</th>
                <th>발령수정자</th>
                <th>발령번호</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td>
                        <fmt:formatDate value="${appointment.appRegistdate}" pattern="yyyy-MM-dd" />
                    </td>
                    <td>${appointment.appCa}</td>
                    <td>${appointment.appDt}</td>
                    <td>${appointment.appRegister}</td>
                    <td>
                        <c:out value="${appointment.appModifier}" default="-" />
                    </td>
                    <td>${appointment.appId}</td>
                </tr>
            </c:forEach>

            <c:if test="${empty appointments}">
                <tr>
                    <td colspan="6" style="text-align: center;">조회된 발령 정보가 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

    <div class="pagination">
        <!-- 실제 페이징 구현 시 여기에 동적 링크 처리 -->
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">&gt;</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
