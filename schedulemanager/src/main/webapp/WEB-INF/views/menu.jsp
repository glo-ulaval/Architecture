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
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
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
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<p>Vous êtes connecté en tant que : ${user.idul}</p>
		</div>
		<div class="hero-unit">
			<h1>Menu</h1>
			<p>Veuillez sélectionner une option :</p>
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
		<sec:authorize access="hasRole('ROLE_Administrateur')">
			<c:import url="partialViews/menuAdmin.jsp" />
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_Directeur')">
			<c:import url="partialViews/menuDirector.jsp" />
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_Enseignant')">
			<c:import url="partialViews/menuTeacher.jsp" />
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_Responsable')">
			<c:import url="partialViews/menuManager.jsp" />
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_Usager')">
			<c:import url="partialViews/menuUser.jsp" />
		</sec:authorize>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
