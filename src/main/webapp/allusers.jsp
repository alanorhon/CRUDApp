<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alanorthon
  Date: 06.01.2020
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of all registered Users</title>
</head>
<body>
<table style=" width: 30%; border: none">
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Password</th>
        <th>email</th>
        <th>Role</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="user">
        <tr>
            <td style="border: 1px solid black; text-align: left">${user.id}</td>
            <td style="border: 1px solid black; text-align: left">${user.login}</td>
            <td style="border: 1px solid black; text-align: left">${user.password}</td>
            <td style="border: 1px solid black; text-align: left">${user.email}</td>
            <td style="border: 1px solid black; text-align: left">${user.role}</td>
            <td>
                <form action="/edituser" method="GET">
                    <input type="submit" value="Edit User"/>
                    <input type="hidden" name="id" value=${user.id} />
                </form>
            </td>
            <td>
                <form action="/deleteuser" method="POST">
                    <input type="submit" value="Delete User"/>
                    <input type="hidden" name="id" value=${user.id} />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="/adduser" method="GET">
    <input type="submit" value="Add User">
</form>
<form action="index.jsp" method="GET">
    <input type="submit" value="Go back">
</form>
<form action="user.jsp" method="GET">
    <input type="submit" value="Go to User Page">
</form>

</body>
</html>
