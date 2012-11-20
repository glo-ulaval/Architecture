console.log(schedule);

$(function() {

	generateMonday();
	generateTuesday();
	generateWednesday();
	generateThursday();
	generateFriday();

	$('.hour').disableSelection();

});

function generateMonday(){
	for ( var i = 0; i < schedule.monday.length; i++) {
		var cs = schedule.monday[i];
		generateCourses(cs, i);
	}
	$('#monday').css('height', schedule.monday.length * 25 + 'px');
}

function generateTuesday() {
	for ( var i = 0; i < schedule.tuesday.length; i++) {
		var cs = schedule.tuesday[i];
		generateCourses(cs, i);
	}
	$('#tuesday').css('height', schedule.tuesday.length * 25 + 'px');
}

function generateWednesday(){
	for ( var i = 0; i < schedule.wednesday.length; i++) {
		var cs = schedule.wednesday[i];
		generateCourses(cs, i);
	}
	$('#wednesday').css('height', schedule.wednesday.length * 25 + 'px');
} 

function generateThursday(){
	for ( var i = 0; i < schedule.thursday.length; i++) {
		var cs = schedule.thursday[i];
		generateCourses(cs, i);
	}
	$('#thursday').css('height', schedule.thursday.length * 25 + 'px');
}

function generateFriday(){
	for ( var i = 0; i < schedule.friday.length; i++) {
		var cs = schedule.friday[i];
		generateCourses(cs, i);
	}
	$('#friday').css('height', schedule.friday.length * 25 + 'px');
}

function generateCourses(cs, height) {
	var durationInHours = parseInt(cs.duration);
	var nextTime = getNextTime(cs);

	createEventDiv(height, durationInHours, cs, nextTime);
}

function createEventDiv(height, durationInHours, cs, nextTime) {
	var event = $('<div/>', {
		class : 'event',
	});
	event.appendTo(findId(cs, nextTime));
	event.css('width', durationInHours * 100 + 'px');

	var position = event.parent().position();
	event.css('position', 'absolute');
	event.css('top', position.top + height * 25);

	var course = $('<div/>', {
		id : cs.nrc,
		class : 'event-name',
		text : cs.acronym + ' - ' + cs.group,
	}).appendTo(event);
	
	if(cs.isLab) {
		event.addClass('lab');
	}
	
	generateConflictsPopover(cs, event, course);
}

function generateConflictsPopover(cs, event, course){
	if(cs.conflicts.length > 0) {
		var conflictDescription = "";

		for ( var i = 0; i < cs.conflicts.length; i++) {
			conflictDescription += '<b>' + cs.conflicts[i].firstNrc + '</b> '
					+ cs.conflicts[i].description + '<br/>';
			conflictDescription += getConflictTeacher(cs.conflicts[i]);
			conflictDescription += getConflictSecondNrc(cs.conflicts[i]);
		}

		var conflictTitle = "Conflit";
		if(cs.conflicts.length > 1) {
			conflictTitle += "s";
		}
		
		var conflictIcon = $('<i/>', {
			class : 'icon-fire icon-white conflictIcon',
		}).appendTo(course);
		conflictIcon.popover({
			placement: "right",
			trigger: "hover",
			title: conflictTitle,
			content: conflictDescription
		});

		if(event.hasClass('lab')) {
			event.removeClass('lab');
		}
		event.addClass('red');
	}
}

function getConflictTeacher(conflict) {
	if (conflict.teacher) {
		return '<b>Professeur impliqu&eacute; : </b><br/>' + conflict.teacher
				+ '<br/><br/>';
	}
	return '';
}

function getConflictSecondNrc(conflict) {
	if (conflict.secondNrc) {
		return 'Entre la section <b>' + conflict.firstNrc
				+ '</b> et la section <b>' + conflict.secondNrc
				+ '</b>, dans les plages horaires du <b>'
				+ conflict.dayOfWeek + '</b> allant'
				+ 'de <b><span class="blue">' + conflict.firstStartTime
				+ '</span></b> à <b><span class="blue">'
				+ conflict.firstEndTime
				+ '</span></b> et de <b><span class="blue">'
				+ conflict.secondStartTime
				+ '</span></b> à <b><span class="blue">'
				+ conflict.secondEndTime + '</span></b>.<br/>';
	}
	return '';
}

function getNextTime(cs) {
	var hour = cs.timeSlotStart.split(':')[0];
	var minute = minutes(cs);
	return parseInt(hour + minute);
}

function findId(cs, nextTime) {
	return ('#' + cs.dayOfWeek.toLowerCase().substring(0, 3) + nextTime);
}

function minutes(cs) {
	var minutes = cs.timeSlotStart.split(":")[1];
	if (minutes == 30) {
		return 5;
	}
	return 0;
}

function setDuration(cs) {
	return (cs.duration * 100) + 'px';
}

function getDay(object) {
	return object.attr('id').substr(0, 3);
}

function getTimeStart(object) {
	var time = object.attr('id').substr(3);
	var hour = time.substr(0, time.length - 1);
	var minute = time.substr(time.length - 1);
	if (minute == "5") {
		minute = "30";
	} else {
		minute = "00";
	}
	return hour + ":" + minute;
}

function getDuration(object) {
	var pixelWidth = object.css('width').substr(0,
			object.css('width').length - 2);
	var duration = pixelWidth / 100;
	return duration;
}
