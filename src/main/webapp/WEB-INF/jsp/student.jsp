<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/21/2016
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Student Page</title>
</head>
<body>

Hello ${student.getFirstName()} ${student.getLastName()}
<br/>

<c:forEach items="${student.getCourses()}" var="course">
<form method="GET" action="showcourse">
    <input name="course_id" type=hidden value=${course.getId()} />
    <button type="submit">${course.getName()}</button>
</form>


    <br/>



</c:forEach>

<br/>

<form action="register" method="GET">
<button>Register For Course</button>
</form>
<br/>
<h1>
    <a href="${pageContext.request.contextPath}/do/logout">LOGOUT</a>
</h1>

</body>
</html>
