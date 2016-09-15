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
            <th width="15%">Date</th>
            <th width="10%">Time</th>
            <th width="20%">Description</th>
            <th width="10%">Calories</th>
        </tr>
        <c:forEach items="${mealList}" var="meal">
            <tr id="${meal.exceed ? 'exceed': 'non-exceed'}">
                <td>${meal.id}</td>
                <td>${meal.dateTime.year} ${meal.dateTime.month} ${meal.dateTime.dayOfYear}</td>
                <td>${meal.dateTime.hour}:${meal.dateTime.minute}</td>
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
        <select name="day">
            <c:forEach var="d" begin="1" end="31" step="1">
                <option value="${d}">${d}</option>
            </c:forEach>
        </select>dd
        <select name="month">
            <c:forEach var="m" begin="1" end="12" step="1">
                <option value="${m}">${m}</option>
            </c:forEach>
        </select>mm
        <select name="year">
            <c:forEach var="y" begin="1988" end="2016" step="1">
                <option value="${y}">${y}</option>
            </c:forEach>
        </select>yyyy

        Time:<select name="minutes">
        <c:forEach var="h" begin="0" end="24" step="1">
            <c:forEach var="m" begin="00" end="50" step="10">
                <option value="${h * 60 + m}">${h}h ${m}m</option>
            </c:forEach>
        </c:forEach>
    </select><br>

        Meal:<input type="text" name="meal" value="meal">
        Calories:<input type="number" name="calories" value="500"><br>


        <input type="hidden" value="create" name="operation">
        <input type="submit" name="operation" value="submit">
    </form>
</div>
</body>
</html>
