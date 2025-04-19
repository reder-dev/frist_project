<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/admin-sidebar.jsp">
    <jsp:param name="menu" value="appointment" />
</jsp:include>

<div class="content">
    <h2>발령 상세 정보</h2>

    <style>
        .appointment-detail {
            max-width: 900px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #fff;
        }

        .appointment-detail table {
            width: 100%;
            border-collapse: collapse;
        }

        .appointment-detail th,
        .appointment-detail td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .appointment-detail th {
            background-color: #f4f4f4;
            width: 150px;
        }

        .appointment-detail tr:last-child td {
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

    <div class="appointment-detail">
        <table>
            <tr>
                <th>발령번호</th>
                <td>${appointment.appId}</td>
                <th>사원번호</th>
                <td>${appointment.empId}</td>
            </tr>
            <tr>
                <th>발령종류</th>
                <td>${appointment.appCa}</td>
                <th>발령내용</th>
                <td>${appointment.appDt}</td>
            </tr>
            <tr>
                <th>발령일자</th>
                <td colspan="3">
                    <fmt:formatDate value="${appointment.appRegistdate}" pattern="yyyy-MM-dd HH:mm:ss" />
                </td>
            </tr>
            <tr>
                <th>등록자</th>
                <td>${appointment.appRegister}</td>
                <th>수정자</th>
                <td>${appointment.appModifier}</td>
            </tr>
            <tr>
                <th>수정일자</th>
                <td colspan="3">
                    <fmt:formatDate value="${appointment.appModifydate}" pattern="yyyy-MM-dd HH:mm:ss" />
                </td>
            </tr>
        </table>

        <div class="button-box">
            <a href="<c:url value='/admin/appointment/list' />" class="btn-secondary">목록</a>
        </div>
    </div>
</div>

<jsp:include page="../../common/footer.jsp" />

<script src="<c:url value='/resources/js/script.js' />"></script>
<script src="<c:url value='/resources/js/session-timer.js' />"></script>
</body>
</html>
