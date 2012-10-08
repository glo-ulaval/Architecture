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
<link href="<c:url value="/resources/css/addsection.css" />" rel="stylesheet">
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
			<div class="tab-pane active" id="incourse">
				<c:import url="sectiontemplate.jsp">
					<c:param name="credits" value="${course.credits}"></c:param>
					<c:param name="isdistance" value="false"></c:param>
				</c:import>
			</div>
			<div class="tab-pane" id="virtual">
				<c:import url="sectiontemplate.jsp">
					<c:param name="credits" value="${course.credits}"></c:param>
					<c:param name="isdistance" value="false"></c:param>
				</c:import>
			</div>
			<div class="tab-pane" id="remotly">
        <c:import url="sectiontemplate.jsp">
          <c:param name="credits" value="${course.credits}"></c:param>
          <c:param name="isdistance" value="true"></c:param>
        </c:import>
			</div>
		</div>
		<a class="btn btn-success pull-right" href="addsection">Sauvegarder</a>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
