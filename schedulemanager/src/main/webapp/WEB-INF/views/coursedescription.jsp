<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true"%>
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
				<div class="hero-unit">
					<h1>Cours ${acronym}</h1>
					<br />
					<h3>- ${title}</h3>
				</div>
				<h3 style="display: inline;">Cycle : &nbsp;</h3>
				<a class="btn btn-large btn-primary disabled">${cycle}</a>
				<h3>Description :</h3>
				<div class="span12">${description}</div>
				<h3>Préalables :</h3>
				<c:url var = "courseurl" value="/course/"></c:url>
				<c:set var="count" value="0" scope="page" />
				<c:set var="courseCount" value="0" scope="page" />
				<c:forEach var="prerequisite" items="${prerequisites}">
          <div class="span12"><c:set var="acronyms" value="${prerequisite.acronyms}"/>
				  <c:set var="count" value="${count + 1}" scope="page"/>
					<h4 class = "inline">(</h4><c:forEach var="course" items="${acronyms}">
					 <c:set var="courseCount" value="${courseCount + 1}" scope="page"/>
					 <h4 class = "inline"><a class = "muted" href="${courseurl}${course}" target="_blank">${course}</a></h4><c:if test="${courseCount < fn:length(acronyms)}"><h4 class = "inline text-info">&nbsp;OU</h4></c:if>
					</c:forEach><h4 class = "inline">)</h4><c:if test="${prerequisite.isConcomitant}"><span class="text-warning"><b>&nbsp; - Concomitant</b></span></c:if><c:if test="${count != fn:length(prerequisites)}"><h4 class = "text-success">&nbsp;ET</h4></c:if></div>
				</c:forEach>
				<h3>Temps consacré :</h3>
				<div class="span8 controls">
					<div id="hour_group">
						<label class="control-label">Heures en classe :</label> <input
							type="text" disabled class="input-small" value="${timeInClass}">
					</div>
					<div id="hour_group">
						<label class="control-label" for="hours_labo">Heures en
							labo/travail dirigé : </label> <input type="text" disabled
							class="input-small" value="${timeInLab}">
					</div>
					<div id="hour_group">
						<label class="control-label" for="hours_home">Heures de
							travail personnel : </label> <input type="text" disabled
							class="input-small" value="${timeAtHome}">
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
