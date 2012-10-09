<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
		<c:if test="${not empty schedules}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Horaires :</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="schedule" items="${schedules}">
					<tr id="${schedule.id}">
						<td><b>[ ${schedule.year} ] Horaire ${schedule.id}</b></td>
						<td class="centered">
							<a class="btn" href="schedule/${schedule.id}"><i class="icon-search"></i></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
