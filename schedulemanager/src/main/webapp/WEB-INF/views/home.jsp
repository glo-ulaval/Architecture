<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/app.css" rel="stylesheet">
</head>
<body>
	<header>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="#"><img src="assets/img/logo_mini.png"/>ScheduleManager</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="#">Accueil</a></li>
						<li><a href="#">À propos</a></li>
						<li><a href="https://github.com/glo-ulaval/Architecture">Source</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</header>
	<div class="container">
		<div class="hero-unit">
			<h1></h1>
			<img src="assets/img/logo.png"/>
			<p>Bienvenue sur l'application ScheduleManager développée dans le
				cadre du cours "GLO-4003 - Architecture Logicielle".</p>
		</div>
		<div class="row-fluid">
			<div class="span6 well fixed_height">
				<h2>Gestion des horaires automatisé IFT-GLO</h2>
				<h4>De quoi faire rêver toutes les autres facultés.</h4>
				<p class="justified">
				Entrez les disponibilités des professeurs,
				sélectionnez l'offre de cours pour l'année et laissez le système vous proposer un horaire. Remaniez l'horaire à votre guise et
				suivez les recommandations du système afin d'éviter les conflits.
				</p>
			</div>
			<div class="span6 well fixed_height">
				<h2>Zone Membre</h2>
				<p>Zone membre pour organiser, visualer et accepter vos horaires.</p>
				<form>
					<input type="text" placeholder="IDUL">
					<input type="password" placeholder="Mot de passe"><br/>
					<button type="submit" class="btn">Se connecter</button>
				</form>
			</div>
		</div>
	</div>
	<footer class="footer">
		<div class="container">
			<p>Projet développé dans le cadre du cours GLO-4003 -
				Architecture Logicielle, Université Laval</p>
			<p>Développé par Philippe Bourdages, William Fortin, Bruno
				Gagnon-Adam, Jonathan Rochette, Vincent Séguin, Olivier Sylvain</p>
		</div>
	</footer>
</body>

</html>
