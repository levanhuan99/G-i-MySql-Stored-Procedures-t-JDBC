<%--
  Created by IntelliJ IDEA.
  User: VanHuan
  Date: 6/4/2020
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h1>Edit user</h1>
<p><a href="user?action=user">List All Users</a></p>
<form method="post">
    <fieldset>
        <legend>Edit</legend>
        <table>
            <tr>
                <td>
                    <input type="hidden" name="id" value="${requestScope["user"].getId()}">
                </td>
            </tr>
            <tr>
                <td>Name:<input type="text" name="name" value="${requestScope["user"].getName()}"></td>
            </tr>
            <tr>
                <td>Email:<input type="text" name="email" value="${requestScope["user"].getEmail()}"></td>
            </tr>
            <tr>
                <td>Country:<input type="text" name="country" value="${requestScope["user"].getCountry()}"></td>
            </tr>

        </table>
        <input type="submit">
    </fieldset>
</form>
</body>
</html>