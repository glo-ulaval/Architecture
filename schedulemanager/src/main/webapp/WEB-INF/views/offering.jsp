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
	<c:import url="header.jsp" />
	<div class="container">
		<div class="hero-unit">
			<h1>Éditer/Visualiser une offre de cours</h1>
			<p>Choisissez l'année à visualiser/éditer.</p>
		</div>
		<c:if test="${not empty years}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Année</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="year" items="${years}">
					<tr id="${year}">
						<td><b>${year}</b></td>
						<td class="centered">
							<a class="btn" href="${year}"><i class="icon-search"></i></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
