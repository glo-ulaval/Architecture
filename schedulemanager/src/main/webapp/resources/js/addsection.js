$(document).ready(function() {

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

function addHours(teachmode, hours) {
	var element;
	var pane = document.getElementById(teachmode);
	if (pane == null) {
		element = document.getElementsByClassName("hours")[0];
	} else {
		element = pane.getElementsByClassName("hours")[0];
	}
	var html = '';
	for (i = 0; i < hours; i++) {
		html += '<select class="input-medium days" name="days"><option>Lundi</option><option>Mardi</option><option>Mercredi</option><option>Jeudi</option><option>Vendredi</option></select><input type="text" placeholder="HH:MM"class="input-small" name="timeSlotStarts" /> &agrave; <input type="text" placeholder="HH:MM" class="input-small"name="timeSlotEnds" /> <br />';
	}
	element.innerHTML = html;
}

function addTeacher(teachmode) {
	var newDiv = document.createElement('div');
	newDiv.setAttribute('class', 'divteacher');
	newDiv.innerHTML = "<select class=\"input-xlarge\" name=\"teachers\"></select><a class=\"btn btn-danger removeTeacher\" onClick=removeTeacher(\"" + teachmode + "\")><i class=\"icon-minus-sign icon-white\"></i></a>";
	
	var pane = document.getElementById(teachmode);
	var teacher = pane.getElementsByClassName("teachers")[0];
	teacher.appendChild(newDiv);
}

function removeTeacher(teachmode) {
	var pane = document.getElementById(teachmode);
	var teacher = pane.getElementsByClassName("teachers")[0];
	var teachers = teacher.getElementsByClassName('divteacher');
	teacher.removeChild(teachers[teachers.length-1]);
}

function changeGroup(teachmode, group) {
	var pane = document.getElementById(teachmode);
	var groupElement = pane.getElementsByClassName("groupInput")[0];
	groupElement.value = group;
}