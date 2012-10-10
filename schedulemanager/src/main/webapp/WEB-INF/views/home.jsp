<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:import url = "header.jsp"/>
	<div class="container">
		<div class="hero-unit">
			<h1></h1>
			<img src="resources/img/logo.png" alt=""/>
			<p>Bienvenue sur l'application ScheduleManager d�velopp�e dans le
				cadre du cours "GLO-4003 - Architecture Logicielle".</p>
		</div>
		<div class="row-fluid">
			<div class="span6 well fixed_height">
				<h2>Gestion des horaires automatis�e IFT-GLO</h2>
				<h4>De quoi faire r�ver toutes les autres facult�s.</h4>
				<p class="justified">
				Entrez les disponibilit�s des professeurs,
				s�lectionnez l'offre de cours pour l'ann�e et laissez le syst�me vous proposer un horaire. Remaniez l'horaire � votre guise et
				suivez les recommandations du syst�me afin d'�viter les conflits.
				</p>
			</div>
			<div class="span6 well fixed_height">
				<h2>Zone Membre</h2>
				<p>Zone membre pour organiser, visualiser et accepter vos horaires.</p>
				<c:if test="${not empty loginError}">
					<div class="alert alert-error">
						<button type="button" class="close" data-dismiss="alert">�</button>
						${loginError}
					</div>
				</c:if>
				<form name='login' action="<c:url value='j_spring_security_check' />" method='POST'>
					<input type="text" name="j_username" placeholder="IDUL">
					<input type="password" name="j_password" placeholder="Mot de passe"><br/>
					<button type="submit" name="submit" class="btn">Se connecter</button>
				</form>
			</div>
		</div>
	</div>
	<c:import url = "footer.jsp"/>
</body>

</html>
