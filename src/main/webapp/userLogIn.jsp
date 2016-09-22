<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <form action="users" method="post"></form>
    <input type="hidden" name="action" value="log_in">
    Login:
    <select name="userName">
        <c:forEach items="userList" var="user">
            <option>${user}</option>
        </c:forEach>
    </select><br>
    Password:
    <input type="password" name="password">
    <input type="submit" value="Log in">
</div>