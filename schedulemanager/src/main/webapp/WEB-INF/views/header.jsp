<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="/schedulemanager"> <img
					src="<c:url value="/resources/img/logo_mini.png" />" />
					ScheduleManager
				</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="/schedulemanager">Accueil</a></li>
						<c:if test="${not empty user.idul}">
							<li><a href="${menu}">Menu</a></li>
						</c:if
						<li><a href="#">À propos</a></li>
						<li><a href="https://github.com/glo-ulaval/Architecture">Source</a></li>
					</ul>
					<c:if test="${not empty user.idul}">
						<div class="btn-group floatRight">
							<a class="btn btn-inverse" href="#"> <i
								class="icon-user icon-white"></i><b class="username">${user.idul}</b>
							</a> <a class="btn btn-inverse" href="<c:url value="/logout" />">
								<i class="icon-remove icon-white"></i>
							</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</header>