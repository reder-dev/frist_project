<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="employee" />
</jsp:include>

<div class="content">
    <h2>사원 상세 정보</h2>

    <style>
        .employee-detail {
            max-width: 900px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #fff;
        }
        .employee-detail table {
            width: 100%;
            border-collapse: collapse;
        }
        .employee-detail th, .employee-detail td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .employee-detail th {
            background-color: #f4f4f4;
            width: 150px;
        }
        .employee-detail tr:last-child td {
            border-bottom: none;
        }
        .button-box {
            text-align: right;
            margin-top: 20px;
        }
        .btn-primary {
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            margin-left: 10px;
        }
        .btn-secondary {
            padding: 8px 16px;
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 4px;
            text-decoration: none;
        }
    </style>

    <div class="employee-detail">
        <table>
            <tr>
                <th>사번</th>
                <td>${employee.empId}</td>
                <th>이름</th>
                <td>${employee.empName}</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${employee.empGender}</td>
                <th>주민번호</th>
                <td>${employee.empJm}</td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>${employee.empPhone}</td>
                <th>이메일</th>
                <td>${employee.empEmail}</td>
            </tr>
            <tr>
                <th>주소</th>
                <td colspan="3">${employee.empAddress}</td>
            </tr>
            <tr>
                <th>입사일</th>
                <td><fmt:formatDate value="${employee.empJd}" pattern="yyyy-MM-dd" /></td>
                <th>퇴사일</th>
                <td>
                    <c:choose>
                        <c:when test="${not empty employee.empQd}">
                            <fmt:formatDate value="${employee.empQd}" pattern="yyyy-MM-dd" />
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th>부서</th>
                <td>${employee.depName}</td>
                <th>직책</th>
                <td>${employee.posId}</td>
            </tr>
            <tr>
                <th>직급</th>
                <td>${employee.rankId}</td>
                <th>회사명</th>
                <td>${employee.empCn}</td>
            </tr>
            <tr>
                <th>등록자</th>
                <td>${employee.empRegister}</td>
                <th>등록일</th>
                <td>
                    <fmt:formatDate value="${employee.empRegistdate}" pattern="yyyy-MM-dd HH:mm:ss" />
                </td>
            </tr>
            <tr>
                <th>수정자</th>
                <td>${employee.empModifier}</td>
                <th>수정일</th>
                <td>
                    <fmt:formatDate value="${employee.empModifydate}" pattern="yyyy-MM-dd HH:mm:ss" />
                </td>
            </tr>
        </table>

        <div class="button-box">
            <a href="<c:url value='/admin/employee/list' />" class="btn-secondary">목록</a>
            <a href="<c:url value='/admin/employee/edit/${employee.empId}' />" class="btn-primary">수정하기</a>
        </div>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>