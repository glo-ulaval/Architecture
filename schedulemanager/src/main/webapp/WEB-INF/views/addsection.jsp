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
				<h1>Ajout d'une section au cours ${acronym}</h1>
			</div>
		</div>
		<div class="btn-group" id="myTab" data-toggle="buttons-radio">
			<button class="active btn btn-info btn-large" onClick=changeGroup("incourse","A")><a class="tab_a" href="#incourse" data-toggle="tab">En
					classe</a></button>
			<button class = "btn btn-info btn-large" onClick=changeGroup("virtual","Z3")><a class="tab_a" href="#virtual" data-toggle="tab">Virtuel</a></button>
			<button class = "btn btn-info btn-large" onClick=changeGroup("remotly","Z1")><a class="tab_a" href="#remotly" data-toggle="tab">� distance</a></button>
		</div>
		<div class="tab-content">
			<div class="tab-pane active" id="incourse">
				<c:import url="sectiontemplate.jsp">
					<c:param name="credits" value="${course.credits}"></c:param>
					<c:param name="teachmode" value="incourse"></c:param>
					<c:param name="isdistance" value="false"></c:param>
				</c:import>
			</div>
			<div class="tab-pane" id="virtual">
				<c:import url="sectiontemplate.jsp">
					<c:param name="credits" value="${course.credits}"></c:param>
					<c:param name="teachmode" value="virtual"></c:param>
					<c:param name="isdistance" value="false"></c:param>
				</c:import>
			</div>
			<div class="tab-pane" id="remotly">
        <c:import url="sectiontemplate.jsp">
          <c:param name="credits" value="${course.credits}"></c:param>
          <c:param name="teachmode" value="remotly"></c:param>
          <c:param name="isdistance" value="true"></c:param>
        </c:import>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
