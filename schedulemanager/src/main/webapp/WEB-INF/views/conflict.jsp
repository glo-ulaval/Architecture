<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<c:choose>
	<c:when test="${not empty conflict.secondNrc}">
		<div class="centered">
			Entre la section <b>${conflict.firstNrc}</b> et la section <b>${conflict.secondNrc}</b>,
			dans les plages horaires du <b>${conflict.dayOfWeek}</b> allant
			de <b><span class="blue">${conflict.firstStartTime}</span></b>
			à <b><span class="blue">${conflict.firstEndTime}</span></b> et
			de <b><span class="blue">${conflict.secondStartTime}</span></b>
			à <b><span class="blue">${conflict.secondEndTime}</span></b>.
		</div>
	</c:when>
	<c:otherwise>
		<div class="centered">
			Dans la section <b>${conflict.firstNrc}</b>, dans la plage
			horaire du <b>${conflict.dayOfWeek}</b> allant de <b><span
				class="blue">${conflict.firstStartTime}</span></b> à <b><span
				class="blue">${conflict.firstEndTime}</span></b> .
		</div>
	</c:otherwise>
</c:choose>

<br>

<h4 class="centered">Description :</h4>
<div class="centered">${conflict.description}</div>
<br>

<c:if test="${not empty conflict.teacher}">
	<h4 class="centered">Professeur impliqué :</h4>
	<div class="centered">${conflict.teacher}</div>
</c:if>
