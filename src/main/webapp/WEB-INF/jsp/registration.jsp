<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/21/2016
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <div class="page-header"><h3>List Of Courses</h3></div>
    <form role="form" action="register" method="POST">
        <c:forEach items="${courses}" var="course">
            <div class="radio">
                <label><input type="radio" name="course_id" value=${course.getId()}>${course.getName()}</label>
            </div>
        </c:forEach>

        <button class="btn btn-primary btn-sm" type="submit">Register</button>
    </form>


</div>
</body>
</html>
