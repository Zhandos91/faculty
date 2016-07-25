<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Teacher: ${sessionScope.teacher.getName()} </h3>

<h3>Course: ${sessionScope.course.getName()} </h3>

<h3>Students: <br/><br/>

<c:forEach items="${sessionScope.students}" var="student">
    ${student.getName()} ${student.getSurname()}<br/>

</c:forEach>
</h3>

</body>
</html>
