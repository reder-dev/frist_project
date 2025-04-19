<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="notice" />
</jsp:include>

<div class="content">
    <h2>공지사항 수정</h2>

    <form action="<c:url value='/admin/notice/edit' />" method="post" enctype="multipart/form-data">
	    <input type="hidden" name="notId" value="${notice.notId}" />
	
	    <div class="form-group">
	        <label for="notTi">제목</label>
	        <input type="text" id="notTi" name="notTi" class="form-control" value="${notice.notTi}" required />
	    </div>
	
	    <div class="form-group">
	        <label for="notCn">내용</label>
	        <textarea id="notCn" name="notCn" class="form-control" rows="10" required>${notice.notCn}</textarea>
	    </div>
	
	    <div class="form-group">
	        <label for="notFile">첨부파일</label>
	        <input type="text" id="notFile" name="notFile" class="form-control" value="${notice.notFile}" />
	        <c:if test="${not empty notice.notFile}">
	            <p>현재 파일: ${notice.notFile}</p>
	        </c:if>
	    </div>
	
	    <div class="form-group">
	        <label for="notRegister">작성자</label>
	        <input type="text" id="notRegister" name="notRegister" class="form-control" value="${notice.notRegister}" readonly />
	    </div>
	
	    <div class="form-group text-center">
	        <button type="submit" class="btn btn-primary">수정 완료</button>
	        <a href="<c:url value='/admin/notice/manage' />" class="btn btn-secondary">취소</a>
	    </div>
	</form>

</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html> 
