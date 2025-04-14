<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/sidebar.jsp">
    <jsp:param name="menu" value="personnel" />
</jsp:include>

<div class="content">
    <h2>직원 목록</h2>
    <div class="card">
        <table class="data-table">
            <thead>
                <tr>
                    <th>사번</th>
                    <th>이름</th>
                    <th>부서</th>
                    <th>직급</th>
                    <th>입사일</th>
                    <th>이메일</th>
                    <th>연락처</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1001</td>
                    <td>홍길동</td>
                    <td>개발팀</td>
                    <td>과장</td>
                    <td>2020-01-01</td>
                    <td>hong@example.com</td>
                    <td>010-1234-5678</td>
                    <td>
                        <button class="btn-sm btn-edit">수정</button>
                        <button class="btn-sm btn-delete">삭제</button>
                    </td>
                </tr>
                <tr>
                    <td>1002</td>
                    <td>김철수</td>
                    <td>인사팀</td>
                    <td>대리</td>
                    <td>2021-03-15</td>
                    <td>kim@example.com</td>
                    <td>010-2345-6789</td>
                    <td>
                        <button class="btn-sm btn-edit">수정</button>
                        <button class="btn-sm btn-delete">삭제</button>
                    </td>
                </tr>
                <tr>
                    <td>1003</td>
                    <td>이영희</td>
                    <td>마케팅팀</td>
                    <td>사원</td>
                    <td>2022-05-20</td>
                    <td>lee@example.com</td>
                    <td>010-3456-7890</td>
                    <td>
                        <button class="btn-sm btn-edit">수정</button>
                        <button class="btn-sm btn-delete">삭제</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />