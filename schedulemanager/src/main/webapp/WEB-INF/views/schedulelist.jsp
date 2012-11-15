<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="true"%>
<c:set var="id" value="${schedule.scheduleInfo.id}"/>
<c:set var="semester" value="${schedule.scheduleInfo.semester}"/>
<c:set var="year" value="${schedule.scheduleInfo.year}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app.js" />" /></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.js" />" /></script>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h1>[${semester} ${year}] ${id}</h1>
			</div>
		</div>
		<br /> <b>* Notez que les sections indiquées en <span
			class="yellow">jaune</span> sont des sections de laboratoire.
		</b> <br>
		<br> <b>* Les plages horaires indiquées en <span class="red">rouge</span>
			sont en conflits, soit avec une autre plage horaire ou avec une
			disponibilité d'un professeur.
		</b> <br /> <br />
		<div class="btn-group">
			<a href="#" type="button" class="btn active">Liste</a> <a
				href="calendar" type="button" class="btn">Calendrier</a>
		</div>
		<div class="schedule_details">
			<c:url var="editsection"
				value="/schedule/editsection/${id}/${year}/${semester}"></c:url>
			<c:url var="deletesection"
				value="/schedule/deletesection/${id}/${year}/${semester}"></c:url>
			<table class="table">
				<tr class="well">
					<td colspan="4"><h4>Lundi</h4></td>
				</tr>
				<c:set var="counter" value="0" />
				<c:forEach var="mondaysection" items="${schedule.monday}">
					<c:choose>
						<c:when test="${mondaysection.isLab}">
							<c:set value="yellow" var="color"></c:set>
						</c:when>
						<c:when test="${fn:length(mondaysection.conflicts) > 0}">
							<c:set value="red" var="color"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="" var="color"></c:set>
						</c:otherwise>
					</c:choose>
					<tr class="${color}">
						<td><b>${mondaysection.timeSlotStart} -
								${mondaysection.timeSlotEnd}</b> || ${mondaysection.acronym}
							(${mondaysection.nrc} - ${mondaysection.group})</td>
						<sec:authorize
							access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
							<td class="centered"><a class="btn btn-info"
								href="${editsection}/${mondaysection.nrc}"><i
									class="icon-edit icon-white"></i></a></td>
							<td class="centered">
						<a class="btn btn-danger"
								href="${deletesection}/${mondaysection.nrc}"><i
								class="icon-trash icon-white"></i></a>
							</td></sec:authorize>
						<c:if test="${fn:length(mondaysection.conflicts) > 0}">
							<c:url var="conflicturl" value="/conflict/monday/${counter}"></c:url>
							<td><a class="btn btn-warning" href="${conflicturl}"
								title="Détails du/des conflit(s)"><i
									class="icon-warning-sign"></i></a></td>
						</c:if>
					</tr>
					<c:set var="counter" value="${counter+1}" />
				</c:forEach>
				<tr class="well">
					<td colspan="4"><h4>Mardi</h4></td>
				</tr>
				<c:forEach var="tuesdaysection" items="${schedule.tuesday}">
					<c:choose>
						<c:when test="${tuesdaysection.isLab}">
							<c:set value="yellow" var="color"></c:set>
						</c:when>
						<c:when test="${fn:length(tuesdaysection.conflicts) > 0}">
							<c:set value="red" var="color"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="" var="color"></c:set>
						</c:otherwise>
					</c:choose>
					<tr class="${color}">
						<td class="span12"><b>${tuesdaysection.timeSlotStart} -
								${tuesdaysection.timeSlotEnd}</b> || ${tuesdaysection.acronym}
							(${tuesdaysection.nrc} - ${tuesdaysection.group})</td>
						<sec:authorize
							access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
							<td class="centered"><a class="btn btn-info"
								href="${editsection}/${tuesdaysection.nrc}"><i
									class="icon-edit icon-white"></i></a></td>
							<td class="centered"><a class="btn btn-danger"
								href="${deletesection}/${tuesdaysection.nrc}"><i
									class="icon-trash icon-white"></i></a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
				<tr class="well">
					<td colspan="4"><h4>Mercredi</h4></td>
				</tr>
				<c:forEach var="wednesdaysection" items="${schedule.wednesday}">
					<c:choose>
						<c:when test="${wednesdaysection.isLab}">
							<c:set value="yellow" var="color"></c:set>
						</c:when>
						<c:when test="${fn:length(wednesdaysection.conflicts) > 0}">
							<c:set value="red" var="color"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="" var="color"></c:set>
						</c:otherwise>
					</c:choose>
					<tr class="${color}">
						<td class="span12"><b>${wednesdaysection.timeSlotStart} -
								${wednesdaysection.timeSlotEnd}</b> || ${wednesdaysection.acronym}
							(${wednesdaysection.nrc} - ${wednesdaysection.group})</td>
						<sec:authorize
							access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
							<td class="centered"><a class="btn btn-info"
								href="${editsection}/${wednesdaysection.nrc}"><i
									class="icon-edit icon-white"></i></a></td>
							<td class="centered"><a class="btn btn-danger"
								href="${deletesection}/${wednesdaysection.nrc}"><i
									class="icon-trash icon-white"></i></a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
				<tr class="well">
					<td colspan="4"><h4>Jeudi</h4></td>
				</tr>
				<c:forEach var="thursdaysection" items="${schedule.thursday}">
					<c:choose>
						<c:when test="${thursdaysection.isLab}">
							<c:set value="yellow" var="color"></c:set>
						</c:when>
						<c:when test="${fn:length(thursdaysection.conflicts) > 0}">
							<c:set value="red" var="color"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="" var="color"></c:set>
						</c:otherwise>
					</c:choose>
					<tr class="${color}">
						<td class="span12"><b>${thursdaysection.timeSlotStart} -
								${thursdaysection.timeSlotEnd}</b> || ${thursdaysection.acronym}
							(${thursdaysection.nrc} - ${thursdaysection.group})</td>
						<sec:authorize
							access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
							<td class="centered"><a class="btn btn-info"
								href="${editsection}/${thursdaysection.nrc}"><i
									class="icon-edit icon-white"></i></a></td>
							<td class="centered"><a class="btn btn-danger"
								href="${deletesection}/${thursdaysection.nrc}"><i
									class="icon-trash icon-white"></i></a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
				<tr class="well">
					<td colspan="4"><h4>Vendredi</h4></td>
				</tr>
				<c:forEach var="fridaysection" items="${schedule.friday}">
					<c:choose>
						<c:when test="${fridaysection.isLab}">
							<c:set value="yellow" var="color"></c:set>
						</c:when>
						<c:when test="${fn:length(fridaysection.conflicts) > 0}">
							<c:set value="red" var="color"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="" var="color"></c:set>
						</c:otherwise>
					</c:choose>
					<tr class="${color}">
						<td class="span12"><b>${fridaysection.timeSlotStart} -
								${fridaysection.timeSlotEnd}</b> || ${fridaysection.acronym}
							(${fridaysection.nrc} - ${fridaysection.group})</td>
						<sec:authorize
							access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
							<td class="centered"><a class="btn btn-info"
								href="${editsection}/${fridaysection.nrc}"><i
									class="icon-edit icon-white"></i></a></td>
							<td class="centered"><a class="btn btn-danger"
								href="${deletesection}/${fridaysection.nrc}"><i
									class="icon-trash icon-white"></i></a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
