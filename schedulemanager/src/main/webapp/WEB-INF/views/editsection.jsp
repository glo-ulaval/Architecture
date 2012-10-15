<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
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
  src="<c:url value="/resources/js/addsection.js" />" /></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.js" />" /></script>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h1>Édition de la section de cours ${section.acronym} - ${section.nrc} - ${section.group}</h1>
				<h2>
				<c:choose>
				<c:when test="${section.teachMode == 'InCourse'}">Section en classe</c:when>
				<c:when test="${section.teachMode == 'Virtual'}">Section en classe virtuelle</c:when>
				<c:when test="${section.teachMode == 'Remotly'}">Section à distance</c:when>
				</c:choose>
				</h2>
			</div>
		</div>
		<div class="tab-content">
			<div class="tab-pane active">
				<c:import url="editsectiontemplate.jsp"/>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
