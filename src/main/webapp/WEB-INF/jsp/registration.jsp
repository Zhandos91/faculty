<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/21/2016
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<form action="register" method="POST">
<c:forEach items="${courses}" var="course">
    ${course.getName()}
    <input type="radio" name="course_id" value=${course.getId()}  />
    <br/>
</c:forEach>

    <button>Register</button>
</form>


</body>
</html>
