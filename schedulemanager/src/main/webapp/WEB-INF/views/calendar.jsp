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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/app.js" />" /></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />" /></script>
<script type="text/javascript" src="<c:url value="/resources/js/calendar.js" />" /></script>

</head>

<table class="table">
	<tr>
		<td class="day"></td>
		<td class="hours">
			<div class="hour-head">8:30</div>
			<div class="hour-head">9:00</div>
			<div class="hour-head">9:30</div>
			<div class="hour-head">10:00</div>
			<div class="hour-head">10:30</div>
			<div class="hour-head">11:00</div>
			<div class="hour-head">11:00</div>
			<div class="hour-head">12:00</div>
			<div class="hour-head">12:00</div>
			<div class="hour-head">13:00</div>
			<div class="hour-head">13:00</div>
			<div class="hour-head">14:00</div>
			<div class="hour-head">14:00</div>
			<div class="hour-head">15:00</div>
			<div class="hour-head">15:00</div>
			<div class="hour-head">16:00</div>
			<div class="hour-head">16:00</div>
			<div class="hour-head">17:00</div>
			<div class="hour-head">17:00</div>
			<div class="hour-head">18:00</div>
			<div class="hour-head">18:00</div>
			<div class="hour-head">19:00</div>
			<div class="hour-head">19:00</div>
			<div class="hour-head">20:00</div>
			<div class="hour-head">20:00</div>
			<div class="hour-head">21:00</div>
			<div class="hour-head">21:00</div>
		</td>
	</tr>
	<tr>
		<td class="day">Lundi</td>
		<td class="hours">
			<div class="hour">
			    <div class="event">
			        <div class="event-name">Event</div>
			    </div>
			</div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Mardi</td>
		<td class="hours">
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Mercredi</td>
		<td class="hours">
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Jeudi</td>
		<td class="hours">
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
		</td>
	</tr>
	<tr>
		<td class="day">Vendredi</td>
		<td class="hours">
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
			<div class="hour"></div>
		</td>
	</tr>
</table>


