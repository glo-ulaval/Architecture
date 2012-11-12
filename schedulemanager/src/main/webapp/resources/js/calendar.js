$(function() {
	
	$('.hour').sortable({
		connectWith : '.hour',
		start: function(e, ui){
	        ui.placeholder.width(ui.item.width());
	    },
	    receive : function(e, ui){
	    	$.ajax({
				type : "POST",
				url : '/schedulemanager/schedule/' + id + '/update',
				data : { nrc: "John", timeStart: "2pm", timeEnd: "derp" },
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

		for ( var j = 0; j < duration ; j++) {
			var cs = sections.courseSlots[i];

			var hour = cs.timeSlotStart.split(':')[0];
			var minute = minutes(cs);
			var nextTime = parseInt(hour + minute);
			nextTime = nextTime + 5*j;
			
			
			var event = $('<div/>', {
			    class : 'event',
			});
			event.css('background-color', color);
			
			event.appendTo(findId(cs, nextTime));
			if(j == duration-1){
				$('<div/>', {
					id: sections.courseSlots[i].nrc,
					title: sections.courseSlots[i].acronym,
					class : 'event-name',
					text : sections.courseSlots[i].acronym,
				}).appendTo(event);
			}
		}
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