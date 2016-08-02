<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/18/2016
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>
<body>

<div>

    <form action="signup" focus="login" method="post">
        <text>User: </text>

        <select name="userRole">
            <option value="STUDENT">Student</option>
            <option value="TEACHER">Teacher</option>
        </select>
        <br>
        Name: <input type="text" name="name" /><br/>
        Surname: <input type="text" name="surname"/><br/>
        Email: <input type="text" name="email"/><br/>

        Password: <input type="password" name="password"/><br>
        Confirm the password:  <input type="password" name="confirm_password"/><br>
        <button type="submit">Signup</button>
        <div style="color:red">${signupError}</div>
    </form>
</div>

</body>
</html>
