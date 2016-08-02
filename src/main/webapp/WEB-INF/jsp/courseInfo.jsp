<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><u>Teachers:</u> <br/><br/>

<c:forEach items="${sessionScope.teachers}" var="teacher">
    ${teacher.getFirstName()} ${teacher.getLastName()}<br/>

</c:forEach>
</h3>
<h3><u>Course:</u> ${sessionScope.course.getName()} </h3>

<h3><u>Description:</u> ${sessionScope.course.getDescription()} </h3>

<h3><u>Students:</u> <br/><br/>

<c:forEach items="${sessionScope.students}" var="student">
    ${student.getFirstName()} ${student.getLastName()}<br/>

</c:forEach>
</h3>

</body>
</html>
