<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/18/2016
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Signup Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Bootstrap JS -->
    <script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- FormValidation plugin and the class supports validating Bootstrap form -->
    <script src="/vendor/formvalidation/dist/js/formValidation.min.js"></script>
    <script src="/vendor/formvalidation/dist/js/framework/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <form class="form-horizontal" role="form" action="signup" method="post">

        <div class="page-header"><h2>User Information</h2></div>

        <div class="form-group">
            <label class="control-label col-md-2">User role:</label>
            <div class="col-md-10">
                <select class="control-panel" name="user_role">
                    <option>STUDENT</option>
                    <option>TEACHER</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">First Name:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="first_name" placeholder="Enter First Name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2">Last Name:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="last_name" placeholder="Enter Last Name">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-md-2">Email:</label>
            <div class="col-md-10">
                <input type="email" class="form-control" name="email" placeholder="Enter Email">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Password:</label>
            <div class="col-md-10">
                <input type="password" class="form-control" name="password" placeholder="Enter Password">
            </div>

        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Confirm Password:</label>
            <div class="col-md-10">
                <input type="password" class="form-control" name="retyped_password" placeholder="Retype Password">
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>

        <div style="color:red">${signupError}</div>
    </form>
</div>


</body>
</html>


<%--Password: <input type="password" name="password"/><br>--%>
<%--Confirm the password:  <input type="password" name="confirm_password"/><br>--%>


<%--</form>--%>
<%--</div>--%>