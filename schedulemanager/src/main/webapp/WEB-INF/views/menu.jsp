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
		<div class="hero-unit">
			<h1>Menu</h1>
			<p>Veuillez sélectionner une option :</p>
		</div>
		<div>
			<a class="btn" href="offering">Éditer/visualiser l'offre de cours   &raquo;</a><br/>
			<a class="btn" href="addschedule">Ajouter un horaire   &raquo;</a><br/>
			<a class="btn" href="schedule">Visualiser un horaire   &raquo;</a><br/>
		</div>
	</div>
	<c:import url = "footer.jsp"/>
</body>

</html>
