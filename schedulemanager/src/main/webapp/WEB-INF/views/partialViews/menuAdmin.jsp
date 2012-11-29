<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="centered">
	<button class="btn btn-large btn-info btnCrawl" type="button">Mettre
		les cours à jours selon Capsule >>></button>
	<img class="loading" src="<c:url value="/resources/img/ajax-loader.gif" />" alt="" />
	<span class="label label-success" id="spanSuccess"> <i class="icon icon-ok"></i> Les cours sont à jour.</span>
</div><br/>
<div class="row-fluid">
	<div class="span6">
		<h2>Créer un nouvel usager</h2>
		<p>Permet à l'administrateur de créer un compte pour un usager et
			lui définir les permissions.</p>
	</div>
	<div class="span6">
		<h2>Changer les permissions d'un usager</h2>
		<p>Permet à l'administrateur de modifier les permissions d'un
			usager existant.</p>
	</div>
</div>
<div class="row-fluid">
	<div class="span6">
		<a class="btn btn-large" href="createuser">Sélectionner &raquo;</a>
	</div>
	<div class="span6">
		<a class="btn btn-large" href="edituser">Sélectionner &raquo;</a>
	</div>
</div>
<br />
<br />