<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid section_details">
	<div class="span2 pull-right">
		<b>Crédits &raquo;</b> ${course.credits}
	</div>
	<div class="span8" id="bordered">
		<label class="span3 control-label">Titulaire :</label>
		<div class="span8 controls">
			<select class="input-xlarge">
				<option>Thierry Eude</option>
				<option>Nadia Tawbi</option>
				<option>Denis Laurendeau</option>
				<option>C'est ça la</option>
				<option>Marc-Philippe Parent</option>
			</select>
		</div>
	</div>
	<c:if test="${!param.isdistance}">
	<div class="span8" id="bordered">
		<label class="span3 control-label">Heures en classe :</label>
		<div class="span9 controls">
		  <div class="btn-group" data-toggle="buttons-radio">
		    <button type="button" class="btn btn-info active" onClick=addHours(1)>1 séance</button>
		    <button type="button" class="btn btn-info" onClick=addHours(2)>2 séances</button>
		    <button type="button" class="btn btn-info" onClick=addHours(3)>3 séances</button>
		  </div>
		  <div id ="hours">
			 <input type="text" placeholder="HH:MM" class="input-small"> à
			 <input type="text" placeholder="HH:MM" class="input-small">
			</div>
		</div>
	</div>
	<div class="span8" id="bordered">
		<label class="span3 control-label">Heures en labo/travail
			dirigé :</label>
		<div class="span8 controls">
		   <div class="hours">
       <input type="text" placeholder="HH:MM" class="input-small"> à
       <input type="text" placeholder="HH:MM" class="input-small">
       </div>
		</div>
	</div>
	</c:if>
	<div class="span8 " id="bordered">
		<label class="span3 control-label">Heures de travail personnel :</label>
		<label class="span2 control-label centered"><div class="personal_hours">#lerestant#</div></label>
	</div>
</div>