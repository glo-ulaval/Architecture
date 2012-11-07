<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app.js" />" /></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.js" />" /></script>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h1>Offre de cours ${year}</h1>
				<c:choose>
					<c:when test="${ empty error }"></c:when>
					<c:when test="${error != 'success'}">
						<div class="alert alert-error">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Erreur!</strong> Une erreur est survenue, veuillez
							réessayer - ${error}.
						</div>
					</c:when>
					<c:when test="${ error == 'success'}">
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Succès!</strong> La requête s'est effectuée avec succès.
						</div>
					</c:when>
				</c:choose>
				<c:if test="${not empty courses}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Titre</th>
								<th>Crédits</th>
								<th>Cycle</th>
								<th class="centered">Détails</th>
								<sec:authorize access="hasRole('ROLE_Directeur')">
									<th class="centered">Actions</th>
								</sec:authorize>
							</tr>
						</thead>
						<c:url var="courseurl" value="/course/"></c:url>
						<c:forEach var="course" items="${courses}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<td>${course.credits}</td>
								<td>${course.cycle}</td>
								<td class="centered"><a class="btn btn-info"
									href="${courseurl}${course.acronym}"><i
										class="icon-info-sign icon-white"></i></a></td>
								<sec:authorize access="hasRole('ROLE_Directeur')">
									<td class="centered"><a class="btn btn-danger"
										href="deletecourse?acronym=${course.acronym}"><i
											class="icon-trash icon-white"></i></a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
		<sec:authorize access="hasRole('ROLE_Directeur')">
		<div class="row-fluid">
			<div class="span12 well">
				<h3>Ajouter un cours à cette offre</h3>
				<c:url value="schedulemanager/offering/${year}/availablecourses"
					var="url" />
				<a href="/${url}" class="btn btn-success">Ajouter un cours
					&raquo;</a>
			</div>
		</div>
		</sec:authorize>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
