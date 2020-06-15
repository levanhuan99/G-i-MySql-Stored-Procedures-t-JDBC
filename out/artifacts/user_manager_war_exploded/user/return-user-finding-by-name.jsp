<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><a href="user?action=user">List All Users</a></p>
<table>
    <tr>
        <td>${requestScope["user"].getId()} </td>
    </tr>
    <tr>
        <td>${requestScope["user"].getName()}</td>
    </tr>
    <tr>
        <td>${requestScope["user"].getEmail()}</td>
    </tr>
    <tr>
        <td>${requestScope["user"].getCounty()}</td>
    </tr>
</table>
</body>
</html>
