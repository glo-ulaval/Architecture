<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/schedule/editsection/${id}/${year}/${semester}/${section.nrc}" var="url" />
<form action="${url}" method=POST scope="request" commandName="section">
	<input type="hidden" name="teachMode" value="${section.teachMode}"
		scope="request"> <input type="hidden" name="acronym"
		value="${section.acronym}" scope="request">
	<div class="row-fluid section_details">
		<div class="span3 pull-right group">
			<b>Groupe : </b><input class="groupInput" disabled="disabled" type="text" name="group"
				value="${section.group}" scope="request">
		</div>
		<div class="span8" id="bordered">
			<label class="span3 control-label">Titulaire :</label>
			<div class="span8 controls">
			<input disabled="disabled" type="text" name="personInCharge"
        value="${section.personInCharge}" scope="request">
			</div>
		</div>
		<div class="span8" id="bordered">
			<label class="span3 control-label">Enseignants :</label>
			<div class="span8 controls">
				<div class="teachers">
        <c:forEach var="teacher" items="${section.teachers}">
          <input disabled="disabled" type="text" name="teachers"
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
								class="input-small hours_class" name="hoursInClass" value="${section.hoursInClass}">
						</div>
						<div id="hour_group">
							<label class="control-label" for="hours_labo">Labo/Travail
								dirigé</label> <input type="text" placeholder="H"
								class="input-small hours_labo" name="hoursInLab" value="${section.hoursInLab}">
						</div>
						<div id="hour_group">
							<label class="control-label" for="hours_home">Travail
								personnel</label> <input type="text" placeholder="H"
								class="input-small hours_home" name="hoursAtHome" value="${section.hoursAtHome}">
						</div>
					</div>
				</div>
				<c:if test="${not empty section.days}">
				<div class="hours_class_div">
					<div class="span8" id="bordered">
						<label class="span3 control-label">Heures en classe :</label>
						<div class="span9 controls">
							<div class="btn-group" data-toggle="buttons-radio">
								<button type="button" class="btn btn-info active"
									onClick=addHours("${section.teachMode}", 1)>1 séance</button>
								<button type="button" class="btn btn-info" onClick=addHours("${section.teachMode}", 2)>2
									séances</button>
								<button type="button" class="btn btn-info" onClick=addHours("${section.teachMode}", 3)>3
									séances</button>
							</div>
							<c:forEach var="day" items="${section.days}" varStatus="status">
							<div class="hours">
								<select class="input-medium days" name="days" value='${day}'><option>Lundi</option>
									<option>Mardi</option>
									<option>Mercredi</option>
									<option>Jeudi</option>
									<option>Vendredi</option></select><input type="text" placeholder="HH:MM"
									class="input-small" name="timeSlotStarts" value="${section.timeSlotStarts[0]}0"/> &agrave; <input
									type="text" placeholder="HH:MM" class="input-small"
									name="timeSlotEnds" value="${section.timeSlotEnds[0]}0"/> <br />
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${not empty section.labDay}">
				<div class="hours_labo_div">
					<div class="span8" id="bordered">
						<label class="span3 control-label">Heures en labo/travail
							dirigé :</label>
						<div class="span8 controls">
							<div class="hoursLab">
								<select class="input-medium labDay" name="labDay" value="${section.labDay}" selected><option>Lundi</option>
									<option>Mardi</option>
									<option>Mercredi</option>
									<option>Jeudi</option>
									<option>Vendredi</option></select> <input type="text"
									placeholder="HH:MM" class="input-small"
									name="laboTimeSlotStart" value="${section.laboTimeSlotStart}0"> à <input type="text"
									placeholder="HH:MM" class="input-small" name="laboTimeSlotEnd" value="${section.laboTimeSlotEnd}0">
							</div>
						</div>
					</div>
				</div>
				</c:if>
			</c:when>
		</c:choose>
	</div>
	<input type="submit" class="btn btn-success pull-right"
		value="Sauvegarder">
</form>