<%--
  Created by IntelliJ IDEA.
  User: Zhandos_Suleimenov
  Date: 7/19/2016
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Course</title>
</head>
<body>


<form action="addcourse" method="POST">
      Course Name: <input type="text" name="courseName"/><br/>
      Description: <input type="text" name="description"/><br/>
<button type="submit">Add Course</button><br/>
<div style="color:red">${courseAddError}</div>
</form>
</body>
</html>
