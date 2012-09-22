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
<header>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="#">ScheduleManager</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="#">Accueil</a></li>
					<li><a href="#">À propos</a></li>
					<li><a href="https://github.com/glo-ulaval/Architecture">Code
							Source</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
</header>
<body>
	<div class="container">
		<div class="hero-unit">
			<h1>ScheduleManager</h1>
			<p>Bienvenue sur l'application ScheduleManager développée dans le
				cadre du cours "GLO-4003 - Architecture Logicielle".</p>
		</div>
		<div class="row-fluid">
			<div class="span4 well fixed_height">
				<h2>Automatisé</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.
				</p>
			</div>
			<div class="span4 well fixed_height">
				<h2>Simple & Efficace</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.
				</p>
			</div>
			<div class="span4 well fixed_height">
				<h2>Zone Membre</h2>
				<p>Zone membre pour organiser, visualer et accepter vos horaires.</p>
				<form>
					<input type="text" placeholder="Email">
					<input type="password" placeholder="Password">
					<button type="submit" class="btn">Se connecter</button>
				</form>
			</div>
		</div>
	</div>

</body>

</html>
