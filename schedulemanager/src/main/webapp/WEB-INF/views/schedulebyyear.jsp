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
		<div class="row-fluid">
			<div class="span12">
				<h1>Horaire ${year}</h1>
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
							<strong>Succès!</strong>La requête s'est effectuée avec succès.
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
		<ul class="nav nav-tabs">
		  <li><a href="#fall" data-toggle="tab">Automne</a></li>
		  <li><a href="#winter" data-toggle="tab">Hiver</a></li>
		  <li><a href="#summer" data-toggle="tab">Été</a></li>
		</ul>
		<div class="tab-content">
		  <div class="tab-pane active" id="fall">
		  <c:import url="scheduledetails.jsp" />
		  </div>
		  <div class="tab-pane" id="winter">le pénis de vince est bleu, il fait froid</div>
		  <div class="tab-pane" id="summer">derp</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
