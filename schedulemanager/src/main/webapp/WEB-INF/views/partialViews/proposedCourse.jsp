<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/schedule/proposesection/${id}/${year}/${semester}" var="url" />
<form action="${url}" method=POST scope="request" commandName="section">
<c:set var="count" value="0" scope="page"></c:set>
<c:forEach var="timeSlot" items="${timeSlots}">
	<input type="radio" name="timeSlot" value="timeSlot${count}">${timeSlot.dayOfWeek} : ${timeSlot.startTime} - ${timeSlot.endTime}<br>
	<c:set var="count" value="${count + 1}" scope="page"></c:set>
</c:forEach>
</form>
