<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<c:import url="header.jsp" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h1>[${schedule.semester} ${schedule.year}] ${schedule.id}</h1>
			</div>
		</div>
		<br/>
		<div class="schedule_details">
		<c:url var="editsection" value="/schedule/editsection/${schedule.id}/${schedule.year}/${schedule.semester}"></c:url>
		<c:url var="deletesection" value="/schedule/deletesection/${schedule.id}/${schedule.year}/${schedule.semester}"></c:url>
			<table class="table table-striped">
				<tr><td><h4>Lundi</h4></td></tr>
				<c:forEach var="mondaysection" items="${sections.monday}">
					<tr>
						<td class="span12"><b>${mondaysection.timeSlotStart} - ${mondaysection.timeSlotEnd}</b> (${mondaysection.nrc}) ${mondaysection.acronym}</td>
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${mondaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${mondaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
					</tr>
				</c:forEach>
				<tr><td><h4>Mardi</h4></td></tr>
				<c:forEach var="tuesdaysection" items="${sections.tuesday}">
					<tr>
						<td class="span12"><b>${tuesdaysection.timeSlotStart} - ${tuesdaysection.timeSlotEnd}</b> (${tuesdaysection.nrc}) ${tuesdaysection.acronym}</td>
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${tuesdaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${tuesdaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
					</tr>
				</c:forEach>
				<tr><td><h4>Mercredi</h4></td></tr>
				<c:forEach var="wednesdaysection" items="${sections.wednesday}">
					<tr>
						<td class="span12"><b>${wednesdaysection.timeSlotStart} - ${wednesdaysection.timeSlotEnd}</b> (${wednesdaysection.nrc}) ${wednesdaysection.acronym}</td>
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${wednesdaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${wednesdaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
					</tr>
				</c:forEach>
				<tr><td><h4>Jeudi</h4></td></tr>
				<c:forEach var="thursdaysection" items="${sections.thursday}">
					<tr>
						<td class="span12"><b>${thursdaysection.timeSlotStart} - ${thursdaysection.timeSlotEnd}</b> (${thursdaysection.nrc}) ${thursdaysection.acronym}</td>
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${thursdaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${thursdaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
					</tr>
				</c:forEach>
				<tr><td><h4>Vendredi</h4></td></tr>
				<c:forEach var="fridaysection" items="${sections.friday}">
					<tr>
						<td class="span12"><b>${fridaysection.timeSlotStart} - ${fridaysection.timeSlotEnd}</b> (${fridaysection.nrc}) ${fridaysection.acronym}</td>
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${fridaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${fridaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
