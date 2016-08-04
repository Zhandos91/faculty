<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Course Info Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="page-header"><h3>Course Info</h3></div>

    <h4 class="text-primary">Teachers:</h4>
    <h4 class="text-success">
        <c:forEach items="${sessionScope.teachers}" var="teacher">
            ${teacher.getFirstName()} ${teacher.getLastName()}<br/>
        </c:forEach>
    </h4><br/>

    <h4 class="text-primary">Name:</h4> <h4 class="text-success">${sessionScope.course.getName()}</h4><br/>

    <h4 class="text-primary">Status:</h4> <h4 class="text-success">${sessionScope.course.getStatus()} </h4><br/>

    <h4 class="text-primary">Description:</h4> <h4 class="text-success">${sessionScope.course.getDescription()} </h4><br/>

    <h4 class="text-primary">Students:</h4>
    <h4 class="text-success">
        <c:forEach items="${sessionScope.students}" var="student">
            ${student.getFirstName()} ${student.getLastName()}<br/>
        </c:forEach>
    </h4>
</div>
</body>
</html>
