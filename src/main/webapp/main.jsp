<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h4>${requestScope.date}</h4>
<h1>${requestScope.name}</h1>
<table>
    <tr>
        <td>File</td>
        <td>Size</td>
        <td>Date</td>
    </tr>
    <tr>
        <td>
            <a href="?path=${requestScope.path.getParent().toFile().getAbsolutePath().substring(12).replace('\\', '/')}">/Up/</a><br>
        </td>
    </tr>
    <c:forEach var="file" items="${requestScope.files}">
        <tr>
            <td>
                <a href="./?path=${file.getAbsolutePath().substring(12).replace('\\', '/')}"
                   class="ml2">${file.getName()}/</a>
            </td>
            <td>
                    ${file.length()}
            </td>
            <td>
                    ${Date(file.lastModified())}
            </td>
<%--        <td>--%>
<%--            <form method="GET" >--%>
<%--                <button name="btnDownload" type="submit" value="${file.getPath()}">--%>
<%--                    Download--%>
<%--                </button>--%>
<%--            </form>--%>
<%--        </td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>