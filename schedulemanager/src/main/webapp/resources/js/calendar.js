$(function() {

	for ( var i = 0; i < schedule.monday.length; i++) {
		var cs = schedule.monday[i];
		generateCourses(cs, i);
	}
	for ( var i = 0; i < schedule.tuesday.length; i++) {
		var cs = schedule.tuesday[i];
		generateCourses(cs, i);
	}
	for ( var i = 0; i < schedule.wednesday.length; i++) {
		var cs = schedule.wednesday[i];
		generateCourses(cs, i);
	}
	for ( var i = 0; i < schedule.thursday.length; i++) {
		var cs = schedule.thursday[i];
		generateCourses(cs, i);
	}
	for ( var i = 0; i < schedule.friday.length; i++) {
		var cs = schedule.friday[i];
		generateCourses(cs, i);
	}
	$('.hour').disableSelection();
});

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

	$('<div/>', {
		id : cs.nrc,
		title : cs.acronym,
		class : 'event-name',
		text : cs.acronym,
	}).appendTo(event);
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
