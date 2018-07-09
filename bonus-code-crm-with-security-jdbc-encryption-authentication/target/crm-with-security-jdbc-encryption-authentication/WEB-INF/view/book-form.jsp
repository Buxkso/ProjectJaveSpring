<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Book</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Book Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Book</h3>
	
		<form:form action="saveBook" modelAttribute="book" method="POST">

			<!-- need to associate this data with book id -->
			<form:hidden path="book_id" id="book_id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>Book name:</label></td>
						<td><form:input path="name" /></td>
					</tr>

					<tr>
						<td><label>Styles:</label></td>
						<td><form:select path="styleList" items="${styles}" multiple="true"  id="style_id" itemValue="style_id" itemLabel="name"/>

						</td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>

		
		<p>
			<a href="${pageContext.request.contextPath}/book/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










