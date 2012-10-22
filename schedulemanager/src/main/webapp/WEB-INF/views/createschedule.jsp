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
<link href="<c:url value="/resources/css/addsection.css" />"
	rel="stylesheet">
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
        <c:choose>
          <c:when test="${ empty error }"></c:when>
          <c:when test="${error != 'success'}">
            <div class="alert alert-error">
              <button type="button" class="close" data-dismiss="alert">×</button>
              <strong>Erreur!</strong> Une erreur est survenue, veuillez
              réessayer - ${error}.
            </div>
          </c:when>
          <c:when test="${ error == 'success'}">
            <div class="alert alert-success">
              <button type="button" class="close" data-dismiss="alert">×</button>
              <strong>Succès!</strong> La requête s'est effectuée avec succès.
            </div>
          </c:when>
        </c:choose>
		<div class="row-fluid">
			<div class="span6">
				<h1>Offre de cours ${year}</h1>
				<c:if test="${not empty courses}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Cours</th>
								<th>Actions</th>
							</tr>
						</thead>
						<c:forEach var="course" items="${courses}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<c:url value="/schedule/addsection/${id}/${year}/${semester}"
									var="url" />
								<td class="centered"><a class="btn btn-success"
									href="${url}?acronym=${course.acronym}"><i
										class="icon-plus-sign icon-white"></i></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<div class="span6">
				<h1>Sections de cours</h1>
				<c:if test="${not empty sections}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Sections</th>
								<th class="pull-right">Actions&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							</tr>
						</thead>
						<c:forEach var="section" items="${sections}">
							<tr id="${section.nrc}">
								<td><b>${section.acronym} - ${section.nrc} -
										${section.group}</b></td>
								<c:url value="/schedule/editsection/${id}/${year}/${semester}/${section.nrc}"
									var="url" />
								<c:url value="/schedule/deletesection/${id}/${year}/${semester}/${section.nrc}"
									var="deleteurl" />
								<td class="centered"><a
									class="btn btn-danger pull-right actionbutton"
									href="${deleteurl}"><i class="icon-trash icon-white"></i></a><a
									class="btn btn-warning pull-right actionbutton" href="${url}"><i
										class="icon-edit"></i></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
		<c:url var="generateurl" value="/schedule/${id}"></c:url>
		<a class="btn btn-success pull-right" href="${generateurl}">Générer</a>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
