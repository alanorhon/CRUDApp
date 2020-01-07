<%--
  Created by IntelliJ IDEA.
  User: Alanorthon
  Date: 08.01.2020
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<H1><%
    if (request.getAttribute("message") != null) {
        out.println(request.getAttribute("message"));
    }
%></H1><br/>
<H2>You can do nothing on this page<br/>
    Just relax and look on this picture</H2>
<img src="https://i.gifer.com/fyDA.gif" align="center" width="300px" alt="pic"><br/>
<form action="index.jsp" method="GET">
    <input type="submit" value="Go to Authorisation page">
</form>
</body>
</html>
