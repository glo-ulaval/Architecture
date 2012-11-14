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
			<div class="hero-unit">
				<h1>Détails des conflits</h1>
			</div>
			<c:if test="${not empty conflicts}">
				<c:set var="count" value="1" scope="page" />
				<c:forEach var="conflict" items="${conflicts}">
					<div class="span11">
						<h2 class="centered red">Conflit ${count} :</h2>
						<c:choose>
							<c:when test="${not empty conflict.secondNrc}">
								<div class="centered">
									Entre la section <b>${conflict.firstNrc}</b> et la section <b>${conflict.secondNrc}</b>,
									dans les plages horaires du <b>${conflict.dayOfWeek}</b> allant
									de <b><span class="blue">${conflict.firstStartTime}</span></b>
									à <b><span class="blue">${conflict.firstEndTime}</span></b> et
									de <b><span class="blue">${conflict.secondStartTime}</span></b>
									à <b><span class="blue">${conflict.secondEndTime}</span></b>.
								</div>
							</c:when>
							<c:otherwise>
								<div class="centered">
									Dans la section <b>${conflict.firstNrc}</b>, dans la plage
									horaire du <b>${conflict.dayOfWeek}</b> allant de <b><span
										class="blue">${conflict.firstStartTime}</span></b> à <b><span
										class="blue">${conflict.firstEndTime}</span></b> .
								</div>
							</c:otherwise>
						</c:choose>
						<br>
						<h4 class="centered">Description :</h4>
						<div class="centered">${conflict.description}</div>
						<br>
						<c:if test="${not empty conflict.teacher}">
							<h4 class="centered">Professeur impliqué :</h4>
							<div class="centered">${conflict.teacher}</div>
						</c:if>
						<br>
					</div>
					<c:set var="count" value="${count + 1}" scope="page" />
				</c:forEach>
			</c:if>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
