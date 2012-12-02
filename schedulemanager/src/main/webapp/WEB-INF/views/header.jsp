<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var idul = "${user.idul}";
</script>
<script type="text/javascript" src="<c:url value="/resources/js/header.js" />" /></script>
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
						<c:choose>
							<c:when test="${not empty user.idul}">
								<li><a href="<c:url value="/logout" />">Accueil</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/schedulemanager">Accueil</a></li>
							</c:otherwise>
						</c:choose>
						<c:if test="${not empty user.idul}">
							<c:url var="menu" value="/menu"></c:url>
							<li><a href="${menu}">Menu</a></li>
						</c:if>
						<li><a href="#">À propos</a></li>
						<li><a href="https://github.com/glo-ulaval/Architecture">Source</a></li>
					</ul>
					<c:url var="profileUrl" value="/profile/${user.idul}"></c:url>
					<c:if test="${not empty user.idul}">
						<div class="floatRight">
							<a class="btn btn-info profil" href="${profileUrl}"><b>Modifier
									mon profil</b></a>
						</div>
						<div class="btn-group floatRight">
							<a class="btn btn-inverse"> <i class="icon-user icon-white"></i><b
								class="username">${user.idul}</b>
							</a> <a class="btn btn-inverse" href="<c:url value="/logout" />">
								<i class="icon-remove icon-white"></i>
							</a>
						</div>				
						<c:choose>
							<c:when test="${true}">
								<div class="btn-group floatRight">
									<a id="notificationBtn" class="btn btn-inverse" href="#">
										<i class="icon-comment icon-white"></i>
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="btn-group floatRight">
									<a id="notificationBtnDisabled" class="btn btn-inverse disabled">
										<i class="icon-comment"></i>
									</a>
								</div>
							</c:otherwise>
						</c:choose>
					</c:if>
				</div>

			</div>
		</div>
	</div>
</header>