<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../system/header-with-menu.jsp" %>

<!DOCTYPE html>

<html>

<head>
    <title>List Customers</title>

    <!-- reference our style sheet -->

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>

<body>

<h1>List Customers</h1>

<div id="container">

    <div id="content">

        <p>
            User: <security:authentication property="principal.username"/>, Role(s): <security:authentication
                property="principal.authorities"/>
        </p>


        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

            <!-- put new button: Add Customer -->

            <button onclick="window.location.href='/register/showRegistrationForm'; return false;" type="button" class="btn btn-outline-primary"> Add
                Customer
            </button>
            <%--<div>
                <a href="${pageContext.request.contextPath}/register/showRegistrationForm" class="btn btn-outline-primary" role="button" aria-pressed="true">Add Customer</a>
            </div>--%>

        </security:authorize>
        <br>

        <!--  add our html table here -->


        <table class="table">

            <thead>
            <tr>
                <th scope="col">Username</th>
                <%-- Only show "Action" column for managers or admin --%>
                <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                    <th scope="col">Action</th>
                </security:authorize>
            </tr>
            </thead>

            <tbody>

            <!-- loop over and print our customers -->
            <c:forEach var="tempCustomer" items="${customers}">
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.userName}" />
                </c:url>
                <c:url var="shCart" value="/customer/showUserCart">
                    <c:param name="customerId" value="${tempCustomer.userName}" />
                </c:url>

                <tr>
                    <td scope="row"> ${tempCustomer.userName} </td>
                    <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                    <td scope="row"><a href="${updateLink}">Confirm Cart | </a><a href="${shCart}">Show Cart</a></td>
                    </security:authorize>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>

</div>


</body>

</html>









