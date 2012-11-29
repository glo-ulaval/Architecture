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
	
	
	$('#enterManually').click(function() {
		$('.active .enterManuallyResult').show();
		$('.active #proposeCourses').hide();
		$('.active #enterManually').hide();
	});
	
	$('#enterManuallyLab').click(function() {
		$('.active .lab').show();
		$('.active #proposeLab').hide();
		$('.active #enterManuallyLab').hide();
	});
	
	$('#proposeCourses').click(function() {
		postTimeSlots(false);
		$('#enterManually').hide();
		$('#proposeCourses').hide();
	});
	
	$('#proposeLab').click(function() {
		postTimeSlots(true);
		$('#enterManuallyLab').hide();
		$('#proposeLab').hide();
	});

});

function postTimeSlots(isLab) {
	var teachers = new Array();
	$('.active .teachers').find('select option:selected').each(function () {
		if ($.inArray($(this).val(), teachers) == -1) {
			console.log($(this).val());
			teachers.push($(this).val());
		}
    });
	var data;
	var url;
	if (isLab) {
		url='/schedulemanager/schedule/proposelabsection/'+ id + "/" + year + '/' + semester;
		data = {
				teachers : JSON.stringify(teachers),
				labHours : $('.active .hours_labo').val()
			};
	} else {
		url='/schedulemanager/schedule/proposesection/'+ id + "/" + year + '/' + semester;
		data = {
				teachers : JSON.stringify(teachers),
				courseHours : $('.active .hours_class').val()
			};
	}
	
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		success : function(data) {
			if (isLab) {
				$('.active .proposedHoursLab').show();
				$('.active .proposedHoursLab').html(data);
			} else {
				$('.active .proposedHours').show();
				$('.active .proposedHours').html(data);
			}
		}
	});
}

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
	var newDiv = document.createElement('div');  	
	newDiv.setAttribute('class', 'divteacher');
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
	var pane = document.getElementById(teachmode);
	var teacher = pane.getElementsByClassName("teachers")[0]; 	
	var teachers = teacher.getElementsByClassName('divteacher');
	teacher.removeChild(teachers[teachers.length-1]);
}

function changeGroup(teachmode, group) {
	var pane = $('#'+teachmode);
	var groupElement = pane.find('.groupInput')[0];
	groupElement.value = group;
}