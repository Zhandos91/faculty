<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/22/2016
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin Page</title>

</head>
<body>

<table>
    <tr>
        <th>Teacher</th>
        <th>Student</th>
        <th>Course</th>
        <th>Grade</th>
        <th>Date</th>
    </tr>
    <c:forEach items="${sessionScope.archives}" var="archive">
        <tr>
            <td>${archive.getTeacher()}</td>
            <td>${archive.getStudentName()}</td>
            <td>${archive.getCourseName()}</td>
            <td>${archive.getGrade()}</td>
            <td>${archive.getDate()}</td>
        </tr>
    </c:forEach>
</table>


<br/>
<h1>
    <a href="${pageContext.request.contextPath}/do/logout">LOGOUT</a>
</h1>

</body>
</html>
