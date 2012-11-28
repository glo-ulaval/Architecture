$(document).ready(function() {

	$('.hours_class').bind('input', function() {
		if ($(this).val() <= 0) {
			$('.hours_class_div').hide();
		} else {
			$('.hours_class_div').show();
		}
	});

	$('.hours_labo').bind('input', function() {
		if ($(this).val() <= 0) {
			$('.hours_labo_div').hide();
		} else {
			$('.hours_labo_div').show();
		}
	});

	$('.hours_home').bind('input', function() {
		if ($(this).val() <= 0) {
			$('.hours_other_div').hide();
		} else {
			$('.hours_other_div').show();
		}
	});
	
	$('#proposeCourses').click(function() {
		alert('allo');
	});

});

function addHours(teachmode, hours) {
	var element;
	var pane = $('#'+teachmode);
	if (pane == null) {
		element = $('.hours')[0];
	} else {
		element = pane.find('.hours')[0];
	}
	var html = '';
		
	for (var i = 0; i < hours; i++) {
		html += '<select class="input-medium days" name="days">'+
		'<option>Lundi</option><option>Mardi</option><option>Mercredi</option><option>Jeudi</option><option>Vendredi</option></select>'+
		'<input type="text" placeholder="HH:MM"class="input-small" name="timeSlotStarts" />'+
		' &agrave; <input type="text" placeholder="HH:MM" class="input-small"name="timeSlotEnds" /> <br />';

	}
	element.innerHTML = html;
}

function addTeacher(teachmode) {
	var newDiv = $('<div/>', {
		class : 'divteacher'
	});
	$('.teachersDropdown').first().clone().appendTo(newDiv);
	var a = $('<a/>', {
		class : 'btn btn-danger removeTeacher',
		onClick : 'removeTeacher("' + teachmode + '")'
	});
	var i = $('<i/>', {
		class : 'icon-minus-sign icon-white'
	});
	
	i.appendTo(a);
	a.appendTo(newDiv);
	
	var pane = $('#'+teachmode);
	var teacher = pane.find('.teachers')[0];
	teacher.appendChild(newDiv);
}

function removeTeacher(teachmode) {
	var pane = $('#'+teachmode);
	var teacher = pane.find('.teachers')[0];
	var teachers = teacher.find('.divteacher');
	teacher.removeChild(teachers[teachers.length-1]);
}

function changeGroup(teachmode, group) {
	var pane = $('#'+teachmode);
	var groupElement = pane.find('.groupInput')[0];
	groupElement.value = group;
}