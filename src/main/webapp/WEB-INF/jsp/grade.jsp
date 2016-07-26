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

<h2><u>CourseName:</u> ${sessionScope.course.getName()} </h2> <br/>

<form method="POST" action="submitgrade">
    <table>
        <tr>
            <th>Student</th>
            <th>Grade</th>
        </tr>
        <c:forEach items="${sessionScope.students}" var="student" varStatus="loop">
            <tr>
                <td> ${student.getName()} ${student.getSurname()}</td>
                <td><input type="text" name="${student.getId()}"/></td>
            </tr>

        </c:forEach>
    </table>
    <br/>
    <button type="submit">GRADE</button>
    <div style="color:red">${gradeError}</div>
</form>

</body>
</html>
