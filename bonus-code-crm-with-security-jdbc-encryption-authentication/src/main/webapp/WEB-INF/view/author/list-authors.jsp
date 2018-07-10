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
    <title>List Authors</title>

    <!-- reference our style sheet -->

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>

<body>

<h1>List Authors</h1>

<div id="container">

    <div id="content">


        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

            <!-- put new button: Add Author -->

            <button onclick="window.location.href='showFormForAdd'; return false;" type="button"
                    class="btn btn-outline-primary"> Add Author
            </button>

        </security:authorize>
        <br>

        <!--  add our html table here -->

        <table class="table">

            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>

                <%-- Only show "Action" column for managers or admin --%>
                <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

                    <th scope="col">Action</th>

                </security:authorize>

            </tr>

            </thead>

            <tbody>
            <!-- loop over and print our authors -->
            <c:forEach var="tempAuthor" items="${authors}">

                <!-- construct an "update" link with author id -->
                <c:url var="updateLink" value="/author/showFormForUpdate">
                    <c:param name="authorId" value="${tempAuthor.id}"/>
                </c:url>

                <!-- construct an "delete" link with author id -->
                <c:url var="deleteLink" value="/author/delete">
                    <c:param name="authorId" value="${tempAuthor.id}"/>
                </c:url>

                <tr>
                    <td scope="row"> ${tempAuthor.name} </td>
                    <td scope="row"> ${tempAuthor.surname} </td>


                    <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

                        <td scope="row">
                            <!-- display the update link -->
                            <a href="${updateLink}">Update </a>


                            <security:authorize access="hasAnyRole('ADMIN')">
                                <a href="${deleteLink}"
                                   onclick="if (!(confirm('Are you sure you want to delete this author?'))) return false">
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
