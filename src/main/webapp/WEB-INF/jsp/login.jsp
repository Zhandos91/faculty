<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/14/2016
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>
<body>

<div>

    <form action="login" focus="login" method="post">
        <text>User: </text>

        <select name="userRole">
            <option value="STUDENT">Student</option>
            <option value="TEACHER">Teacher</option>
            <option value="ADMIN">Admin</option>
        </select>
        <br>
        Login:    <input type="text" name="login"/><br>
        Password: <input type="password" name="password"/><br>
        <button type="submit">Login</button>
        <div style="color:red">${loginError}</div>
    </form>
</div>

</body>
</html>
