<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/18/2016
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Teacher Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">


<div class="page-header"><h2>Hello, Prof.${teacher.getLastName()}!</h2></div>

<h2><ul>Archived courses:</ul></h2>

<c:forEach items="${sessionScope.teacher.courses}" var="course">

    <c:if test="${ course.getStatus() == 'archived'}">
        <h2><a href="${pageContext.request.contextPath}/do/showcourse?course_id=${course.getId()}">${course.getName()}</a></h2>
    </c:if>

</c:forEach>

    <h2><ul>Open courses:</ul></h2>
    <c:forEach items="${sessionScope.teacher.courses}" var="course">


        <c:if test="${ course.getStatus() == 'open' }">

            <form class="form-inline" role="form" method="GET" action="gradecourse">
                <div class="form-group">

                    <label class="control-label"><a href="${pageContext.request.contextPath}/do/showcourse?course_id=${course.getId()}">${course.getName()}</a></label>
                <input class="form-control" type="hidden" name="course_id" value=${course.getId()} />
                <button class="btn btn-default btn-sm" type="submit">grade</button>
                </div>
            </form>
        </c:if>

        <br/><br/>
    </c:forEach>





<br/><br/>
<form role="form" action="addcourse" method="GET">

    <button class="btn btn-primary" type="submit">Add courses</button>
</form>

<br/><br/>
<h2><a href="${pageContext.request.contextPath}/do/logout">LOGOUT</a></h2>


</div>
</body>
</html>

