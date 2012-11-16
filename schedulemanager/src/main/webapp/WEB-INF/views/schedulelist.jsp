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
<script type="text/javascript"	src="<c:url value="/resources/js/bootstrap.js" />" /></script>
<script type="text/javascript"	src="<c:url value="/resources/js/schedule.js" />" /></script>
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
			<c:url var="editsection" value="/schedule/editsection/${id}/${year}/${semester}"></c:url>
			<c:url var="deletesection" value="/schedule/deletesection/${id}/${year}/${semester}"></c:url>
			<table class="table">
			
			<c:forEach var="day" items="${schedule.days}" varStatus="i">
				<tr class="well">
					<td colspan="4"><h4>${schedule.jours[i.count - 1]}</h4></td>
				</tr>
				<c:set var="counter" value="0" />
				
				<c:forEach var="section" items="${schedule[day]}">
					<c:choose>
						<c:when test="${section.isLab}">
							<c:set value="yellow" var="color"></c:set>
						</c:when>
						<c:when test="${fn:length(section.conflicts) > 0}">
							<c:set value="red" var="color"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="" var="color"></c:set>
						</c:otherwise>
					</c:choose>
					<tr class="${color}">
						<td><b>${section.timeSlotStart} - ${section.timeSlotEnd}</b> || ${section.acronym}
							(${section.nrc} - ${section.group})
						</td>
						<sec:authorize access="hasAnyRole('ROLE_Responsable', 'ROLE_Directeur')">
						<td class="centered">
							<a class="btn btn-info" href="${editsection}/${section.nrc}">
							<i class="icon-edit icon-white"></i></a>
						</td>
						<td class="centered">
							<a class="btn btn-danger" href="${deletesection}/${section.nrc}">
							<i class="icon-trash icon-white"></i></a>
						</td>
						</sec:authorize>
						<c:if test="${fn:length(section.conflicts) > 0}">
							<c:url var="conflicturl" value="/conflict/monday/${counter}"></c:url>
						<td>
							<a href="#" title="Détails du/des conflit(s)" class="btn show-conflicts">
								<i class="icon-chevron-down"></i></a>
						</td>
						</c:if>
					</tr> 
					<c:if test="${fn:length(section.conflicts) > 0}">
					<tr class="conflicts"> 
						<td colspan="4">
							<ul>
								<c:forEach var="conflict" items="${section.conflicts}">
								<li class="conflit">
									${conflict.firstNrc} - ${conflict.description} 
									<a href="#" class="btn show-details-conflict"><i class="icon-chevron-down"></i></a>
									<div class="details-conflict">
										<c:choose>
											<c:when test="${not empty conflict.secondNrc}">
												<div>
													Entre la section <b>${conflict.firstNrc}</b> et la section <b>${conflict.secondNrc}</b>,
													dans les plages horaires du <b>${conflict.dayOfWeek}</b> allant
													de <b><span class="blue">${conflict.firstStartTime}</span></b>
													à <b><span class="blue">${conflict.firstEndTime}</span></b> et
													de <b><span class="blue">${conflict.secondStartTime}</span></b>
													à <b><span class="blue">${conflict.secondEndTime}</span></b>.
												</div>
											</c:when>
											<c:otherwise>
												<div>
													Dans la section <b>${conflict.firstNrc}</b>, dans la plage
													horaire du <b>${conflict.dayOfWeek}</b> allant de <b><span
														class="blue">${conflict.firstStartTime}</span></b> à <b><span
														class="blue">${conflict.firstEndTime}</span></b> .
												</div>
											</c:otherwise>
										</c:choose>
										
										<br>
										
										<c:if test="${not empty conflict.teacher}">
											<h4>Professeur impliqué :</h4>
											<div>${conflict.teacher}</div>
										</c:if>
									</div>
								</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
					</c:if>
					
					<c:set var="counter" value="${counter+1}" />
				</c:forEach>
				</c:forEach>
				
			</table>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
