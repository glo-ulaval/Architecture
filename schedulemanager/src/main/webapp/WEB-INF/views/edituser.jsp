<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
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
	
</head>
<body>
	<c:import url="header.jsp" />
		<div class="container">
			<div class="row-fluid">
				<div class="hero-unit">
					<h1>Modification des rôles d'un usager</h1>
				</div>
				
				<form action="/schedulemanager/edituser" method=POST scope="request" commandName="user">
					<div>
						<select id='userToChange' name='userToChange'>
							<c:forEach var="user" items="${users}">
								<option value="${user.idul}" data-role='[
								<c:forEach var="role" items="${user.roles}" varStatus="loop">"${role}" 
									<c:if test="${!loop.last}">
										,
									</c:if>
								</c:forEach>]'>${user.name}
								</option>
							</c:forEach>
						</select>
					</div>
					<div id="display"></div>
					<input id="roleAdmin" type="checkbox" name="roleAdmin" value="ROLE_Administrateur">Administrateur<br>
					<input id="roleDirecteur" type="checkbox" name="roleDirecteur" value="ROLE_Directeur">Directeur<br>
					<input id="roleResponsable" type="checkbox" name="roleResponsable" value="ROLE_Responsable">Responsable<br>
					<input id="roleEnseignant" type="checkbox" name="roleEnseignant" value="ROLE_Enseignant">Enseignant<br>
					<input id="roleUsager" type="checkbox" name="roleUsager" value="ROLE_Usager">Usager
					<input type="submit" class="btn btn-success pull-right" value="Modifier"> 
				</form>
			</div>
		</div>
	<c:import url="footer.jsp" />
</body>
</html>