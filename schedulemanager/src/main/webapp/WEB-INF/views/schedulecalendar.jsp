<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>Schedule Manager</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
<link href='<c:url value="/resources/css/calendar.css" />' rel="stylesheet"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />

<script type="text/javascript">
	var schedule = ${schedule};
</script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/app.js" />" /></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" /></script>
<script type="text/javascript" src="<c:url value="/resources/js/calendar.js" />" /></script>
</head>
<body>
<c:import url="header.jsp" />
<h1>Horaire</h1>
<div class="btn-group">
  <a href="list" type="button" class="btn">Liste</a>
  <a href="#" type="button" class="btn active">Calendrier</a>
</div>
<table class="table table-striped">
	<tr>
		<td class="day"></td>
		<td class="hours-head">
			<div class="hour-head">8:30</div>
			<div class="hour-head">9:00</div>
			<div class="hour-head">9:30</div>
			<div class="hour-head">10:00</div>
			<div class="hour-head">10:30</div>
			<div class="hour-head">11:00</div>
			<div class="hour-head">11:30</div>
			<div class="hour-head">12:00</div>
			<div class="hour-head">12:30</div>
			<div class="hour-head">13:00</div>
			<div class="hour-head">13:30</div>
			<div class="hour-head">14:00</div>
			<div class="hour-head">14:30</div>
			<div class="hour-head">15:00</div>
			<div class="hour-head">15:30</div>
			<div class="hour-head">16:00</div>
			<div class="hour-head">16:30</div>
			<div class="hour-head">17:00</div>
			<div class="hour-head">17:30</div>
			<div class="hour-head">18:00</div>
			<div class="hour-head">18:30</div>
			<div class="hour-head">19:00</div>
			<div class="hour-head">19:30</div>
			<div class="hour-head">20:00</div>
			<div class="hour-head">20:30</div>
			<div class="hour-head">21:00</div>
			<div class="hour-head">21:30</div>
		</td>
	</tr>
	<tr>
		<td class="day">Lundi</td>
		<td class="hours-column">
			<div id="mon85" class="hour"></div>
			<div id="mon90" class="hour"></div>
			<div id="mon95" class="hour"></div>
			<div id="mon100" class="hour"></div>
			<div id="mon105" class="hour"></div>
			<div id="mon110" class="hour"></div>
			<div id="mon115" class="hour"></div>
			<div id="mon120" class="hour"></div>
			<div id="mon125" class="hour"></div>
			<div id="mon130" class="hour"></div>
			<div id="mon135" class="hour"></div>
			<div id="mon140" class="hour"></div>
			<div id="mon145" class="hour"></div>
			<div id="mon150" class="hour"></div>
			<div id="mon155" class="hour"></div>
			<div id="mon160" class="hour"></div>
			<div id="mon165" class="hour"></div>
			<div id="mon170" class="hour"></div>
			<div id="mon175" class="hour"></div>
			<div id="mon180" class="hour"></div>
			<div id="mon185" class="hour"></div>
			<div id="mon190" class="hour"></div>
			<div id="mon195" class="hour"></div>
			<div id="mon200" class="hour"></div>
			<div id="mon205" class="hour"></div>
			<div id="mon210" class="hour"></div>
			<div id="mon215" class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Mardi</td>
		<td class="hours-column">
			<div id="tue85" class="hour"></div>
			<div id="tue90" class="hour"></div>
			<div id="tue95" class="hour"></div>
			<div id="tue100" class="hour"></div>
			<div id="tue105" class="hour"></div>
			<div id="tue110" class="hour"></div>
			<div id="tue115" class="hour"></div>
			<div id="tue120" class="hour"></div>
			<div id="tue125" class="hour"></div>
			<div id="tue130" class="hour"></div>
			<div id="tue135" class="hour"></div>
			<div id="tue140" class="hour"></div>
			<div id="tue145" class="hour"></div>
			<div id="tue150" class="hour"></div>
			<div id="tue155" class="hour"></div>
			<div id="tue160" class="hour"></div>
			<div id="tue165" class="hour"></div>
			<div id="tue170" class="hour"></div>
			<div id="tue175" class="hour"></div>
			<div id="tue180" class="hour"></div>
			<div id="tue185" class="hour"></div>
			<div id="tue190" class="hour"></div>
			<div id="tue195" class="hour"></div>
			<div id="tue200" class="hour"></div>
			<div id="tue205" class="hour"></div>
			<div id="tue210" class="hour"></div>
			<div id="tue215" class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Mercredi</td>
		<td class="hours-column">
			<div id="wed85" class="hour"></div>
			<div id="wed90" class="hour"></div>
			<div id="wed95" class="hour"></div>
			<div id="wed100" class="hour"></div>
			<div id="wed105" class="hour"></div>
			<div id="wed110" class="hour"></div>
			<div id="wed115" class="hour"></div>
			<div id="wed120" class="hour"></div>
			<div id="wed125" class="hour"></div>
			<div id="wed130" class="hour"></div>
			<div id="wed135" class="hour"></div>
			<div id="wed140" class="hour"></div>
			<div id="wed145" class="hour"></div>
			<div id="wed150" class="hour"></div>
			<div id="wed155" class="hour"></div>
			<div id="wed160" class="hour"></div>
			<div id="wed165" class="hour"></div>
			<div id="wed170" class="hour"></div>
			<div id="wed175" class="hour"></div>
			<div id="wed180" class="hour"></div>
			<div id="wed185" class="hour"></div>
			<div id="wed190" class="hour"></div>
			<div id="wed195" class="hour"></div>
			<div id="wed200" class="hour"></div>
			<div id="wed205" class="hour"></div>
			<div id="wed210" class="hour"></div>
			<div id="wed215" class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Jeudi</td>
		<td class="hours-column">
			<div id="thu85" class="hour"></div>
			<div id="thu90" class="hour"></div>
			<div id="thu95" class="hour"></div>
			<div id="thu100" class="hour"></div>
			<div id="thu105" class="hour"></div>
			<div id="thu110" class="hour"></div>
			<div id="thu115" class="hour"></div>
			<div id="thu120" class="hour"></div>
			<div id="thu125" class="hour"></div>
			<div id="thu130" class="hour"></div>
			<div id="thu135" class="hour"></div>
			<div id="thu140" class="hour"></div>
			<div id="thu145" class="hour"></div>
			<div id="thu150" class="hour"></div>
			<div id="thu155" class="hour"></div>
			<div id="thu160" class="hour"></div>
			<div id="thu165" class="hour"></div>
			<div id="thu170" class="hour"></div>
			<div id="thu175" class="hour"></div>
			<div id="thu180" class="hour"></div>
			<div id="thu185" class="hour"></div>
			<div id="thu190" class="hour"></div>
			<div id="thu195" class="hour"></div>
			<div id="thu200" class="hour"></div>
			<div id="thu205" class="hour"></div>
			<div id="thu210" class="hour"></div>
			<div id="thu215" class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Vendredi</td>
		<td class="hours-column">
			<div id="fri85" class="hour"></div>
			<div id="fri90" class="hour"></div>
			<div id="fri95" class="hour"></div>
			<div id="fri100" class="hour"></div>
			<div id="fri105" class="hour"></div>
			<div id="fri110" class="hour"></div>
			<div id="fri115" class="hour"></div>
			<div id="fri120" class="hour"></div>
			<div id="fri125" class="hour"></div>
			<div id="fri130" class="hour"></div>
			<div id="fri135" class="hour"></div>
			<div id="fri140" class="hour"></div>
			<div id="fri145" class="hour"></div>
			<div id="fri150" class="hour"></div>
			<div id="fri155" class="hour"></div>
			<div id="fri160" class="hour"></div>
			<div id="fri165" class="hour"></div>
			<div id="fri170" class="hour"></div>
			<div id="fri175" class="hour"></div>
			<div id="fri180" class="hour"></div>
			<div id="fri185" class="hour"></div>
			<div id="fri190" class="hour"></div>
			<div id="fri195" class="hour"></div>
			<div id="fri200" class="hour"></div>
			<div id="fri205" class="hour"></div>
			<div id="fri210" class="hour"></div>
			<div id="fri215" class="hour"></div>
		</td>
	</tr>
</table>
</body>
</html>


