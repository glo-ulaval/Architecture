<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	
<script type="text/javascript">
	var json = ${availabilitiesJSON};
</script>
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
			<div class="btn-group">
				<button id="btn-dispo" class="btn">1</button>
				<button id="btn-ssi-dispo" class="btn">2</button>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span2">
				<h3>Lundi</h3>
				<ol class="well centered selectable" style="display:block;">
					<li id="mon8-1" class="ui-widget-content">08:30</li>
					<li id="mon9-1" class="ui-widget-content">09:30</li>
					<li id="mon10-1" class="ui-widget-content">10:30</li>
					<li id="mon11-1" class="ui-widget-content">11:30</li>
					<li id="mon12-1" class="ui-widget-content">12:30</li>
					<li id="mon13-1" class="ui-widget-content">13:30</li>
					<li id="mon14-1" class="ui-widget-content">14:30</li>
					<li id="mon15-1" class="ui-widget-content">15:30</li>
					<li id="mon16-1" class="ui-widget-content">16:30</li>
					<li id="mon17-1" class="ui-widget-content">17:30</li>
					<li id="mon18-1" class="ui-widget-content">18:30</li>
					<li id="mon19-1" class="ui-widget-content">19:30</li>
					<li id="mon20-1" class="ui-widget-content">20:30</li>
				</ol>
				<ol class="well centered selectable2" style="display:none;">
					<li id="mon8-2" class="ui-widget-content">08:30</li>
					<li id="mon9-2" class="ui-widget-content">09:30</li>
					<li id="mon10-2" class="ui-widget-content">10:30</li>
					<li id="mon11-2" class="ui-widget-content">11:30</li>
					<li id="mon12-2" class="ui-widget-content">12:30</li>
					<li id="mon13-2" class="ui-widget-content">13:30</li>
					<li id="mon14-2" class="ui-widget-content">14:30</li>
					<li id="mon15-2" class="ui-widget-content">15:30</li>
					<li id="mon16-2" class="ui-widget-content">16:30</li>
					<li id="mon17-2" class="ui-widget-content">17:30</li>
					<li id="mon18-2" class="ui-widget-content">18:30</li>
					<li id="mon19-2" class="ui-widget-content">19:30</li>
					<li id="mon20-2" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Mardi</h3>
				<ol class="well centered selectable" style="display:block;">
					<li id="tue8-1" class="ui-widget-content">08:30</li>
					<li id="tue9-1" class="ui-widget-content">09:30</li>
					<li id="tue10-1" class="ui-widget-content">10:30</li>
					<li id="tue11-1" class="ui-widget-content">11:30</li>
					<li id="tue12-1" class="ui-widget-content">12:30</li>
					<li id="tue13-1" class="ui-widget-content">13:30</li>
					<li id="tue14-1" class="ui-widget-content">14:30</li>
					<li id="tue15-1" class="ui-widget-content">15:30</li>
					<li id="tue16-1" class="ui-widget-content">16:30</li>
					<li id="tue17-1" class="ui-widget-content">17:30</li>
					<li id="tue18-1" class="ui-widget-content">18:30</li>
					<li id="tue19-1" class="ui-widget-content">19:30</li>
					<li id="tue20-1" class="ui-widget-content">20:30</li>
				</ol>
				<ol class="well centered selectable2" style="display:none;">
					<li id="tue8-2" class="ui-widget-content">08:30</li>
					<li id="tue9-2" class="ui-widget-content">09:30</li>
					<li id="tue10-2" class="ui-widget-content">10:30</li>
					<li id="tue11-2" class="ui-widget-content">11:30</li>
					<li id="tue12-2" class="ui-widget-content">12:30</li>
					<li id="tue13-2" class="ui-widget-content">13:30</li>
					<li id="tue14-2" class="ui-widget-content">14:30</li>
					<li id="tue15-2" class="ui-widget-content">15:30</li>
					<li id="tue16-2" class="ui-widget-content">16:30</li>
					<li id="tue17-2" class="ui-widget-content">17:30</li>
					<li id="tue18-2" class="ui-widget-content">18:30</li>
					<li id="tue19-2" class="ui-widget-content">19:30</li>
					<li id="tue20-2" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Mercredi</h3>
				<ol class="well centered selectable" style="display:block;">
					<li id="wed8-1" class="ui-widget-content">08:30</li>
					<li id="wed9-1" class="ui-widget-content">09:30</li>
					<li id="wed10-1" class="ui-widget-content">10:30</li>
					<li id="wed11-1" class="ui-widget-content">11:30</li>
					<li id="wed12-1" class="ui-widget-content">12:30</li>
					<li id="wed13-1" class="ui-widget-content">13:30</li>
					<li id="wed14-1" class="ui-widget-content">14:30</li>
					<li id="wed15-1" class="ui-widget-content">15:30</li>
					<li id="wed16-1" class="ui-widget-content">16:30</li>
					<li id="wed17-1" class="ui-widget-content">17:30</li>
					<li id="wed18-1" class="ui-widget-content">18:30</li>
					<li id="wed19-1" class="ui-widget-content">19:30</li>
					<li id="wed20-1" class="ui-widget-content">20:30</li>
				</ol>
				<ol class="well centered selectable2" style="display:none;">
					<li id="wed8-2" class="ui-widget-content">08:30</li>
					<li id="wed9-2" class="ui-widget-content">09:30</li>
					<li id="wed10-2" class="ui-widget-content">10:30</li>
					<li id="wed11-2" class="ui-widget-content">11:30</li>
					<li id="wed12-2" class="ui-widget-content">12:30</li>
					<li id="wed13-2" class="ui-widget-content">13:30</li>
					<li id="wed14-2" class="ui-widget-content">14:30</li>
					<li id="wed15-2" class="ui-widget-content">15:30</li>
					<li id="wed16-2" class="ui-widget-content">16:30</li>
					<li id="wed17-2" class="ui-widget-content">17:30</li>
					<li id="wed18-2" class="ui-widget-content">18:30</li>
					<li id="wed19-2" class="ui-widget-content">19:30</li>
					<li id="wed20-2" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Jeudi</h3>
				<ol class="well centered selectable" style="display:block;">
					<li id="thu8-1" class="ui-widget-content">08:30</li>
					<li id="thu9-1" class="ui-widget-content">09:30</li>
					<li id="thu10-1" class="ui-widget-content">10:30</li>
					<li id="thu11-1" class="ui-widget-content">11:30</li>
					<li id="thu12-1" class="ui-widget-content">12:30</li>
					<li id="thu13-1" class="ui-widget-content">13:30</li>
					<li id="thu14-1" class="ui-widget-content">14:30</li>
					<li id="thu15-1" class="ui-widget-content">15:30</li>
					<li id="thu16-1" class="ui-widget-content">16:30</li>
					<li id="thu17-1" class="ui-widget-content">17:30</li>
					<li id="thu18-1" class="ui-widget-content">18:30</li>
					<li id="thu19-1" class="ui-widget-content">19:30</li>
					<li id="thu20-1" class="ui-widget-content">20:30</li>
				</ol>				
				<ol class="well centered selectable2" style="display:none;">
					<li id="thu8-2" class="ui-widget-content">08:30</li>
					<li id="thu9-2" class="ui-widget-content">09:30</li>
					<li id="thu10-2" class="ui-widget-content">10:30</li>
					<li id="thu11-2" class="ui-widget-content">11:30</li>
					<li id="thu12-2" class="ui-widget-content">12:30</li>
					<li id="thu13-2" class="ui-widget-content">13:30</li>
					<li id="thu14-2" class="ui-widget-content">14:30</li>
					<li id="thu15-2" class="ui-widget-content">15:30</li>
					<li id="thu16-2" class="ui-widget-content">16:30</li>
					<li id="thu17-2" class="ui-widget-content">17:30</li>
					<li id="thu18-2" class="ui-widget-content">18:30</li>
					<li id="thu19-2" class="ui-widget-content">19:30</li>
					<li id="thu20-2" class="ui-widget-content">20:30</li>
				</ol>
			</div>
			<div class="span2">
				<h3>Vendredi</h3>
				<ol class="well centered selectable" style="display:block;">
					<li id="fri8-1" class="ui-widget-content">08:30</li>
					<li id="fri9-1" class="ui-widget-content">09:30</li>
					<li id="fri10-1" class="ui-widget-content">10:30</li>
					<li id="fri11-1" class="ui-widget-content">11:30</li>
					<li id="fri12-1" class="ui-widget-content">12:30</li>
					<li id="fri13-1" class="ui-widget-content">13:30</li>
					<li id="fri14-1" class="ui-widget-content">14:30</li>
					<li id="fri15-1" class="ui-widget-content">15:30</li>
					<li id="fri16-1" class="ui-widget-content">16:30</li>
					<li id="fri17-1" class="ui-widget-content">17:30</li>
					<li id="fri18-1" class="ui-widget-content">18:30</li>
					<li id="fri19-1" class="ui-widget-content">19:30</li>
					<li id="fri20-1" class="ui-widget-content">20:30</li>
				</ol>
				<ol class="well centered selectable2" style="display:none;">
					<li id="fri8-2" class="ui-widget-content">08:30</li>
					<li id="fri9-2" class="ui-widget-content">09:30</li>
					<li id="fri10-2" class="ui-widget-content">10:30</li>
					<li id="fri11-2" class="ui-widget-content">11:30</li>
					<li id="fri12-2" class="ui-widget-content">12:30</li>
					<li id="fri13-2" class="ui-widget-content">13:30</li>
					<li id="fri14-2" class="ui-widget-content">14:30</li>
					<li id="fri15-2" class="ui-widget-content">15:30</li>
					<li id="fri16-2" class="ui-widget-content">16:30</li>
					<li id="fri17-2" class="ui-widget-content">17:30</li>
					<li id="fri18-2" class="ui-widget-content">18:30</li>
					<li id="fri19-2" class="ui-widget-content">19:30</li>
					<li id="fri20-2" class="ui-widget-content">20:30</li>
					</ol>
			</div>
		</div>
		<br /> <br />
		<div class="row-fluid">
			<div class="span12">
				<button class="btn btn-primary" id="submit">Enregistrer mes
					disponibilités</button>
			</div>
		</div>
	</div>
</body>
</html>
