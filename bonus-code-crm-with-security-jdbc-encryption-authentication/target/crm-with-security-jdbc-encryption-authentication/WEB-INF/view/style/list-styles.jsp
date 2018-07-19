<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../system/header-with-menu.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: Buxkso
  Date: 8. 7. 2018
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>

<html>

<head>
    <title>List Styles</title>

    <!-- reference our style sheet -->

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>

<body>

<h1>List Styles</h1>

<div id="container">

    <div id="content">


        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

            <!-- put new button: Add Style -->

            <button onclick="window.location.href='showFormForAdd'; return false;" type="button"
                    class="btn btn-outline-primary"> Add Style
            </button>

        </security:authorize>

        <br>
        <!--  add our html table here -->

        <table class="table">

            <thead>
            <tr>
                <th scope="col">Name</th>


                <%-- Only show "Action" column for managers or admin --%>
                <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

                    <th scope="col">Action</th>

                </security:authorize>

            </tr>
            </thead>

            <tbody>

            <!-- loop over and print our authors -->
            <c:forEach var="tempStyle" items="${styles}">

                <!-- construct an "update" link with author id -->
                <c:url var="updateLink" value="/style/showFormForUpdate">
                    <c:param name="styleId" value="${tempStyle.style_id}"/>
                </c:url>

                <!-- construct an "delete" link with author id -->
                <c:url var="deleteLink" value="/style/delete">
                    <c:param name="styleId" value="${tempStyle.style_id}"/>
                </c:url>

                <tr>
                    <td scope="row"> ${tempStyle.name} </td>


                    <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

                        <td scope="row">
                            <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                                <!-- display the update link -->
                                <a href="${updateLink}">Update </a>
                            </security:authorize>

                            <security:authorize access="hasAnyRole('ADMIN')">
                                <a href="${deleteLink}"
                                   onclick="if (!(confirm('Are you sure you want to delete this style?'))) return false">
                                    Delete</a>
                            </security:authorize>
                        </td>

                    </security:authorize>

                </tr>

            </c:forEach>
            </tbody>
        </table>

    </div>

</div>


</body>

</html>
