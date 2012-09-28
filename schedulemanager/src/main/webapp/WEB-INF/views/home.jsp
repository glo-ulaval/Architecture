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
			<h1></h1>
			<img src="assets/img/logo.png" alt=""/>
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
				<form>
					<input type="text" placeholder="IDUL">
					<input type="password" placeholder="Mot de passe"><br/>
					<!-- <button type="submit" class="btn">Se connecter</button> -->
					<a href="courseoffering" class="btn">Se connecter</a>
				</form>
			</div>
		</div>
	</div>
	<c:import url = "footer.jsp"/>
</body>

</html>
