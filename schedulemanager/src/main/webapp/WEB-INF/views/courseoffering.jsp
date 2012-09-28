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
					<a class="brand" href="#"><img src="assets/img/logo_mini.png"
						alt="" />ScheduleManager</a>
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
		<select>
			<option value="2009">2009</option>
			<option value="2010">2010</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>
		</select>
		<div class=row-fluid">
			<div class="span6">
				<h1>Cours disponibles</h1>
				<c:if test="${not empty list}">
					<table class="table table-striped">
							<thead>
								<tr>
									<th>Titre</th>
									<th>Credits</th>
									<th>Cycle</th>
									<th>Actions</th>
								</tr>
							</thead>
						<c:forEach var="course" items="${list}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<td>${course.credits}</td>
								<td>${course.cycle}</td>
								<td class="centered"><i class="icon-plus"></i></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<div class="span6">
			<h1>Offre de cours</h1>
				<c:if test="${not empty list}">
					<table class="table table-striped">
							<thead>
								<tr>
									<th>Titre</th>
									<th>Credits</th>
									<th>Cycle</th>
									<th class="centered">Actions</th>
								</tr>
							</thead>
						<c:forEach var="course" items="${list}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<td>${course.credits}</td>
								<td>${course.cycle}</td>
								<td class="centered"><i class="icon-minus"></i></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
