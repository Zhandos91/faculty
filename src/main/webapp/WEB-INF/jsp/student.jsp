<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/21/2016
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Student Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="page-header"><h2>Hello, ${student.getFirstName()} ${student.getLastName()}!</h2></div>


<c:forEach items="${student.getCourses()}" var="course">
<form role="form" method="GET" action="showcourse">
    <input name="course_id" type=hidden value=${course.getId()} />
    <button class="btn btn-default" type="submit">${course.getName()}</button>
</form>


</c:forEach>

<br/><br/>

<form role="form" action="register" method="GET">
<button class="btn btn-primary" type="submit">Register For Courses</button>
</form>

    <br/><br/>

    <h2><a href="${pageContext.request.contextPath}/do/logout">LOGOUT</a></h2>


</div>
</body>
</html>
