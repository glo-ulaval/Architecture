<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid section_details">
	<div class="span2 pull-right">
		<b>Cr�dits &raquo;</b> ${course.credits}
	</div>
	<div class="span8" id="bordered">
		<label class="span3 control-label">Titulaire :</label>
		<div class="span8 controls">
			<select class="input-xlarge" value="${section.personInCharge}">
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
			<div class="btn-group" data-toggle="buttons-radio">
				<button type="button" class="btn btn-info" onClick=addTeacher(1)>1
					enseignant</button>
				<button type="button" class="btn btn-info" onClick=addTeacher(2)>2
					enseignants</button>
			</div>
			<div id="teachers"></div>
		</div>
	</div>
	<c:choose>
		<c:when test="${!param.isdistance}">
			<div class="span8" id="bordered">
				<label class="span3 control-label">R�partition des cours :</label>
				<div class="span8 controls">
					<div id="hour_group">
						<label class="control-label" for="hours_class">En classe</label> <input
							type="text" placeholder="H" class="input-small hours_class"
							value="0">
					</div>
					<div id="hour_group">
						<label class="control-label" for="hours_labo">Labo/Travail
							dirig�</label> <input type="text" placeholder="H"
							class="input-small hours_labo" value="0">
					</div>
					<div id="hour_group">
						<label class="control-label" for="hours_home">Travail
							personnel</label> <input type="text" placeholder="H"
							class="input-small hours_home" value="0">
					</div>
				</div>
			</div>
			<div class="hours_class_div">
				<div class="span8" id="bordered">
					<label class="span3 control-label">Heures en classe :</label>
					<div class="span9 controls">
						<div class="btn-group" data-toggle="buttons-radio">
							<button type="button" class="btn btn-info" onClick=addHours(1)>1
								s�ance</button>
							<button type="button" class="btn btn-info" onClick=addHours(2)>2
								s�ances</button>
							<button type="button" class="btn btn-info" onClick=addHours(3)>3
								s�ances</button>
						</div>
						<div id="hours"></div>
					</div>
				</div>
			</div>
			<div class="hours_labo_div">
				<div class="span8" id="bordered">
					<label class="span3 control-label">Heures en labo/travail
						dirig� :</label>
					<div class="span8 controls">
						<div class="hours">
							<input type="text" placeholder="HH:MM" class="input-small">
							� <input type="text" placeholder="HH:MM" class="input-small">
						</div>
					</div>
				</div>
			</div>
			<div class="hours_other_div">
				<div class="span8 " id="bordered">
					<label class="span3 control-label">Heures de travail
						personnel :</label> <label class="span2 control-label centered"><div
							class="personal_hours">heures</div></label>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="hours_div">
				<div class="span8 " id="bordered">
					<label class="span3 control-label">Heures de travail
						personnel :</label> <label class="span2 control-label centered"><div
							class="personal_hours">${course.totalHours}  heures</div></label>
				</div>
		</c:otherwise>
	</c:choose>
</div>