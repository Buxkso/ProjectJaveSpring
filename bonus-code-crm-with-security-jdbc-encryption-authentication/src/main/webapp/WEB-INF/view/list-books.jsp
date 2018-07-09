<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Book Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
			

			<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
			
				<!-- put new button: Add Book -->
			
				<input type="button" value="Add Book"
					   onclick="window.location.href='showFormForAdd'; return false;"
					   class="add-button"
				/>
			
			</security:authorize>
	
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>Name</th>
					<th>Reserved</th>
					<th>Reserved_from</th>
					<th>Reserved_to</th>
					<th>Owner</th>
					<th>Author</th>
					<th>Styles</th>
					
					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
					
						<th>Action</th>
					
					</security:authorize>
					
				</tr>
				
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
						<td> ${tempBook.name} </td>
						<td> ${tempBook.reserved} </td>
						<td> ${tempBook.reserved_from} </td>
						<td> ${tempBook.reserved_to} </td>
						<td> ${tempBook.theUsername} </td>
						<td> ${tempBook.theAuthor.fullname} </td>
						<td>

								<c:forEach var="tempStyle" items="${tempBook.styleList}">

									<div>${tempStyle.name}</div>

								</c:forEach>

					</td>

						<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
						
							<td>
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









