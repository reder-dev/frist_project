<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>출결 다운로드</title>
</head>
<body>
    <h2>출결 다운로드</h2>

    <form method="get" action="/attendance/download/excel">
        <button type="submit">Excel로 다운로드</button>
    </form>

    <form method="get" action="/attendance/download/pdf">
        <button type="submit">PDF로 다운로드</button>
    </form>
</body>
</html>
