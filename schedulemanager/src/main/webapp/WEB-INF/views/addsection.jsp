<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
				<h1>Ajout d'une section au cours ${acronym}</h1>
			</div>
		</div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#incourse" data-toggle="tab">En
					classe</a></li>
			<li><a href="#virtual" data-toggle="tab">Virtuel</a></li>
			<li><a href="#remotly" data-toggle="tab">À distance</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="incourse"><c:import url="sectiontemplate.jsp"><c:param name="credits" value="${course.credits}"></c:param></c:import></div>
			<div class="tab-pane" id="virtual"><c:import url="sectiontemplate.jsp"><c:param name="credits" value="${course.credits}"></c:param></c:import></div>
			<div class="tab-pane" id="remotly">
				<div class="row-fluid">
					<div class="span2 pull-right">
						<b>Crédits &raquo;</b> ${course.credits}
					</div>
					<div class="span11">
						<label class="span3 control-label">Titulaire</label>
						<div class="span8 controls">
							<select class="input-xlarge">
								<option>Thierry Eude</option>
								<option>Nadia Tawbi</option>
								<option>Denis Laurendeau</option>
								<option>C'est ça la</option>
								<option>Marc-Philippe Parent</option>
							</select>
						</div>
					</div>
					<div class="span11">
						<label class="span3 control-label">Heures de travail
							personnel</label> <label class="span2 control-label">#lerestant#</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
