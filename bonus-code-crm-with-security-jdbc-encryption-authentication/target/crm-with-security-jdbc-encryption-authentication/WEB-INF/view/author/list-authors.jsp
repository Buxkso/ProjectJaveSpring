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
          href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Author Relationship Manager</h2>
    </div>
</div>

<div id="container">

    <div id="content">
        


        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

            <!-- put new button: Add Author -->

            <input type="button" value="Add Author"
                   onclick="window.location.href='showFormForAdd'; return false;"
                   class="add-button"
            />

        </security:authorize>


        <!--  add our html table here -->

        <table>
            <tr>
                <th>Name</th>
                <th>Surname</th>

                <%-- Only show "Action" column for managers or admin --%>
                <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

                    <th>Action</th>

                </security:authorize>

            </tr>

            <!-- loop over and print our authors -->
            <c:forEach var="tempAuthor" items="${authors}">

                <!-- construct an "update" link with author id -->
                <c:url var="updateLink" value="/author/showFormForUpdate">
                    <c:param name="authorId" value="${tempAuthor.id}" />
                </c:url>

                <!-- construct an "delete" link with author id -->
                <c:url var="deleteLink" value="/author/delete">
                    <c:param name="authorId" value="${tempAuthor.id}" />
                </c:url>

                <tr>
                    <td> ${tempAuthor.name} </td>
                    <td> ${tempAuthor.surname} </td>
                 

                    <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

                        <td>
                            <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                                <!-- display the update link -->
                                <a href="${updateLink}">Update</a>
                            </security:authorize>

                            <security:authorize access="hasAnyRole('ADMIN')">
                                <a href="${deleteLink}"
                                   onclick="if (!(confirm('Are you sure you want to delete this author?'))) return false">Delete</a>
                            </security:authorize>
                        </td>

                    </security:authorize>

                </tr>

            </c:forEach>

        </table>

    </div>

</div>

<p></p>

<!-- Add a logout button -->
<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">

    <input type="submit" value="Logout" class="add-button" />

</form:form>

</body>

</html>
