<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function proposeHour(dayOfWeek, startTime, endTime, isLab) {
	if(isLab) {
		 $('.active .lab').show();
		$('.active .labDay').val(dayOfWeek);
	      $('.active .startingHourLab').val(startTime);
	      $('.active .endHourLab').val(endTime);
	} else {
	    $('.active .enterManuallyResult').show();
	    $('.active .days').val(dayOfWeek);
	    $('.active .startingHourCourse').val(startTime);
	    $('.active .endHourCourse').val(endTime);
	}
};
</script>
<c:url value="/schedule/proposesection/${id}/${year}/${semester}" var="url" />
<form action="${url}" method=POST scope="request" commandName="section">
<c:set var="count" value="0" scope="page"></c:set>
<c:choose>
<c:when test="${isLab}">
  Heures de labo suggérées : <br/>
</c:when>
<c:otherwise>
  Heures de cours suggérées :<br/>
</c:otherwise>
</c:choose>
<c:forEach var="timeSlot" items="${timeSlots}">
	<input class="radioCourse" type="radio" name="timeSlot" value="timeSlot${count}" onClick="proposeHour('${timeSlot.dayOfWeek}','${timeSlot.startTime}','${timeSlot.endTime}',${isLab})">&nbsp;&nbsp; ${timeSlot.dayOfWeek} : ${timeSlot.startTime} - ${timeSlot.endTime}<br/>
	<c:set var="count" value="${count + 1}" scope="page"></c:set>
</c:forEach>
</form>
