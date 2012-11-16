$(function() {
	initializeDraggableCalendar();
	setDoubleClickEdit();
});

function initializeDraggableCalendar() {
	$('.hour').sortable(
			{
				connectWith : '.hour',
				start : function(e, ui) {
					ui.placeholder.width(ui.item.width());
				},
				receive : function(e, ui) {
					$(ui.item).css('top', $(e.target).position().top+'px');
					$.ajax({
						type : "POST",
						url : '/schedulemanager/schedule/'
								+ schedule.scheduleInfo.id + '/update',
						data : {
							nrc : ui.item.find('.event-name').attr('id')
									.toString(),
							oldDay : getDay(ui.sender),
							oldTimeStart : getTimeStart(ui.sender),
							newDay : getDay(ui.item.parent()),
							newTimeStart : getTimeStart(ui.item.parent()),
							duration : getDuration(ui.item)
						}
					});
				}
			});
}

function setDoubleClickEdit() {
	$('.event').dblclick(function(event) {
		redirectToEditSection();
	});
}

function redirectToEditSection() {
	console.log("is that called?");
	var nrc = $(event.target).attr('id');
	var url = 'http://localhost:8080/schedulemanager/schedule/editsection/'
			+ schedule.scheduleInfo.id + '/' + schedule.scheduleInfo.year + '/' + schedule.scheduleInfo.semester + '/' + nrc + '/calendar';

	console.log(url);
	window.location.assign(url);
}