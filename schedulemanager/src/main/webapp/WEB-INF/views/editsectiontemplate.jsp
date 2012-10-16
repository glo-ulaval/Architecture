<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:url
	value="/schedule/editsection/${id}/${year}/${semester}/${section.nrc}"
	var="url" />
<form action="${url}" method=POST scope="request" commandName="section">
	<input type="hidden" name="teachMode" value="${section.teachMode}"
		scope="request"> <input type="hidden" name="acronym"
		value="${section.acronym}" scope="request">
  <input type="hidden" name="group" value="${section.group}"
    scope="request"> <input type="hidden" name="personInCharge" value="${section.personInCharge}"
    scope="request"> <input type="hidden" name="teachers" value="${section.teachers}"
    scope="request"> 
	<div class="row-fluid section_details">
		<div class="span3 pull-right group">
			<b>Groupe : </b><input class="groupInput"
				type="text" value="${section.group}" disabled="disabled"}">
		</div>
		<div class="span8" id="bordered">
			<label class="span3 control-label">Titulaire :</label>
			<div class="span8 controls">
				<input disabled="disabled" type="text"
					value="${section.personInCharge}" scope="request">
			</div>
		</div>
		<div class="span8" id="bordered">
			<label class="span3 control-label">Enseignants :</label>
			<div class="span8 controls">
				<div class="teachers">
					<c:forEach var="teacher" items="${section.teachers}">
						<input disabled="disabled" type="text"
							value="${teacher}" scope="request">
					</c:forEach>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${param.teachMode != 'Remotly'}">
				<div class="span8" id="bordered">
					<label class="span3 control-label">Temps consacré :</label>
					<div class="span8 controls">
						<div id="hour_group">
							<label class="control-label" for="hours_class">En classe</label>
							<input type="text" placeholder="H"
								class="input-small hours_class" name="hoursInClass"
								value="${section.hoursInClass}">
						</div>
						<div id="hour_group">
							<label class="control-label" for="hours_labo">Labo/Travail
								dirigé</label> <input type="text" placeholder="H"
								class="input-small hours_labo" name="hoursInLab"
								value="${section.hoursInLab}">
						</div>
						<div id="hour_group">
							<label class="control-label" for="hours_home">Travail
								personnel</label> <input type="text" placeholder="H"
								class="input-small hours_home" name="hoursAtHome"
								value="${section.hoursAtHome}">
						</div>
					</div>
				</div>
				<c:if test="${not empty section.days}">
					<div class="hours_class_div" style="display: block;">
						<div class="span8" id="bordered">
							<label class="span3 control-label">Heures en classe :</label>
							<div class="span9 controls">
								<div class="btn-group" data-toggle="buttons-radio">
								<c:choose>
								<c:when test="${fn:length(section.days) == 1}"><c:set value="btn btn-info active" var="cssClass1"></c:set></c:when><c:otherwise><c:set value="btn btn-info" var="cssClass1"></c:set></c:otherwise>
								</c:choose>
                <c:choose>
                <c:when test="${fn:length(section.days) == 2}"><c:set value="btn btn-info active" var="cssClass2"></c:set></c:when><c:otherwise><c:set value="btn btn-info" var="cssClass2"></c:set></c:otherwise>
                </c:choose>
                <c:choose>
                <c:when test="${fn:length(section.days) == 3}"><c:set value="btn btn-info active" var="cssClass3"></c:set></c:when><c:otherwise><c:set value="btn btn-info" var="cssClass3"></c:set></c:otherwise>
                </c:choose>
									<button type="button" class="${cssClass1}"
										onClick=addHours("${section.teachMode}",1)>1 séance</button>
									<button type="button" class="${cssClass2}" onClick=addHours("${section.teachMode}",2)>2
										séances</button>
									<button type="button" class="${cssClass3}" onClick=addHours("${section.teachMode}",3)>3
										séances</button>
								</div>
								<div class="hours">
									<c:forEach var="day" items="${section.days}" varStatus="status">
										<select class="input-medium days" name="days"><option
												<c:if test="${day == 'Lundi'}">selected</c:if>>Lundi</option>
											<option <c:if test="${day == 'Mardi'}">selected</c:if>>Mardi</option>
											<option <c:if test="${day == 'Mercredi'}">selected</c:if>>Mercredi</option>
											<option <c:if test="${day == 'Jeudi'}">selected</c:if>>Jeudi</option>
											<option <c:if test="${day == 'Vendredi'}">selected</c:if>>Vendredi</option></select>
										<input type="text" placeholder="HH:MM" class="input-small"
											name="timeSlotStarts" value="${section.timeSlotStarts[status.index]}0" />
										&agrave; <input type="text" placeholder="HH:MM"
											class="input-small" name="timeSlotEnds"
											value="${section.timeSlotEnds[status.index]}0" />
										<br />
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<div class="hours_labo_div" style="display: block;">
					<div class="span8" id="bordered">
						<label class="span3 control-label">Heures en labo/travail
							dirigé :</label>
						<div class="span8 controls">
							<div class="hoursLab">
								<c:choose>
									<c:when test="${not empty section.labDay}">
										<select class="input-medium labDay" name="labDay"><option
												<c:if test="${section.labDay == 'Lundi'}">selected</c:if>>Lundi</option>
											<option
												<c:if test="${section.labDay == 'Mardi'}">selected</c:if>>Mardi</option>
											<option
												<c:if test="${section.labDay == 'Mercredi'}">selected</c:if>>Mercredi</option>
											<option
												<c:if test="${section.labDay == 'Jeudi'}">selected</c:if>>Jeudi</option>
											<option
												<c:if test="${section.labDay == 'Vendredi'}">selected</c:if>>Vendredi</option></select>
										<input type="text" placeholder="HH:MM" class="input-small"
											name="laboTimeSlotStart"
											value="${section.laboTimeSlotStart}0"> à <input
											type="text" placeholder="HH:MM" class="input-small"
											name="laboTimeSlotEnd" value="${section.laboTimeSlotEnd}0">
									</c:when>
									<c:otherwise> Pas d'heures de laboratoire.</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</c:when>
		</c:choose>
	</div>
	<input type="submit" class="btn btn-success pull-right"
		value="Sauvegarder">
</form>