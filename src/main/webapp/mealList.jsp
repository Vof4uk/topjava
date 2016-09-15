<%--
  Created by IntelliJ IDEA.
  User: Микитенко
  Date: 12.09.2016
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>MealList</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>

<div>
    <table style="width:100%" border="1">
        <tr>
            <th width="10%">ID</th>
            <th width="15%">When?</th>
            <th width="20%">Description</th>
            <th width="10%">Calories</th>
        </tr>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed" scope="page"/>
            <tr id="${meal.exceed ? 'exceed': 'non-exceed'}">
                <td>${meal.id}</td>
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td width="2%">
                    <form action="${pageContext.request.contextPath}/meals" method="post">
                        <input type="hidden" value="${meal.id}" name="id"><br>
                        <input type="submit" name="operation" value="delete">
                    </form>
                </td>
                <td width="2%">
                    <form action="${pageContext.request.contextPath}/meals" method="post">
                        <input type="hidden" value="${meal.id}" name="id"><br>
                        <input type="submit" name="operation" value="update">
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/meals" method="post">
        Choose date:
        <input type="datetime-local" name="timestamp">

        Meal:<input type="text" name="meal" value="meal">
        Calories:<input type="number" name="calories" value="500"><br>


        <input type="hidden" value="create" name="operation">
        <input type="submit" name="operation" value="submit">
    </form>
</div>
</body>
</html>
