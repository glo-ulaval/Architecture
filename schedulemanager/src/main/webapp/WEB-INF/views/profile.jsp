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
	src="<c:url value="/resources/js/createuser.js" />" /></script>
</head>
<body>
	<c:import url="header.jsp" />
	<c:url value="/profile/${user.idul}" var="url" />
	<form action="${url}" method=POST scope="request" commandName="user">
		<div class="container">
			<div class="row-fluid">
				<div class="hero-unit">
					<h1>Édition du profile</h1>
					<h3>&nbsp;&nbsp;&nbsp; - ${user.name}</h3>
				</div>
        <table class="table table-striped">
          <tr>
            <td class="centered"><b>IDUL : &nbsp;</b></td>
            <td><input class="textRight" type="text" name="idul"
              scope="request" value="${user.idul}" disabled></br></td>
          </tr>
          <tr>
            <td class="centered"><b>Nom : &nbsp;</b></td>
            <td><input class="textRight" type="text" name="name"
              scope="request" value="${user.name}"></br></td>
          </tr>
          <tr>
            <td class="centered"><b>Mot de passe : &nbsp;</b></td>
            <td><input class="textRight" type="password" name="password"
              scope="request" value="${user.password}"></br></td>
          </tr>
        </table>
			</div>
    <input type="submit" class="btn btn-success pull-right"
    value="Sauvegarder">
		</div>
	<c:import url="footer.jsp" />
</body>
</html>
