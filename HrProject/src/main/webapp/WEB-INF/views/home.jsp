<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    String userName = (String) session.getAttribute("userName");
    String deptTitle = (String) session.getAttribute("deptTitle");
    request.setAttribute("userName", userName);
    request.setAttribute("deptTitle", deptTitle);
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

<main class="main-content">
    <h1>환영합니다</h1>
    <p>여기는 메인 콘텐츠 영역입니다.</p>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>