<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<h1>Visualiser un horaire</h1>
			<p>Choisissez l'horaire à visualiser :</p>
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
					<tr id="${schedule.id}">
						<td class="span12">[${schedule.semester} ${schedule.year}] ${schedule.id}</td>
						<td class="centered">
							<a class="btn" href="${scheduleurl}/${schedule.id}"><i class="icon-search"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${scheduleurl}/delete/${schedule.id}"><i class="icon-trash icon-white"></i></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
