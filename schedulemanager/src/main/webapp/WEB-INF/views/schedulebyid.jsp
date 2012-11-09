<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
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
		<b>* Notez que les sections indiquées en <span class = "yellow">jaune</span> sont des sections de laboratoire.</b>
    <br/>
    <br/>
		<div class="btn-group">
		  <a href="#" type="button" class="btn active">Liste</a>
		  <a href="/schedulemanager/calendar/${schedule.id}" type="button" class="btn">Calendrier</a>
		</div>
		<div class="schedule_details">
		<c:url var="editsection" value="/schedule/editsection/${schedule.id}/${schedule.year}/${schedule.semester}"></c:url>
		<c:url var="deletesection" value="/schedule/deletesection/${schedule.id}/${schedule.year}/${schedule.semester}"></c:url>
			<table class="table">
				<tr class="well"><td colspan="3"><h4>Lundi</h4></td></tr>
				<c:forEach var="mondaysection" items="${sections.monday}">
				  <c:if test="${mondaysection.isLab}">
				    <c:set value="yellow" var="lab"></c:set>
				  </c:if>
					<tr class="${lab}">
						<td class="span12"><b>${mondaysection.timeSlotStart} - ${mondaysection.timeSlotEnd}</b> || ${mondaysection.acronym} (${mondaysection.nrc} - ${mondaysection.group})</td>
						<sec:authorize access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${mondaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${mondaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
						</sec:authorize>
					</tr>
				</c:forEach>
				<tr class="well"><td colspan="3"><h4>Mardi</h4></td></tr>
				<c:forEach var="tuesdaysection" items="${sections.tuesday}">
          <c:if test="${tuesdaysection.isLab}">
            <c:set value="yellow" var="lab"></c:set>
          </c:if>
          <tr class="${lab}">
						<td class="span12"><b>${tuesdaysection.timeSlotStart} - ${tuesdaysection.timeSlotEnd}</b> || ${tuesdaysection.acronym} (${tuesdaysection.nrc} - ${tuesdaysection.group})</td>
						<sec:authorize access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${tuesdaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${tuesdaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
						</sec:authorize>
					</tr>
				</c:forEach>
				<tr class="well"><td colspan="3"><h4>Mercredi</h4></td></tr>
				<c:forEach var="wednesdaysection" items="${sections.wednesday}">
          <c:if test="${wednesdaysection.isLab}">
            <c:set value="yellow" var="lab"></c:set>
          </c:if>
          <tr class="${lab}">
						<td class="span12"><b>${wednesdaysection.timeSlotStart} - ${wednesdaysection.timeSlotEnd}</b> || ${wednesdaysection.acronym} (${wednesdaysection.nrc} - ${wednesdaysection.group})</td>
						<sec:authorize access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${wednesdaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${wednesdaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
						</sec:authorize>
					</tr>
				</c:forEach>
				<tr class="well"><td colspan="3"><h4>Jeudi</h4></td></tr>
				<c:forEach var="thursdaysection" items="${sections.thursday}">
          <c:if test="${thursdaysection.isLab}">
            <c:set value="yellow" var="lab"></c:set>
          </c:if>
          <tr class="${lab}">
						<td class="span12"><b>${thursdaysection.timeSlotStart} - ${thursdaysection.timeSlotEnd}</b> || ${thursdaysection.acronym} (${thursdaysection.nrc} - ${thursdaysection.group})</td>
						<sec:authorize access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${thursdaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${thursdaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
						</sec:authorize>
					</tr>
				</c:forEach>
				<tr class="well"><td colspan="3"><h4>Vendredi</h4></td></tr>
				<c:forEach var="fridaysection" items="${sections.friday}">
          <c:if test="${fridaysection.isLab}">
            <c:set value="yellow" var="lab"></c:set>
          </c:if>
          <tr class="${lab}">
						<td class="span12"><b>${fridaysection.timeSlotStart} - ${fridaysection.timeSlotEnd}</b> || ${fridaysection.acronym} (${fridaysection.nrc} - ${fridaysection.group})</td>
						<sec:authorize access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
						<td class="centered">
							<a class="btn btn-warning" href="${editsection}/${fridaysection.nrc}"><i class="icon-edit"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${fridaysection.nrc}"><i class="icon-trash icon-white"></i></a>
						</td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
