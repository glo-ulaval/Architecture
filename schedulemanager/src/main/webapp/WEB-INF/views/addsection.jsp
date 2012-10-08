<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
				<h1>Ajout d'une section au cours ${acronym}</h1>
			</div>
		</div>
		<ul class="nav nav-tabs">
		  <li class = "active"><a href="#incourse" data-toggle="tab">En classe</a></li>
		  <li><a href="#virtual" data-toggle="tab">Virtuel</a></li>
		  <li><a href="#remotly" data-toggle="tab">À distance</a></li>
		</ul>
		<div class="tab-content">
		  <div class="tab-pane active" id="incourse">
	  		<div class="pull-right"><b>Crédits &raquo;</b> ${course.credits}</div>
				<form class="form-horizontal"><fieldset>
					<div class="span11 control-group">
						<label class="control-label">Titulaire</label>
						<div class="controls">
							<select>
								<option>Thierry Eude</option>
								<option>Nadia Tawbi</option>
								<option>Denis Laurendeau</option>
								<option>C'est ça la</option>
								<option>Marc-Philippe Parent</option>
							</select>
						</div>
					</div>
					<div class="span11 control-group">
						<label class="control-label">Heures en classe</label>
						<div class="controls">
							<select>
								<option>Thierry Eude</option>
								<option>Nadia Tawbi</option>
								<option>Denis Laurendeau</option>
								<option>C'est ça la</option>
								<option>Marc-Philippe Parent</option>
							</select>
						</div>
					</div>
				</fieldset></form>	  	
	  		</div>
		  <div class="tab-pane" id="virtual">

		  </div>
		  <div class="tab-pane" id="remotly">

		  </div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
