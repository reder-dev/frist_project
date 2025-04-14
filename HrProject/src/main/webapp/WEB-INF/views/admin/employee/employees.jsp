<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>직원 상세 정보</h2>

	<p><strong>사원번호:</strong> ${employee.emp_id}</p>
	<p><strong>이름:</strong> ${employee.emp_name}</p>
	<p><strong>성별:</strong> ${employee.emp_gender}</p>
	<p><strong>전화번호:</strong> ${employee.emp_phone}</p>
	<p><strong>이메일:</strong> ${employee.emp_email}</p>
	<p><strong>주소:</strong> ${employee.emp_address}</p>
	<p><strong>입사일:</strong> ${employee.emp_jd}</p>
	<p><strong>퇴사일:</strong> ${employee.emp_qd != null ? employee.emp_qd : "퇴사 정보 없음"}</p>
	<p><strong>부서번호:</strong> ${employee.dep_id}</p>
	<p><strong>부서명:</strong> ${employee.dep_name}</p>
	<p><strong>직책:</strong> ${employee.pos_id}</p>
	<p><strong>직급:</strong> ${employee.rank_id}</p>
	<p><strong>등록 일자:</strong> ${employee.emp_registdate}</p>
	<p><strong>등록자:</strong> ${employee.emp_register}</p>
	<p><strong>수정 일자:</strong> ${employee.emp_modifydate}</p>
	<p><strong>수정자:</strong> ${employee.emp_modifier}</p>
	<p><strong>사진:</strong> ${employee.emp_pht}</p>
	<p><strong>회사명:</strong> ${employee.emp_cn}</p>
	

	<a href="${pageContext.request.contextPath}/admin/employees/edit/${employee.emp_id}">
	
    	<button>수정</button>
	</a>
	
	
</body>
</html>