<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/app.css" rel="stylesheet">
</head>
<body>
	<c:import url = "header.jsp"/>
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h1>Offre de cours ${year}</h1>
				<c:if test="${not empty offering}">
					<table class="table table-striped">
							<thead>
								<tr>
									<th>Titre</th>
									<th>Credits</th>
									<th>Cycle</th>
									<th>Actions</th>
								</tr>
							</thead>
						<c:forEach var="course" items="${offering}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<td>${course.credits}</td>
								<td>${course.cycle}</td>
								<td class="centered"><button class="btn"><i class="icon-plus"></i></button></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<c:import url = "footer.jsp"/>
</body>
</html>
