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

    <div class="page-header"><label><h3>Hello, ${student.getFirstName()} ${student.getLastName()}!</h3></label> <label
            class="col-md-offset-8"><h3><a href="${pageContext.request.contextPath}/do/logout">LOGOUT</a></h3></label>
    </div>

    <h3 class="bg-success">Courses:</h3>


<c:forEach items="${student.getCourses()}" var="course">

    <div class="form-group">
        <label class="control-label"><a
                href="${pageContext.request.contextPath}/do/showcourse?course_id=${course.getId()}" >${course.getName()}</a></label>
    </div>
</c:forEach>

<br/><br/>

<form role="form" action="register" method="GET">
<button class="btn btn-primary btn-sm" type="submit">Register For Courses</button>
</form>

</div>
</body>
</html>
