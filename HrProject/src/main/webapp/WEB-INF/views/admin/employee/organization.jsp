<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>조직도 조회 관리</h2>
    
    <div class="admin-actions">
        <button class="btn btn-primary" id="editOrgStructureBtn">
            <i class="fas fa-edit"></i> 조직 구조 편집
        </button>
        <button class="btn btn-secondary" id="exportOrgChartBtn">
            <i class="fas fa-file-export"></i> 조직도 내보내기
        </button>
    </div>
    
    <div class="org-chart-container">
        <div class="org-chart">
            <div class="org-level level-1">
                <div class="org-node">
                    <div class="node-content">
                        <h4>대표이사</h4>
                        <p>김대표</p>
                    </div>
                </div>
            </div>
            
            <div class="org-level level-2">
                <div class="org-node">
                    <div class="node-content">
                        <h4>경영지원본부</h4>
                        <p>이사장</p>
                    </div>
                </div>
                <div class="org-node">
                    <div class="node-content">
                        <h4>개발본부</h4>
                        <p>박이사</p>
                    </div>
                </div>
                <div class="org-node">
                    <div class="node-content">
                        <h4>영업본부</h4>
                        <p>최이사</p>
                    </div>
                </div>
            </div>
            
            <div class="org-level level-3">
                <div class="org-node">
                    <div class="node-content">
                        <h4>인사팀</h4>
                        <p>정부장</p>
                    </div>
                </div>
                <div class="org-node">
                    <div class="node-content">
                        <h4>총무팀</h4>
                        <p>강부장</p>
                    </div>
                </div>
                <div class="org-node">
                    <div class="node-content">
                        <h4>웹개발팀</h4>
                        <p>조부장</p>
                    </div>
                </div>
                <div class="org-node">
                    <div class="node-content">
                        <h4>모바일개발팀</h4>
                        <p>윤부장</p>
                    </div>
                </div>
                <div class="org-node">
                    <div class="node-content">
                        <h4>국내영업팀</h4>
                        <p>한부장</p>
                    </div>
                </div>
                <div class="org-node">
                    <div class="node-content">
                        <h4>해외영업팀</h4>
                        <p>오부장</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="department-list">
        <h3>부서별 직원 목록</h3>
        
        <div class="department-filter">
            <label for="departmentSelect">부서 선택:</label>
            <select id="departmentSelect" class="form-control">
                <option value="all">전체</option>
                <option value="hr">인사팀</option>
                <option value="admin">총무팀</option>
                <option value="webdev">웹개발팀</option>
                <option value="mobiledev">모바일개발팀</option>
                <option value="domestic">국내영업팀</option>
                <option value="overseas">해외영업팀</option>
            </select>
        </div>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th>사원번호</th>
                    <th>이름</th>
                    <th>부서</th>
                    <th>직급</th>
                    <th>이메일</th>
                    <th>연락처</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>2510001</td>
                    <td>김대표</td>
                    <td>경영진</td>
                    <td>대표이사</td>
                    <td>ceo@hrgenie.com</td>
                    <td>010-1234-5678</td>
                    <td>
                        <button class="btn btn-sm btn-info view-btn" data-id="2510001">조회</button>
                        <button class="btn btn-sm btn-warning edit-btn" data-id="2510001">수정</button>
                    </td>
                </tr>
                <tr>
                    <td>2510010</td>
                    <td>이사장</td>
                    <td>경영지원본부</td>
                    <td>이사</td>
                    <td>director1@hrgenie.com</td>
                    <td>010-2345-6789</td>
                    <td>
                        <button class="btn btn-sm btn-info view-btn" data-id="2510010">조회</button>
                        <button class="btn btn-sm btn-warning edit-btn" data-id="2510010">수정</button>
                    </td>
                </tr>
                <tr>
                    <td>2510020</td>
                    <td>정부장</td>
                    <td>인사팀</td>
                    <td>부장</td>
                    <td>hrmanager@hrgenie.com</td>
                    <td>010-3456-7890</td>
                    <td>
                        <button class="btn btn-sm btn-info view-btn" data-id="2510020">조회</button>
                        <button class="btn btn-sm btn-warning edit-btn" data-id="2510020">수정</button>
                    </td>
                </tr>
                <tr>
                    <td>2510035</td>
                    <td>홍길동</td>
                    <td>인사팀</td>
                    <td>사원</td>
                    <td>hrgenie@naver.com</td>
                    <td>010-1111-1111</td>
                    <td>
                        <button class="btn btn-sm btn-info view-btn" data-id="2510035">조회</button>
                        <button class="btn btn-sm btn-warning edit-btn" data-id="2510035">수정</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />