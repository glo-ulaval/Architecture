$(function() {
	initializeCalendar();
	
	var prevDay = "MONDAY";
	var height = 0;
	for ( var i = 0; i < sections.courseSlots.length; i++) {
		
		var durationInHours = parseInt(sections.courseSlots[i].duration);
		var cs = sections.courseSlots[i];
		var nextTime = getNextTime(cs);

		if(prevDay != cs.dayOfWeek){
			height = 0;
		}
		
		createEventDiv(height, durationInHours, cs, nextTime);

		prevDay = cs.dayOfWeek;
		height++;
	}

	setEventFunctions();

});

function createEventDiv(height, durationInHours, cs, nextTime) {
	var event = $('<div/>', {
		class : 'event',
	});
	event.appendTo(findId(cs, nextTime));
	event.css('width', durationInHours * 100+'px');

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

function initializeCalendar(){
	$('.hour').sortable({
		connectWith : '.hour',
		start : function(e, ui) {
			ui.placeholder.width(ui.item.width());
		},
		receive : function(e, ui) {
			$.ajax({
				type : "POST",
				url : '/schedulemanager/schedule/' + id + '/update',
				data : {
					nrc : ui.item.find('.event-name').attr('id').toString(),
					oldDay : getDay(ui.sender),
					oldTimeStart : getTimeStart(ui.sender),
					newDay : getDay(ui.item.parent()),
					newTimeStart : getTimeStart(ui.item.parent()),
					duration : getDuration(ui.item)
				},
				success : function(data) {

				},
				error : function() {

				}
			});
		}
	});
	$('.hour').disableSelection();
}

function setEventFunctions(){
	$('.event').dblclick(function(event) {
		redirectToEditSection();
	});

	$('.event').tooltip();
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

function redirectToEditSection() {
	var nrc = $(event.target).attr('id');
	var currentUrl = document.URL;
	var url = currentUrl.replace('calendar', 'schedule/editsection')
			+ '/2011-2012/Automne/' + nrc;

	window.location.replace(url);
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
	var duration = pixelWidth / 50;
	return duration;

}
