<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/createuser.css" />"
	rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app.js" />" /></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.js" />" /></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/edituser.js" />" /></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
	
</head>
<body>
	<c:import url="header.jsp" />
		<div class="container">
			<div class="row-fluid">
				<div class="hero-unit">
					<h1>Modification des rôles d'un usager</h1>
				</div>
				<c:choose>
					<c:when test="${ empty error }"></c:when>
					<c:when test="${ error == 'success'}">
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Succès!</strong> La requête s'est effectuée avec succès.
						</div>
					</c:when>
				</c:choose>
				<form action="/schedulemanager/changeroles/" method=POST scope="request" commandName="user">
					<div>
						<select id='userToChange' name='userToChange'>
							<option value="null">Professeurs:</option>
							<c:forEach var="user" items="${users}">
								<option value="${user.idul}"> ${user.name} </option>
							</c:forEach>
						</select>
					</div>
					<div id="rolesCheckBoxes"></div>
				</form>
			</div>
		</div>
	<c:import url="footer.jsp" />
</body>
</html>