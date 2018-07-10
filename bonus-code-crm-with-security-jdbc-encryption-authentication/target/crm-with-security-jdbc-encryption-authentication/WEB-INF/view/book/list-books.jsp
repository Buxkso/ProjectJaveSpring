<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../system/header-with-menu.jsp" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Books</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

<h1>List Books</h1>

<div id="container">

	<div id="content">
			

			<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
			
				<!-- put new button: Add Book -->

				<button onclick="window.location.href='showFormForAdd'; return false;" type="button" class="btn btn-outline-primary"> Add
					Book
				</button>

			
			</security:authorize>
		<br>
		
			<!--  add our html table here -->

		<table class="table">

			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Reserved</th>
					<th scope="col">Reserved_from</th>
					<th scope="col">Reserved_to</th>
					<th scope="col">Owner</th>
					<th scope="col">Author</th>
					<th scope="col">Styles</th>
					
					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

						<th scope="col">Action</th>
					
					</security:authorize>
					
				</tr>
			</thead>

			<tbody>
				<!-- loop over and print our books -->
				<c:forEach var="tempBook" items="${books}">
				
					<!-- construct an "update" link with book id -->
					<c:url var="updateLink" value="/book/showFormForUpdate">
						<c:param name="bookId" value="${tempBook.book_id}" />
					</c:url>					

					<!-- construct an "delete" link with book id -->
					<c:url var="deleteLink" value="/book/delete">
						<c:param name="bookId" value="${tempBook.book_id}" />
					</c:url>
					<c:url var="addBookToCard" value="/book/bookToCart">
						<c:param name="bookId" value="${tempBook.book_id}" />
					</c:url>


					<tr>
						<td scope="row"> ${tempBook.name} </td>
						<td scope="row"> ${tempBook.reserved} </td>
						<td scope="row"> ${tempBook.reserved_from} </td>
						<td scope="row"> ${tempBook.reserved_to} </td>
						<td scope="row"> ${tempBook.theUsername} </td>
						<td scope="row"> ${tempBook.theAuthor.fullname} </td>
						<td scope="row">

								<c:forEach var="tempStyle" items="${tempBook.styleList}">

									<div>${tempStyle.name}</div>

								</c:forEach>

					</td>

						<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

						<td scope="row">
								<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
									<!-- display the update link -->
									<a href="${updateLink}">Update</a>
								</security:authorize>
	
								<security:authorize access="hasAnyRole('ADMIN')">
									<a href="${deleteLink}"
									   onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">Delete</a>
								</security:authorize>


						</security:authorize>
								<a href="${addBookToCard}">addToCart</a>

							</td>
												
					</tr>
				
				</c:forEach>
			</tbody>
			</table>
				
		</div>
	
	</div>

</body>

</html>









