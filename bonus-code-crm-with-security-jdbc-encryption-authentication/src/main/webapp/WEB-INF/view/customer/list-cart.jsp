<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<h1>Reserved Books</h1>

<div id="container">

    <div id="content">

        <!--  add our html table here -->


        <table class="table">

            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Action</th>

            </tr>
            </thead>

            <tbody>

            <!-- loop over and print our customers -->
            <c:forEach var="tempBooks" items="${reservedBooks}">
                <c:url var="cartRemove" value="/customer/cart-remove">
                    <c:param name="bookId" value="${tempBooks.book_id}"/>
                </c:url>

                <tr>
                    <td scope="row"> ${tempBooks.name} </td>
                    <td scope="row"><a href="${cartRemove}">Release</a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>

</div>
<c:if test="${fn:length(ownedBooks) > 0}">
    <h1>Owned Books</h1>

    <div id="container1">

        <div id="content1">

            <!--  add our html table here -->


            <table class="table">

                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Reserved_to</th>
                    <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                        <th scope="col">Action</th>
                    </security:authorize>

                </tr>
                </thead>

                <tbody>

                <!-- loop over and print our customers -->
                <c:forEach var="ownedBooks" items="${ownedBooks}">
                    <c:url var="cartReturn" value="/customer/return-book">
                        <c:param name="bookId" value="${ownedBooks.book_id}"/>
                    </c:url>

                    <tr>
                        <td scope="row"> ${ownedBooks.name} </td>
                        <td scope="row"> ${ownedBooks.reserved_to} </td>
                        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                            <td scope="row"><a href="${cartReturn}">Return</a>
                            </td>
                        </security:authorize>

                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>
</c:if>


</body>

</html>









