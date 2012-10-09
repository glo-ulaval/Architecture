<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
		<div class="row-fluid">
			<div class="well span12">
					<table class="table table-striped">
						<tr>
							<td></td>
							<td>Lundi</td>
							<td>Mardi</td>
							<td>Mercredi</td>
							<td>Jeudi</td>
							<td>Vendredi</td>
						</tr>
						<tr>
							<td>08:30</td>
							<td><input type="checkbox" id="mon8"/></td>
							<td><input type="checkbox" id="tue8"/></td>
							<td><input type="checkbox" id="wed8"/></td>
							<td><input type="checkbox" id="thu8"/></td>
							<td><input type="checkbox" id="fri8"/></td>
						</tr>
						<tr>
							<td>09:30</td>
							<td><input type="checkbox" id="mon9"/></td>
							<td><input type="checkbox" id="tue9"/></td>
							<td><input type="checkbox" id="wed9"/></td>
							<td><input type="checkbox" id="thu9"/></td>
							<td><input type="checkbox" id="fri9"/></td>
						</tr>
						<tr>
							<td>10:30</td>
							<td><input type="checkbox" id="mon10"/></td>
							<td><input type="checkbox" id="tue10"/></td>
							<td><input type="checkbox" id="wed10"/></td>
							<td><input type="checkbox" id="thu10"/></td>
							<td><input type="checkbox" id="fri10"/></td>
						</tr>
						<tr>
							<td>11:30</td>
							<td><input type="checkbox" id="mon11"/></td>
							<td><input type="checkbox" id="tue11"/></td>
							<td><input type="checkbox" id="wed11"/></td>
							<td><input type="checkbox" id="thu11"/></td>
							<td><input type="checkbox" id="fri11"/></td>
						</tr>
						<tr>
							<td>12:30</td>
							<td><input type="checkbox" id="mon12"/></td>
							<td><input type="checkbox" id="tue12"/></td>
							<td><input type="checkbox" id="wed12"/></td>
							<td><input type="checkbox" id="thu12"/></td>
							<td><input type="checkbox" id="fri12"/></td>
						</tr>
						<tr>
							<td>13:30</td>
							<td><input type="checkbox" id="mon13"/></td>
							<td><input type="checkbox" id="tue13"/></td>
							<td><input type="checkbox" id="wed13"/></td>
							<td><input type="checkbox" id="thu13"/></td>
							<td><input type="checkbox" id="fri13"/></td>
						</tr>
						<tr>
							<td>14:30</td>
							<td><input type="checkbox" id="mon14"/></td>
							<td><input type="checkbox" id="tue14"/></td>
							<td><input type="checkbox" id="wed14"/></td>
							<td><input type="checkbox" id="thu14"/></td>
							<td><input type="checkbox" id="fri14"/></td>
						</tr>
						<tr>
							<td>15:30</td>
							<td><input type="checkbox" id="mon15"/></td>
							<td><input type="checkbox" id="tue15"/></td>
							<td><input type="checkbox" id="wed15"/></td>
							<td><input type="checkbox" id="thu15"/></td>
							<td><input type="checkbox" id="fri15"/></td>
						</tr>
						<tr>
							<td>16:30</td>
							<td><input type="checkbox" id="mon16"/></td>
							<td><input type="checkbox" id="tue16"/></td>
							<td><input type="checkbox" id="wed16"/></td>
							<td><input type="checkbox" id="thu16"/></td>
							<td><input type="checkbox" id="fri16"/></td>
						</tr>
						<tr>
							<td>17:30</td>
							<td><input type="checkbox" id="mon17"/></td>
							<td><input type="checkbox" id="tue17"/></td>
							<td><input type="checkbox" id="wed17"/></td>
							<td><input type="checkbox" id="thu17"/></td>
							<td><input type="checkbox" id="fri17"/></td>
						</tr>
						<tr>
							<td>18:30</td>
							<td><input type="checkbox" id="mon18"/></td>
							<td><input type="checkbox" id="tue18"/></td>
							<td><input type="checkbox" id="wed18"/></td>
							<td><input type="checkbox" id="thu18"/></td>
							<td><input type="checkbox" id="fri18"/></td>
						</tr>
						<tr>
							<td>19:30</td>
							<td><input type="checkbox" id="mon19"/></td>
							<td><input type="checkbox" id="tue19"/></td>
							<td><input type="checkbox" id="wed19"/></td>
							<td><input type="checkbox" id="thu19"/></td>
							<td><input type="checkbox" id="fri19"/></td>
						</tr>
						<tr>
							<td>20:30</td>
							<td><input type="checkbox" id="mon20"/></td>
							<td><input type="checkbox" id="tue20"/></td>
							<td><input type="checkbox" id="wed20"/></td>
							<td><input type="checkbox" id="thu20"/></td>
							<td><input type="checkbox" id="fri20"/></td>
						</tr>
					</table>
					<button class="btn" id="submit">Submit</button>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
