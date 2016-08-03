<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/19/2016
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Signup Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="page-header"><h2>Add Course</h2></div>

<form class="form-inline" role="form" action="addcourse" method="POST">

    <div class="form-group">
        <label class="control-label">Course Name:</label>
        <input class="form-control" type="text" name="courseName" placeholder="Enter Course Name">
    </div>
    <div class="form-group">
        <label class="control-label">Enter Description:</label>
        <input class="form-control" type="text" name="description" placeholder="Enter Description">
    </div>

<button class="btn btn-primary" type="submit">Add Course</button><br/>
<div style="color:red">${courseAddError}</div>
</form>

</div>
</body>
</html>
