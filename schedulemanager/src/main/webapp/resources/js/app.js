$(document).ready(function() {

	$(".hours_class_div").hide();
	$(".hours_labo_div").hide();
	$(".hours_other_div").hide();

	$('button[data-dismiss]').click(function() {
		$(this).parent().hide();
	});

	$('.hours_class').bind('input', function() {
		if ($(this).val() <= 0) {
			$(".hours_class_div").hide();
		} else {
			$(".hours_class_div").show();
		}
	});

	$('.hours_labo').bind('input', function() {
		if ($(this).val() <= 0) {
			$(".hours_labo_div").hide();
		} else {
			$(".hours_labo_div").show();
		}
	});

	$('.hours_home').bind('input', function() {
		if ($(this).val() <= 0) {
			$(".hours_other_div").hide();
		} else {
			$(".hours_other_div").show();
		}
	});

	$('a[data-toggle="tab"]').on('show', function(e) {
		$(".hours_class_div").hide();
		$(".hours_labo_div").hide();
		$(".hours_other_div").hide();
	});

});

function addHours(hours) {
	var element = document.getElementById("hours");
	var html = '';
	for (i = 0; i < hours; i++) {
		html += '<input type="text" placeholder="HH:MM" class="input-small"/> &agrave; <input type="text" placeholder="HH:MM" class="input-small"/> <br/>';
	}
	element.innerHTML = html;
};

function addTeacher() {
	var element = document.getElementById("teachers");
	var newDiv = document.createElement('div');
	newDiv.setAttribute('id', 'divteacher');
	newDiv.innerHTML = "<select class=\"input-xlarge\" name=\"teachers\" value=\"${selected}\"><option>Thierry Eude</option><option>Nadia Tawbi</option><option>Denis Laurendeau</option><option>C\'est ça la</option><option>Marc-Philippe Parent</option></select><a class=\"btn btn-danger removeTeacher\" onClick=removeTeacher()><i class=\"icon-minus-sign icon-white\"></i></a>";
	element.appendChild(newDiv)
}

function removeTeacher() {
	var element = document.getElementById("teachers");
	element.removeChild(document.getElementById('divteacher'));
}