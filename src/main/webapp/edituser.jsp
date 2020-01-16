<%--
  Created by IntelliJ IDEA.
  User: Alanorthon
  Date: 03.01.2020
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User Page</title>
</head>
<section class="fields">
    <form action="/edituser" method="POST">
        <input type="hidden" name="id" value=${id}>
        Login: <br><input type="text" name="login" value=${login}> <br>
        Password: <br><input type="text" name="password" value=${password}> <br>
        Email: <br><input type="email" name="email" value=${email}> <br>
        Role: <br><input type="text" name="role" value="${role}"><br>
        <input type="submit" value="Edit User">
    </form>
    <form action="/admin" method="GET">
        <input type="submit" value="Go back">
    </form>
    <form action="/user" method="GET">
        <input type="submit" value="Go to User Page">
    </form>
</section>
</body>
</html>