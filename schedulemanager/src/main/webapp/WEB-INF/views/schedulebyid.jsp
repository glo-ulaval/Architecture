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
			<table class="table table-striped">
				<c:forEach var="section" items="${sections}">
					<tr>
						<td class="span12">${section.nrc}</td>
						<td class="centered">
							<a class="btn btn-success" href=""><i class="icon-search"></i></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
