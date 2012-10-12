<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/schedule/addsection/${id}/${year}/${semester}" var="url" />
<form action="${url}" method=POST scope="request" commandName="section">
	<input type="hidden" name="teachMode" value="${param.teachmode}"
		scope="request"> <input type="hidden" name="acronym"
		value="${course.acronym}" scope="request">
	<div class="row-fluid section_details">
		<div class="span3 pull-right group">
			<b>Groupe : </b><input class="groupInput" type="text" name="group"
				value="A" scope="request"> <b>Cr�dits &raquo;</b>
			${course.credits}
		</div>
		<div class="span8" id="bordered">
			<label class="span3 control-label">Titulaire :</label>
			<div class="span8 controls">
				<select class="input-xlarge" name="personInCharge"
					value="${selected}">
					<option>Thierry Eude</option>
					<option>Nadia Tawbi</option>
					<option>Denis Laurendeau</option>
					<option>C'est �a la</option>
					<option>Marc-Philippe Parent</option>
				</select>
			</div>
		</div>
		<div class="span8" id="bordered">
			<label class="span3 control-label">Enseignants :</label>
			<div class="span8 controls">
				<div class="teachers">
					<select class="input-xlarge" name="teachers" value="${selected}"><option>Thierry
							Eude</option>
						<option>Nadia Tawbi</option>
						<option>Denis Laurendeau</option>
						<option>C\'est �a la</option>
						<option>Marc-Philippe Parent</option></select>
				</div>
				<a class="btn btn-success" onClick=addTeacher("${param.teachmode}")><i
					class="icon-plus-sign icon-white"></i></a>
			</div>
		</div>
		<c:choose>
			<c:when test="${!param.isdistance}">
				<div class="span8" id="bordered">
					<label class="span3 control-label">Temps consacr� :</label>
					<div class="span8 controls">
						<div id="hour_group">
							<label class="control-label" for="hours_class">En classe</label>
							<input type="text" placeholder="H"
								class="input-small hours_class" name="hoursInClass" value="0">
						</div>
						<div id="hour_group">
							<label class="control-label" for="hours_labo">Labo/Travail
								dirig�</label> <input type="text" placeholder="H"
								class="input-small hours_labo" name="hoursInLab" value="0">
						</div>
						<div id="hour_group">
							<label class="control-label" for="hours_home">Travail
								personnel</label> <input type="text" placeholder="H"
								class="input-small hours_home" name="hoursAtHome" value="0">
						</div>
					</div>
				</div>
				<div class="hours_class_div">
					<div class="span8" id="bordered">
						<label class="span3 control-label">Heures en classe :</label>
						<div class="span9 controls">
							<div class="btn-group" data-toggle="buttons-radio">
								<button type="button" class="btn btn-info active"
									onClick=addHours(1)>1 s�ance</button>
								<button type="button" class="btn btn-info" onClick=addHours(2)>2
									s�ances</button>
								<button type="button" class="btn btn-info" onClick=addHours(3)>3
									s�ances</button>
							</div>
							<div id="hours">
								<select class="input-medium days" name="days"><option>Lundi</option>
									<option>Mardi</option>
									<option>Mercredi</option>
									<option>Jeudi</option>
									<option>Vendredi</option></select><input type="text" placeholder="HH:MM"
									class="input-small" name="timeSlotStarts" /> &agrave; <input
									type="text" placeholder="HH:MM" class="input-small"
									name="timeSlotEnds" /> <br />
							</div>
						</div>
					</div>
				</div>
				<div class="hours_labo_div">
					<div class="span8" id="bordered">
						<label class="span3 control-label">Heures en labo/travail
							dirig� :</label>
						<div class="span8 controls">
							<div class="hours">
								<select class="input-medium labDay" name="labDay"><option>Lundi</option>
									<option>Mardi</option>
									<option>Mercredi</option>
									<option>Jeudi</option>
									<option>Vendredi</option></select> <input type="text"
									placeholder="HH:MM" class="input-small"
									name="laboTimeSlotStart"> � <input type="text"
									placeholder="HH:MM" class="input-small" name="laboTimeSlotEnd">
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