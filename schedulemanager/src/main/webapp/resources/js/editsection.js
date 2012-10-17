$(document).ready(function() {

	$('.hours_labo').bind('input', function() {
		if ($(this).val() <= 0) {
			$(".hours_labo_div").hide();
		} else {
			$(".hours_labo_div").show();
			setHoursLab();
		}
	});
	
	$('.hours_class').bind('input', function() {
		if ($(this).val() <= 0) {
			$(".hours_class_div").hide();
		} else {
			$(".hours_class_div").show();
			setHoursClass();
		}
	});
});

function setHoursClass() {
	var element = document.getElementsByClassName("classes")[0];
	var html = '<div class="btn-group" data-toggle="buttons-radio"><button type="button" class="btn btn-info active"onClick=addHours("${section.teachmode}",1)>1 s&eacute;ance</button><button type="button" class="btn btn-info" onClick=addHours("${section.teachmode}",2)>2 s&eacute;ances</button><button type="button" class="btn btn-info" onClick=addHours("${section.teachmode}",3)>3 s&eacute;ances</button></div><div class="hours"><select class="input-medium days" name="days"><option>Lundi</option><option>Mardi</option><option>Mercredi</option><option>Jeudi</option><option>Vendredi</option></select><input type="text" placeholder="HH:MM"class="input-small" name="timeSlotStarts" /> &agrave; <input type="text" placeholder="HH:MM" class="input-small" name="timeSlotEnds" /> <br /></div>';
	element.innerHTML = html;
}

function setHoursLab() {
	var element = document.getElementsByClassName("hoursLab")[0];
	var html = '';
	html += '<select class="input-medium labDay" name="labDay"><option>Lundi</option><option>Mardi</option><option>Mercredi</option><option>Jeudi</option><option>Vendredi</option></select> <input type="text"placeholder="HH:MM" class="input-small"name="laboTimeSlotStart"> &agrave; <input type="text"placeholder="HH:MM" class="input-small" name="laboTimeSlotEnd">';
	element.innerHTML = html;
}