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

    <!-- BootStrapValidation CSS file -->
    <link rel="stylesheet" href="/bootstrap-validator/css/bootstrapValidator.min.css">

    <!-- Bootstrap JS -->
    <script src="/bootstrap-validator/js/bootstrapValidator.min.js" type="text/javascript"></script>
</head>
<body>

<div class="container">
    <form id="loginForm" class="form-inline" role="form" action="login" focus="login" method="post">

        <div class="page-header"><h2>Login Information</h2></div>

        <div class="form-group">
            <label class="control-label" for="user_role">User Role:</label>
            <select class="control-panel" id="user_role" name="user_role">
                <option value="">CHOOSE ONE</option>
                <option value="ADMIN">ADMIN</option>
                <option value="STUDENT">STUDENT</option>
                <option value="TEACHER">TEACHER</option>
            </select>
        </div>

        <div class="form-group">
            <label class="control-label" for="login">Login:</label>
            <input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
        </div>

        <div class="form-group">
            <label class="control-label" for="password">Password:</label>
            <input class="form-control" type="password" id="password" name="password" placeholder="Enter password">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
        <div style="color:red">${loginError}</div>
    </form>

</div>

</body>

<script type="text/javascript">
    $(document).ready(function () {
        var validator = $("#loginForm").bootstrapValidator({

            feedbackIcons: {
                valid: "glyphicon glyphicon-ok",
                invalid: "glyphicon glyphicon-remove",
                validating: "glyphicon glyphicon-refresh"
            },
            fields: {
                user_role: {
                    validators: {
                        notEmpty: {
                            message: "User role must be chosen"
                        }
                    }
                },
                login: {
                    validators: {
                        notEmpty: {
                            message: "Login must be entered"
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: "Password must be entered"
                        }
                    }
                }

            }
        });


    });


</script>
</html>
