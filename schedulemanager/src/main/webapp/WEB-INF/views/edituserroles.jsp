<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<form action="/schedulemanager/changeRoles" method=POST score="request" commandName="user">
	<input id="userIdul" type="hidden" name="userIdul" value="${user}">
	<input id="roleAdmin" type="checkbox" name="roleAdmin" <c:if test='${ fn:contains(roles, "Administrateur") }'>checked="checked"</c:if> value="ROLE_Administrateur"> &nbsp; Administrateur<br>
	<input id="roleDirecteur" type="checkbox" name="roleDirecteur" <c:if test='${ fn:contains(roles, "Directeur") }'>checked="checked"</c:if> value="ROLE_Directeur"> &nbsp; Directeur<br>
	<input id="roleResponsable" type="checkbox" name="roleResponsable" <c:if test='${ fn:contains(roles, "Responsable") }'>checked="checked"</c:if> value="ROLE_Responsable"> &nbsp; Responsable<br>
	<input id="roleEnseignant" type="checkbox" name="roleEnseignant" <c:if test='${ fn:contains(roles, "Enseignant") }'>checked="checked"</c:if> value="ROLE_Enseignant"> &nbsp; Enseignant<br>
	<input id="roleUsager" type="checkbox" name="roleUsager" <c:if test='${ fn:contains(roles, "Usager") }'>checked="checked"</c:if> value="ROLE_Usager"> &nbsp; Usager
	<input type="submit" class="btn btn-success pull-right" value="Modifier"> 
</form>
