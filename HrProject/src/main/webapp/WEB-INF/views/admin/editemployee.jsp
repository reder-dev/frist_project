<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/admin/employees/edit" method="post">
    <input type="hidden" name="emp_id" value="${employee.emp_id}" />

    <label>사번:</label>
    <input type="text" name="emp_id" value="${employee.emp_id}" readonly />

    <label>이름:</label>
    <input type="text" name="emp_name" value="${employee.emp_name}" readonly />

    <label>비밀번호:</label>
    <input type="password" name="emp_pw" value="${employee.emp_pw}" readonly />

    <label>성별:</label>
    <input type="text" name="emp_gender" value="${employee.emp_gender}" readonly />

    <label>주민번호:</label>
    <input type="text" name="emp_jm" value="${employee.emp_jm}" readonly />

    <label>전화번호:</label>
    <input type="text" name="emp_phone" value="${employee.emp_phone}" readonly />

    <label>이메일:</label>
    <input type="text" name="emp_email" value="${employee.emp_email}" readonly />

    <label>주소:</label>
    <input type="text" name="emp_address" value="${employee.emp_address}" readonly />

    <label>등록일자:</label>
	<input type="hidden" name="emp_registdate" value="${employee.emp_registdate}" />

    <label>등록자:</label>
    <input type="text" name="emp_register" value="${employee.emp_register}" readonly />

    <label>사진:</label>
    <input type="text" name="emp_pht" value="${employee.emp_pht}" readonly />

   

    <!-- ✅ 수정 가능 -->
    <label>부서번호:</label>
    <input type="text" name="dep_id" value="${employee.dep_id}" />

    <label>부서명:</label>
    <input type="text" name="dep_name" value="${employee.dep_name}" />

    <label>직책:</label>
    <input type="text" name="pos_id" value="${employee.pos_id}" />

    <label>직급:</label>
    <input type="text" name="rank_id" value="${employee.rank_id}" />



    <label>회사명:</label>
    <input type="text" name="emp_cn" value="${employee.emp_cn}" />
	
	<fmt:formatDate value="${employee.emp_jd}" pattern="yyyy-MM-dd" var="empJdFormatted" />
	<fmt:formatDate value="${employee.emp_qd}" pattern="yyyy-MM-dd" var="empQdFormatted" />

	<label>입사일자:</label>
	<input type="date" name="emp_jd" value="${employee_jd}" />

	<label>퇴사일자:</label>
	<input type="date" name="emp_qd" value="${employee_qd}" />
    
    
    <button type="submit">수정 완료</button>
</form>






</body>
</html>