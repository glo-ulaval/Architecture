<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/app.css" rel="stylesheet">
</head>
<body>
	<c:import url = "header.jsp"/>
	<div class="container">
		<select>
			<option value="2009">2009</option>
			<option value="2010">2010</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>
		</select>
		<div class="row-fluid">
			<div class="span6">
				<h1>Cours disponibles</h1>
				<c:if test="${not empty list}">
					<table class="table table-striped">
							<thead>
								<tr>
									<th>Titre</th>
									<th>Credits</th>
									<th>Cycle</th>
									<th>Actions</th>
								</tr>
							</thead>
						<c:forEach var="course" items="${list}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<td>${course.credits}</td>
								<td>${course.cycle}</td>
								<td class="centered"><i class="icon-plus"></i></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<div class="span6">
			<h1>Offre de cours</h1>
				<c:if test="${not empty list}">
					<table class="table table-striped">
							<thead>
								<tr>
									<th>Titre</th>
									<th>Credits</th>
									<th>Cycle</th>
									<th class="centered">Actions</th>
								</tr>
							</thead>
						<c:forEach var="course" items="${list}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<td>${course.credits}</td>
								<td>${course.cycle}</td>
								<td class="centered"><i class="icon-minus"></i></td>
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
