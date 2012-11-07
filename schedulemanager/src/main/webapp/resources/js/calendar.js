$(function() {
	$('.hour').sortable({
		connectWith : '.hour',
		start: function(e, ui){
	        ui.placeholder.width(ui.item.width());
	    }
	});
	$('.hour').disableSelection();
	
	$('.event').tooltip();
	
	for (var i = 0; i < cs.courseSlots.length; i++) {
		var event = $('<div/>', {
		    class : 'event',
		    width : setDuration(cs.courseSlots[i]),
		}).appendTo(findId(cs.courseSlots[i]));
		
		$('<div/>', {
		    id: cs.courseSlots[i].nrc,
		    title: cs.courseSlots[i].acronym,
		    class : 'event-name',
		    text : cs.courseSlots[i].acronym,
		}).appendTo(event);
	}

	$('.event').resizable({
		minWidth: 90,
		maxHeight: 25,
		minHeight: 25,
		grid: 100
	});
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
