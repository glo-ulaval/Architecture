$(document).ready(function() {

	$('.hours_labo').bind('input', function() {
		if ($(this).val() <= 0) {
			$(".hours_labo_div").hide();
		} else {
			$(".hours_labo_div").show();
			setHoursLab();
		}
	});
});

function setHoursLab() {
	var element = document.getElementsByClassName("hoursLab")[0];
	var html = '';
	html += '<select class="input-medium labDay" name="labDay"><option>Lundi</option><option>Mardi</option><option>Mercredi</option><option>Jeudi</option><option>Vendredi</option></select> <input type="text"placeholder="HH:MM" class="input-small"name="laboTimeSlotStart"> &agrave; <input type="text"placeholder="HH:MM" class="input-small" name="laboTimeSlotEnd">';
	element.innerHTML = html;
}