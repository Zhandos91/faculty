<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/20/2016
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>CourseName: ${sessionScope.course.getName()} </h2> <br/>
<h3> Student ------ Grade</h3> <br/>

<form method="POST" action="submitgrade">

<c:forEach items="${sessionScope.students}" var="student" varStatus="loop">

     ${student.getName()}  ${student.getSurname()}

    <input type="text" name="${student.getId()}"/>
<br/>
</c:forEach>

<br/>


    <button type="submit">GRADE</button>

</form>
</body>
</html>
