<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>발령조회 관리</h2>
    
    <div class="admin-actions">
        <button class="btn btn-primary" id="addAppointmentBtn">
            <i class="fas fa-plus"></i> 발령 추가
        </button>
        <button class="btn btn-secondary" id="exportExcelBtn">
            <i class="fas fa-file-excel"></i> Excel 내보내기
        </button>
    </div>
    
    <div class="search-box">
        <form action="<c:url value='/admin/personnel/appointment' />" method="get">
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
                    <label for="searchType">검색조건</label>
                    <select id="searchType" name="searchType" class="form-control">
                        <option value="name">이름</option>
                        <option value="empId">사원번호</option>
                        <option value="department">부서</option>
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
                <th>발령번호</th>
                <th>사원번호</th>
                <th>이름</th>
                <th>발령일자</th>
                <th>발령구분</th>
                <th>발령내용</th>
                <th>이전부서</th>
                <th>이전직급</th>
                <th>발령부서</th>
                <th>발령직급</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><input type="checkbox" class="appointment-select"></td>
                <td>AP001</td>
                <td>2510035</td>
                <td>홍길동</td>
                <td>2025-03-27</td>
                <td>입사</td>
                <td>신규입사</td>
                <td>-</td>
                <td>-</td>
                <td>인사팀</td>
                <td>사원</td>
                <td>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="AP001">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="AP001">삭제</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="appointment-select"></td>
                <td>AP002</td>
                <td>2510035</td>
                <td>홍길동</td>
                <td>2025-06-15</td>
                <td>전보</td>
                <td>부서이동</td>
                <td>인사팀</td>
                <td>사원</td>
                <td>개발팀</td>
                <td>사원</td>
                <td>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="AP002">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="AP002">삭제</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="appointment-select"></td>
                <td>AP003</td>
                <td>2510020</td>
                <td>정부장</td>
                <td>2026-01-01</td>
                <td>승진</td>
                <td>정기승진</td>
                <td>인사팀</td>
                <td>차장</td>
                <td>인사팀</td>
                <td>부장</td>
                <td>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="AP003">수정</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="AP003">삭제</button>
                </td>
            </tr>
        </tbody>
    </table>
    
    <div class="table-actions">
        <button class="btn btn-danger" id="deleteSelectedBtn">선택 삭제</button>
    </div>
    
    <div class="pagination">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">&gt;</a>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />