console.log(sections);

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
				data : { nrc: ui.item.find('.event-name').attr('id').toString(), 
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

		var duration = parseInt(sections.courseSlots[i].duration);
		duration = 2*duration;
		var color = randomColor();

		//for ( var j = 0; j < duration ; j++) {
			var cs = sections.courseSlots[i];

			var hour = cs.timeSlotStart.split(':')[0];
			var minute = minutes(cs);
			var nextTime = parseInt(hour + minute);
			//nextTime = nextTime + 5*j;
			
			var event = $('<div/>', {
			    class : 'event',
			});
			event.css('background-color', color);
			
			event.appendTo(findId(cs, nextTime));
			
			event.css('width',  parseInt(sections.courseSlots[i].duration)*100 +'px');
			
			var position = event.parent().position();
			event.css('position', 'absolute');
			event.css('top', position.top + i*25);
			
			//if(j == duration-1){
				$('<div/>', {
					id: sections.courseSlots[i].nrc,
					title: sections.courseSlots[i].acronym,
					class : 'event-name',
					text : sections.courseSlots[i].acronym,
				}).appendTo(event);
			//}
		//}
	}
	
	$('.event').dblclick(function(event) {
		redirectToEditSection();
	});

	$('.event').tooltip();
	
});

function randomColor() {
	return '#'+Math.floor(Math.random()*16777215).toString(16);
}

function findId(cs, nextTime) {
	return ('#'+ cs.dayOfWeek.toLowerCase().substring(0,3) + nextTime);
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
	var duration = pixelWidth/100;
	return duration;
	
}
