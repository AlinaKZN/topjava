<%--
  Created by IntelliJ IDEA.
  User: all
  Date: 19.10.2020
  Time: 21:10
  To change this template use File | Settings | File Templates.
  <jsp:useBean id="meals" scope="session"  class="ru.javawebinar.topjava.model.MealTo"/>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h3>Meals</h3>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>

        </tr>
    </c:forEach>
    <p><a href="meals?action=insert">Add</a></p>
</table>
</body>
</html>
