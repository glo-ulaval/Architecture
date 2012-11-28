<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/app.js" />" /></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" /></script>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="hero-unit">
			<h1>Réutiliser un horaire</h1>
			<p>Choisissez l'horaire à réutiliser :</p>
		</div>
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
		<c:if test="${not empty schedules}">
		<c:url var="scheduleurl" value="schedule"></c:url>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Horaires :</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="schedule" items="${schedules}">
					<c:if test="${id != schedule.id}">
						<tr id="${schedule.id}">
							<td class="span12">[${schedule.semester} ${schedule.year}] ${schedule.id}</td>
							<td class="centered">
								<c:choose>
								      <c:when test="${schedule.score <= 300}">
								      	<span class="badge badge-success">${schedule.score}</span>
								      </c:when>
	    							  <c:when test="${schedule.score <= 600 && schedule.score > 300}">
								      	<span class="badge badge-warning">${schedule.score}</span>
								      </c:when>
								      <c:otherwise>
								      	<span class="badge badge-important">${schedule.score}</span>
								      </c:otherwise>
								</c:choose>
							</td>
							<td class="centered">
								<a class="btn btn-label" href="<c:url value="/${scheduleurl}/${id}/reuseschedule/${schedule.id}" />">
									<i class="icon-plus"></i> Ajouter
								</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			
			<p>Légende des scores d'horaires</p>
			Bon : <span class="badge badge-success">0-300</span>
	      	Moyen : <span class="badge badge-warning">301-600</span>
	      	Pauvre : <span class="badge badge-important">601++</span>
		</c:if>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
