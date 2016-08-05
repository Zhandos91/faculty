<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/14/2016
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="file://D:/test.js"></script>
</head>
<body>

<div class="container">
<div class="page-header"><h2>Welcome To Faculty Website!</h2></div>

<h4>
    News users, please, sign up <a href="${pageContext.request.contextPath}/do/signup">HERE</a>
<br/><br/>
    Returning users, please, log in <a href="${pageContext.request.contextPath}/do/login">HERE</a>
</h4>
</div>
</body>


</html>
