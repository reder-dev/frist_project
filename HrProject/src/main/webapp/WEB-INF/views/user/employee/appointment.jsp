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
        <form action="<c:url value='/user/personnel/appointment' />" method="get">
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
                <th>이전부서</th>
                <th>이전직급</th>
                <th>발령부서</th>
                <th>발령직급</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>2025-03-27</td>
                <td>입사</td>
                <td>신규입사</td>
                <td>-</td>
                <td>-</td>
                <td>인사팀</td>
                <td>사원</td>
            </tr>
            <tr>
                <td>2025-06-15</td>
                <td>전보</td>
                <td>부서이동</td>
                <td>인사팀</td>
                <td>사원</td>
                <td>개발팀</td>
                <td>사원</td>
            </tr>
            <tr>
                <td>2026-01-01</td>
                <td>승진</td>
                <td>정기승진</td>
                <td>개발팀</td>
                <td>사원</td>
                <td>개발팀</td>
                <td>대리</td>
            </tr>
        </tbody>
    </table>
    
    <div class="pagination">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">&gt;</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />