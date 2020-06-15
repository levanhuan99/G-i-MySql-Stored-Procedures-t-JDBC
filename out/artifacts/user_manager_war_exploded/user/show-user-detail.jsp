<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VanHuan
  Date: 6/4/2020
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User detail</title>
</head>
<body>
<h1>User detail</h1>
<table border="1 solid">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Email</td>
        <td>Country</td>
    </tr>
    <tr>
        <td>${requestScope["user"].getId()} </td>
        <td>${requestScope["user"].getName()}</td>
        <td>${requestScope["user"].getEmail()}</td>
        <td>${requestScope["user"].getCountry()}</td>
    </tr>


</table>
</body>
</html>
