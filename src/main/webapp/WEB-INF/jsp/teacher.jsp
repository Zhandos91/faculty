<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/18/2016
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Teacher Page</title>


</head>
<body>
<h3>Hello Prof.${teacher.getFirstName()} </h3>

<br/>

<c:forEach items="${sessionScope.teacher.courses}" var="course">

    <form method="GET" action="showcourse" style="float:left;margin-right:30px">

        <input type="hidden" name="course_id" value=${course.getId()} />
        <button type="submit">${course.getName()}</button>
    </form>

    <c:if test="${ course.getStatus() == 'open' }">
    <form method="GET" action="gradecourse">
        <input type="hidden" name="course_id" value=${course.getId()} />
        <button type="submit">grade</button>

    </form>
    </c:if>

    <c:if test="${ course.getStatus() == 'archived'}">
        <form method="GET" action="gradecourse">
            <input type="hidden" name="course_id" value=${course.getId()} />
            <button type="submit" style="display: none">grade</button>

        </form>
    </c:if>

<br/><br/>
</c:forEach>


<br/>
<br/>
<form action="addcourse" method="GET">

    <button type="submit">Add course</button>
</form>

<br/>
<br/>
<h1>
    <a href="${pageContext.request.contextPath}/do/logout">LOGOUT</a>
</h1>



</body>
</html>

