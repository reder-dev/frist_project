<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp" />
<jsp:include page="common/sidebar.jsp">
    <jsp:param name="menu" value="home" />
</jsp:include>

<div class="content">
    <h2>HR Genie 시스템에 오신 것을 환영합니다</h2>
    <p>왼쪽 메뉴에서 원하시는 기능을 선택해주세요.</p>
</div>

<jsp:include page="common/footer.jsp" />