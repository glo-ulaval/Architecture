<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/app.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/availibilities.css" />">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/availabilities.js" />" /></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.js" />" /></script>

</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="hero-unit">
			<h1>Disponibilités</h1>
			<p>Entrez vos disponiblités d'enseignement :</p>
		</div>
		<c:import url="partialViews/availibilitiesSuccess.jsp"></c:import>
		<div class="row-fluid">
			<div class="span2">
				<h3>Lundi</h3>
				<ol class="well centered selectable">
					<li id="mon8" class="ui-widget-content">08:30</li>
					<li id="mon9" class="ui-widget-content">09:30</li>
					<li id="mon10" class="ui-widget-content">10:30</li>
					<li id="mon11" class="ui-widget-content">11:30</li>
					<li id="mon12" class="ui-widget-content">12:30</li>
					<li id="mon13" class="ui-widget-content">13:30</li>
					<li id="mon14" class="ui-widget-content">14:30</li>
					<li id="mon15" class="ui-widget-content">15:30</li>
					<li id="mon16" class="ui-widget-content">16:30</li>
					<li id="mon17" class="ui-widget-content">17:30</li>
					<li id="mon18" class="ui-widget-content">18:30</li>
					<li id="mon19" class="ui-widget-content">19:30</li>
					<li id="mon20" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Mardi</h3>
				<ol class="well centered selectable">
					<li id="tue8" class="ui-widget-content">08:30</li>
					<li id="tue9" class="ui-widget-content">09:30</li>
					<li id="tue10" class="ui-widget-content">10:30</li>
					<li id="tue11" class="ui-widget-content">11:30</li>
					<li id="tue12" class="ui-widget-content">12:30</li>
					<li id="tue13" class="ui-widget-content">13:30</li>
					<li id="tue14" class="ui-widget-content">14:30</li>
					<li id="tue15" class="ui-widget-content">15:30</li>
					<li id="tue16" class="ui-widget-content">16:30</li>
					<li id="tue17" class="ui-widget-content">17:30</li>
					<li id="tue18" class="ui-widget-content">18:30</li>
					<li id="tue19" class="ui-widget-content">19:30</li>
					<li id="tue20" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Mercredi</h3>
				<ol class="well centered selectable">
					<li id="wed8" class="ui-widget-content">08:30</li>
					<li id="wed9" class="ui-widget-content">09:30</li>
					<li id="wed10" class="ui-widget-content">10:30</li>
					<li id="wed11" class="ui-widget-content">11:30</li>
					<li id="wed12" class="ui-widget-content">12:30</li>
					<li id="wed13" class="ui-widget-content">13:30</li>
					<li id="wed14" class="ui-widget-content">14:30</li>
					<li id="wed15" class="ui-widget-content">15:30</li>
					<li id="wed16" class="ui-widget-content">16:30</li>
					<li id="wed17" class="ui-widget-content">17:30</li>
					<li id="wed18" class="ui-widget-content">18:30</li>
					<li id="wed19" class="ui-widget-content">19:30</li>
					<li id="wed20" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Jeudi</h3>
				<ol class="well centered selectable">
					<li id="thu8" class="ui-widget-content">08:30</li>
					<li id="thu9" class="ui-widget-content">09:30</li>
					<li id="thu10" class="ui-widget-content">10:30</li>
					<li id="thu11" class="ui-widget-content">11:30</li>
					<li id="thu12" class="ui-widget-content">12:30</li>
					<li id="thu13" class="ui-widget-content">13:30</li>
					<li id="thu14" class="ui-widget-content">14:30</li>
					<li id="thu15" class="ui-widget-content">15:30</li>
					<li id="thu16" class="ui-widget-content">16:30</li>
					<li id="thu17" class="ui-widget-content">17:30</li>
					<li id="thu18" class="ui-widget-content">18:30</li>
					<li id="thu19" class="ui-widget-content">19:30</li>
					<li id="thu20" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Vendredi</h3>
				<ol class="well centered selectable">
					<li id="fri8" class="ui-widget-content">08:30</li>
					<li id="fri9" class="ui-widget-content">09:30</li>
					<li id="fri10" class="ui-widget-content">10:30</li>
					<li id="fri11" class="ui-widget-content">11:30</li>
					<li id="fri12" class="ui-widget-content">12:30</li>
					<li id="fri13" class="ui-widget-content">13:30</li>
					<li id="fri14" class="ui-widget-content">14:30</li>
					<li id="fri15" class="ui-widget-content">15:30</li>
					<li id="fri16" class="ui-widget-content">16:30</li>
					<li id="fri17" class="ui-widget-content">17:30</li>
					<li id="fri18" class="ui-widget-content">18:30</li>
					<li id="fri19" class="ui-widget-content">19:30</li>
					<li id="fri20" class="ui-widget-content">20:30</li>
				</ol>
			</div>
		</div>
		<br />
		<br />
		<div class="row-fluid">
			<div class="span12">
				<button class="btn btn-primary" id="submit">Enregistrer mes
					disponibilités</button>
			</div>
		</div>
	</div>
</body>
</html>
