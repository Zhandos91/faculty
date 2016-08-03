<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/14/2016
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <form class="form-inline" role="form" action="login" focus="login" method="post">

        <div class="page-header"><h2>Login Information</h2></div>

        <div class="form-group">
            <label class="control-label">User Role:</label>
            <select class="control-panel" name="user_role">
                <option>ADMIN</option>
                <option>STUDENT</option>
                <option>TEACHER</option>
            </select>
        </div>

        <div class="form-group">
            <label class="control-label">Login:</label>
            <input type="text" class="form-control" name="login" placeholder="Enter login">
        </div>

        <div class="form-group">
            <label class="control-label">Password:</label>
            <input class="form-control" type="password" name="password" placeholder="Enter password">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
        <div style="color:red">${loginError}</div>
    </form>

</div>

</body>
</html>
