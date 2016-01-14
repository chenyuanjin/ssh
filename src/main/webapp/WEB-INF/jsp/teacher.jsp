<%--
  Created by IntelliJ IDEA.
  User: chenyj
  Date: 16/1/14
  Time: 上午10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring MVC 注解测试页</title>
</head>
<body>
<c:forEach items="${students}" var="student" varStatus="i">
    老师:${teacher} 学生:${student.name}<br>
    </c:forEach>
</body>
</html>
