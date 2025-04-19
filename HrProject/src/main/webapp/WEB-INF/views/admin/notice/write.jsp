<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 작성</h2>

	    <form action="<c:url value='/admin/notice/write' />" method="post" class="notice-form">
	    <div class="form-group">
	        <label for="notTi">제목</label>
	        <input type="text" id="notTi" name="notTi" required class="form-control" />
	    </div>
	
	    <div class="form-group">
	        <label for="notCn">내용</label>
	        <textarea id="notCn" name="notCn" rows="8" class="form-control" required></textarea>
	    </div>
	
	    <div class="form-group">
	        <label for="notFile">첨부파일명</label>
	        <input type="text" id="notFile" name="notFile" class="form-control" placeholder="예: 1234abcd-uuid.pdf" />
	    </div>
	
	    <div class="form-group">
	        <label for="empId">사원번호</label>
	        <input type="text" id="empId" name="empId" required class="form-control" />
	    </div>
	
	    <div class="form-actions">
	        <input type="submit" value="등록" class="btn btn-primary" />
	        <a href="<c:url value='/admin/notice/manage' />" class="btn btn-secondary">취소</a>
	    </div>
	</form>

</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html> 

