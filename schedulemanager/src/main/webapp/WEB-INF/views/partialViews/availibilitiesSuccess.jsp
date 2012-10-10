<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/menu" var="menu"/>
<div id="successLabel" class="alert alert-success" style="display: none">
	<button type="button" class="close" data-dismiss="alert">×</button>
	<p>Vos modifications ont été enregistrés avec succès</p>
	<p><a href="${menu}">Cliquez ici</a> pour retourner au menu.</p>
</div>