<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid section_details">
	<div class="span2 pull-right">
		<b>Cr�dits &raquo;</b> ${course.credits}
	</div>
	<div class="span11">
		<label class="span3 control-label">Titulaire</label>
		<div class="span8 controls">
			<select class="input-xlarge">
				<option>Thierry Eude</option>
				<option>Nadia Tawbi</option>
				<option>Denis Laurendeau</option>
				<option>C'est �a la</option>
				<option>Marc-Philippe Parent</option>
			</select>
		</div>
	</div>
	<div class="span11">
		<label class="span3 control-label">Heures en classe</label>
		<div class="span8 controls">
			<input type="text" placeholder="HH:MM" class="input-small"> �
			<input type="text" placeholder="HH:MM" class="input-small">
		</div>
	</div>
	<div class="span11">
		<label class="span3 control-label">Heures en labo/travail
			dirig�</label>
		<div class="span8 controls">
			<input type="text" placeholder="HH:MM" class="input-small"> �
			<input type="text" placeholder="HH:MM" class="input-small">
		</div>
	</div>
	<div class="span11">
		<label class="span3 control-label">Heures de travail personnel</label>
		<label class="span2 control-label">#lerestant#</label>
	</div>
</div>