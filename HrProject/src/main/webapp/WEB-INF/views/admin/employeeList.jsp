<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
    <thead>
        <tr>
            <th>이름</th>
            <th>직급</th>
            <th>이메일</th>
            <th>조회</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.position}</td>
                <td>${employee.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/employees/edit/${employee.id}">조회</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
	
	
	
</body>
</html>