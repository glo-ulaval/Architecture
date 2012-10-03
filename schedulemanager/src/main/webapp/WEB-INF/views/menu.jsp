<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
</head>
<body>
  <c:import url = "header.jsp"/>
  <div class="container">
  	<div class="alert alert-info">
  	 	<button type="button" class="close" data-dismiss="alert">×</button>
		<p>Vous êtes connecté en tant que : ${username}</p>
	</div>
    <div class="hero-unit">
      <h1>Menu</h1>
      <p>Veuillez sélectionner une option :</p>
    </div>
    <div class="row-fluid">
        <div class="span4">
          <h2>Offre de cours</h2>
          <p>Permet à l'utilisateur d'éditer une offre de cours ainsi que de visualiser les existantes.</p>
        </div>
        <div class="span4">
          <h2>Ajouter un horaire</h2>
          <p>Permet à l'utilisateur de créer un horaire pour l'année en cours, et de créer les sections de cours en fonction de l'offre actuelle.</p>
       </div>
        <div class="span4">
          <h2>Visualiser un horaire</h2>
          <p>Permet à l'utlisateur de visualiser les horaires sauvegardés dans le système, pour l'année actuelle et les années antérieures.</p>
        </div>
      </div>
      <div class = "row-fluid">
        <div class="span4">
          <p><a class="btn btn-large" href="offering">Sélectionner &raquo;</a></p>
        </div>
        <div class="span4">
          <p><a class="btn btn-large" href="addschedule">Sélectionner &raquo;</a></p>
       </div>
        <div class="span4">
          <p><a class="btn btn-large" href="schedule">Sélectionner &raquo;</a></p>
        </div>
      </div>
  </div>
  <c:import url = "footer.jsp"/>
</body>

</html>
