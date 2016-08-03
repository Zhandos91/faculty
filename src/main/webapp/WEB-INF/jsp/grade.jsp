<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/20/2016
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Teacher Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="page-header">Grade Course</div>
<h2><u>CourseName:</u> ${sessionScope.course.getName()} </h2> <br/>

<form method="POST" action="submitgrade">
    <table>
        <tr>
            <th>Student</th>
            <th>Grade</th>
        </tr>
        <c:forEach items="${sessionScope.students}" var="student" varStatus="loop">
            <tr>
                <td> ${student.getFirstName()} ${student.getLastName()}</td>
                <td><input type="text" name="${student.getId()}"/></td>
            </tr>

        </c:forEach>
    </table>
    <br/>
    <button type="submit">GRADE</button>
    <div style="color:red">${gradeError}</div>
</form>

</div>
</body>
</html>
