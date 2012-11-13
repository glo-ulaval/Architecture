$(function() {
	
	console.log(sections);
	
	$('.hour').sortable({
		connectWith : '.hour',
		start: function(e, ui){
	        ui.placeholder.width(ui.item.width());
	    },
	    receive : function(e, ui){
	    	$.ajax({
				type : "POST",
				url : '/schedulemanager/schedule/' + id + '/update',
				data : { nrc: ui.item.find('div[class="event-name"]').attr('id').toString(), 
						 oldDay: getDay(ui.sender),
						 oldTimeStart: getTimeStart(ui.sender), 
						 newDay: getDay(ui.item.parent()),
						 newTimeStart: getTimeStart(ui.item.parent()),
						 duration: getDuration(ui.item)},
				success: function(data) {
					
				},
				error : function() {
					
				}
			});
	    }
	});
	$('.hour').disableSelection();
	
	
	
	for (var i = 0; i < sections.courseSlots.length; i++) {
		var event = $('<div/>', {
		    class : 'event',
		    width : setDuration(sections.courseSlots[i]),
		}).appendTo(findId(sections.courseSlots[i]));
		
		$('<div/>', {
		    id: sections.courseSlots[i].nrc,
		    title: sections.courseSlots[i].acronym,
		    class : 'event-name',
		    text : sections.courseSlots[i].acronym,
		}).appendTo(event);
	}

	$('.event').resizable({
		minWidth: 90,
		maxHeight: 25,
		minHeight: 25,
		grid: 100
	});
	
	$('.event').dblclick(function(event) {
		redirectToEditSection();
	});

	$('.event').tooltip();
	
});

function findId(cs) {
	return ('#'+ cs.dayOfWeek.toLowerCase().substring(0,3) + cs.timeSlotStart.split(':')[0] + minutes(cs));
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

function redirectToEditSection(){
	var nrc = $(event.target).attr('id');
	var currentUrl = document.URL;
	var url = currentUrl.replace('calendar','schedule/editsection') + '/2011-2012/Automne/' + nrc;
	
	window.location.replace(url);
}

function getDay(object){
	return object.attr('id').substr(0,3);
}
	
function getTimeStart(object) {
	var time = object.attr('id').substr(3);
	var hour = time.substr(0, time.length - 1);
	var minute = time.substr(time.length - 1);
	if(minute == "5") {
		minute = "30";
	}else {
		minute = "00";
	}
	return hour + ":" + minute;
} 

function getDuration(object) {
	var pixelWidth = object.css('width').substr(0, object.css('width').length - 2);
	var duration = pixelWidth/50;
	return duration;
	
}