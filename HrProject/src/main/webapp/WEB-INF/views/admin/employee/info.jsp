<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>인사조회 관리</h2>
    
    <div class="admin-actions">
        <button class="btn btn-primary" id="addEmployeeBtn">
            <i class="fas fa-plus"></i> 직원 추가
        </button>
        <button class="btn btn-secondary" id="exportExcelBtn">
            <i class="fas fa-file-excel"></i> Excel 내보내기
        </button>
    </div>
    
    <div class="search-box">
        <form action="<c:url value='/admin/personnel/info' />" method="get">
            <div class="form-row">
                <div class="form-group">
                    <label for="department">부서</label>
                    <select id="department" name="department" class="form-control">
                        <option value="">전체</option>
                        <option value="인사팀">인사팀</option>
                        <option value="개발팀">개발팀</option>
                        <option value="영업팀">영업팀</option>
                        <option value="마케팅팀">마케팅팀</option>
                        <option value="경영지원팀">경영지원팀</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="position">직급</label>
                    <select id="position" name="position" class="form-control">
                        <option value="">전체</option>
                        <option value="사원">사원</option>
                        <option value="대리">대리</option>
                        <option value="과장">과장</option>
                        <option value="차장">차장</option>
                        <option value="부장">부장</option>
                        <option value="이사">이사</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="searchType">검색조건</label>
                    <select id="searchType" name="searchType" class="form-control">
                        <option value="name">이름</option>
                        <option value="empId">사원번호</option>
                        <option value="email">이메일</option>
                        <option value="phone">연락처</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="keyword">검색어</label>
                    <input type="text" id="keyword" name="keyword" class="form-control" placeholder="검색어를 입력하세요">
                </div>
                <div class="form-group">
                    <label>&nbsp;</label>
                    <button type="submit" class="btn btn-primary form-control">검색</button>
                </div>
            </div>
        </form>
    </div>
    
    <table class="data-table">
        <thead>
            <tr>
                <th><input type="checkbox" id="selectAll"></th>
                <th>사원번호</th>
                <th>이름</th>
                <th>부서</th>
                <th>직급</th>
                <th>이메일</th>
                <th>연락처</th>
                <th>입사일</th>
                <th>관리</th>
            </tr>
        </thead>
       <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td><input type="checkbox" class="employee-select"></td>
            <td>${employee.emp_id}</td>
            <td>${employee.emp_name}</td>
            <td>${employee.dep_name}</td>
            <td>${employee.rank_id}</td>
            <td>${employee.emp_email}</td>
            <td>${employee.emp_phone}</td>
            <td><fmt:formatDate value="${employee.emp_jd}" pattern="yyyy-MM-dd" /></td>
            <td>
                <button class="btn btn-sm btn-info view-btn" data-id="${employee.emp_id}">조회</button>
                <button class="btn btn-sm btn-warning edit-btn" data-id="${employee.emp_id}">수정</button>
                <button class="btn btn-sm btn-danger delete-btn" data-id="${employee.emp_id}">삭제</button>
            </td>
        </tr>
    </c:forEach>
</tbody>

    </table>
    
    <div class="table-actions">
        <button class="btn btn-danger" id="deleteSelectedBtn">선택 삭제</button>
    </div>
    
    <div class="pagination">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">&gt;</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />