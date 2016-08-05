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

    <!-- BootStrapValidation CSS file -->
    <link rel="stylesheet" href="/bootstrap-validator/css/bootstrapValidator.min.css">

    <!-- Bootstrap JS -->
    <script src="/bootstrap-validator/js/bootstrapValidator.min.js" type="text/javascript"></script>


</head>
<body>

<div class="container">

    <form id="registrationForm" class="form-horizontal" role="form" action="signup" method="post">

        <div class="page-header"><h2>User Information</h2></div>

        <div class="form-group">
            <label class="control-label col-md-2" for="user_role">User role:</label>
            <div class="col-md-10">
                <select class="control-panel" id="user_role" name="user_role">
                    <option value="">CHOOSE ONE</option>
                    <option value="STUDENT">STUDENT</option>
                    <option value="TEACHER">TEACHER</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2" for="first_name">First Name:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="first_name" name="first_name"
                       placeholder="Enter First Name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="last_name">Last Name:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Enter Last Name">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-md-2" for="email">Email:</label>
            <div class="col-md-10">
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2" for="password">Password:</label>
            <div class="col-md-10">
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password">
            </div>

        </div>

        <div class="form-group">
            <label class="control-label col-md-2" for="confirm_password">Confirm Password:</label>
            <div class="col-md-10">
                <input type="password" class="form-control" id="confirm_password" name="confirm_password"
                       placeholder="Retype Password">
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

<script type="text/javascript">
    $(document).ready(function () {

        var validator = $("#registrationForm").bootstrapValidator({

            feedbackIcons: {
                valid: "glyphicon glyphicon-ok",
                invalid: "glyphicon glyphicon-remove",
                validating: "glyphicon glyphicon-refresh"
            },
            fields: {
                first_name: {
                    validators: {
                        notEmpty: {
                            message: "First Name must be filled"
                        },
                        regexp: {
                            regexp: "^[A-Za-z]*$",
                            message: "Only alphabetic letters are allowed"
                        }
                    }
                },
                last_name: {
                    validators: {
                        notEmpty: {
                            message: "Last Name must be filled"
                        },
                        regexp: {
                            regexp: "^[A-Za-z]*$",
                            message: "Only alphabetic letters are allowed"
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: "Email is required"
                        },
                        stringLength: {
                            min: 6,
                            max: 35,
                            message: "Email must be between 6 and 35"
                        },
                        emailAddress: {
                            message: "Email address was not valid"
                        }

                    }

                },
                password: {
                    validators: {
                        notEmpty: {
                            message: "Password is required"
                        },
                        stringLength: {
                            min: 8,
                            message: "Password must be at least 8 characters long"
                        },
                        different: {
                            field: "email",
                            message: "Email address and password must be different"
                        },
                        regexp: {
                            regexp: "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%\^&\*\(\)+=_]))",
                            message: "Password must contain  at least one lowercase letter, one uppercase letter, one digit and one special character"
                        }
                    }

                },
                confirm_password: {
                    validators: {
                        notEmpty: {
                            message: "Retype password"
                        },
                        identical: {
                            field: "password",
                            message: "Password and confirmed password must match"
                        }
                    }
                },
                user_role: {
                    validators: {
                        notEmpty: {
                            message: "User role is required"
                        }
                    }
                }

            }
        });
    });


</script>

</html>
