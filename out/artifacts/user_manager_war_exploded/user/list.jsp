<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VanHuan
  Date: 6/4/2020
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>List user</title>
</head>
<body>
<h1>Users</h1>
<h3><a href="/user?action=create">Create user</a> </h3>
<h3><a href="/user?action=findUserByName">Find user by name</a></h3>
<h3><a href="/user?action=sortByName">Sort By Name</a></h3>
    <legend>all user</legend>
    <table border="1 solid">
        <tr>
            <td>name</td>
            <td>ID</td>
            <td>email</td>
            <td>country</td>
            <td>edit</td>
            <td>delete</td>


        </tr>
        <c:forEach items="${listUser}" var="user">
            <tr>
                <td><a href="/user?action=view&id=${user.getId()}" >${user.getName()}</a></td>
                <td><c:out value="${user.getId()}"></c:out></td>
                <td><c:out value="${user.getEmail()}"></c:out></td>
                <td><c:out value="${user.getCountry()}"></c:out></td>
                <td><a href="/user?action=edit&id=${user.getId()}">edit</a> </td>
                <td><a href="/user?action=delete&id=${user.getId()}">delete</a></td>
            </tr>
        </c:forEach>

    </table>


</body>
</html>
