<%--
  Created by IntelliJ IDEA.
  User: VanHuan
  Date: 6/6/2020
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create new user</h1>
<form method="post">
    <table border="1 solid">
        <tr>
            <td><input name="id" type="number" placeholder="enter your id"></td>
        </tr>
        <tr>
            <td><input name="name" type="text" placeholder="enter your name"></td>
        </tr>
        <tr>
            <td><input name="email" type="text" placeholder="enter your email"></td>
        </tr>
        <tr>
            <td><input name="country" type="text" placeholder="enter your country"></td>
        </tr>
    </table>
    <input type="submit">

</form>
</body>
</html>
