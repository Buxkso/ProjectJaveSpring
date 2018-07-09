<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "header.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: Buxkso
  Date: 8. 7. 2018
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div>${username}</div>
<div>
<c:forEach var="tempRoles" items="${roles}">
    <div>
            ${tempRoles.authority}
    </div>
</c:forEach>
</div>
</body>
</html>
