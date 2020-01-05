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
        <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
        Login: <br><input type="text" name="login" placeholder="<%=request.getParameter("login")%>"> <br>
        Password: <br><input type="text" name="password" placeholder="<%=request.getParameter("password")%>"> <br>
        Email: <br><input type="email" name="email" placeholder="<%=request.getParameter("email")%>"> <br>
        <input type="submit" value="Edit User">
    </form>
    <form action="index.jsp" method="POST">
        <input type="submit" value="Go back">
    </form>
</section>
</body>
</html>