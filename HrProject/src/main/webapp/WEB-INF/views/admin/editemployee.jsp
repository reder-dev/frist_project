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
<form action="${pageContext.request.contextPath}/admin/employees/update" method="post">
    <input type="hidden" name="id" value="${employee.id}" />

    <!-- ❌ 수정 불가능 (readonly 적용) -->
    <label>사번:</label>
    <input type="text" name="employeeId" value="${employee.employeeId}" readonly />

    <label>이름:</label>
    <input type="text" name="name" value="${employee.name}" readonly />

    <label>비밀번호:</label>
    <input type="password" name="password" value="${employee.password}" readonly />

    <label>성별:</label>
    <input type="text" name="gender" value="${employee.gender}" readonly />

    <label>주민번호:</label>
    <input type="text" name="socialSecurityNumber" value="${employee.socialSecurityNumber}" readonly />

    <label>전화번호:</label>
    <input type="text" name="phoneNumber" value="${employee.phoneNumber}" readonly />

    <label>이메일:</label>
    <input type="text" name="email" value="${employee.email}" readonly />

    <label>주소:</label>
    <input type="text" name="address" value="${employee.address}" readonly />

    <label>등록일자:</label>
    <input type="text" name="registrationDate" value="${employee.registrationDate}" readonly />

    <label>등록자:</label>
    <input type="text" name="registeredBy" value="${employee.registeredBy}" readonly />

    <label>사진:</label>
    <input type="text" name="photo" value="${employee.photo}" readonly />

    <label>발령번호:</label>
    <input type="text" name="appointmentNumber" value="${employee.appointmentNumber}" readonly />

    <!-- ✅ 수정 가능 -->
    <label>부서번호:</label>
    <input type="text" name="departmentNumber" value="${employee.departmentNumber}" />

    <label>부서명:</label>
    <input type="text" name="departmentName" value="${employee.departmentName}" />

    <label>직책:</label>
    <input type="text" name="position" value="${employee.position}" />

    <label>직급:</label>
    <input type="text" name="rank" value="${employee.rank}" />

    <label>수정일자:</label>
    <input type="text" name="modifiedDate" value="${employee.modifiedDate}" />

    <label>수정자:</label>
    <input type="text" name="modifiedBy" value="${employee.modifiedBy}" />

    <label>회사명:</label>
    <input type="text" name="companyName" value="${employee.companyName}" />

    <button type="submit">수정 완료</button>
</form>




</body>
</html>