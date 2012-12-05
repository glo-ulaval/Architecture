$(function() {
	initializeDraggableCalendar();
	setDoubleClickEdit();
	initializeSendEmailBtn();
});

function initializeDraggableCalendar() {
	$('.hour').sortable(
			{
				connectWith : '.hour',
				distance : 50,
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
						},
						success : function(data) {
							var newDay = getDay(ui.item.parent());
							schedule = JSON.parse(data.model.schedule);
							refreshDay(newDay);
						}
					});
				}
			});
}

function setDoubleClickEdit() {
	$('.event').dblclick(function(event) {
		redirectToEditSection(event);
	});
}

function initializeSendEmailBtn() {
	$("#sendEmailDiv").show();
	$("#sendEmailBtn").click( function() {
		
		$('.loading').show();
		$('#emailSuccess').hide();	
		$('#emailFail').hide();
		
		$.ajax({
			type : "POST",
			url : '/schedulemanager/schedule/' +  schedule.scheduleInfo.id + '/sendEmail',
			data : {
			},
			success : function(data) {
				if(data == "success") {
					$('#emailSuccess').show();
				}
				else {
					$('#emailFail').show();
				}
				$('.loading').hide();
			}
		});
	});
}


function redirectToEditSection() {
	var nrc = $(event.target).attr('id');
	if(nrc){
		var url = 'http://localhost:8080/schedulemanager/schedule/editsection/'
			+ schedule.scheduleInfo.id + '/' + schedule.scheduleInfo.year + '/' + schedule.scheduleInfo.semester + '/' + nrc + '/calendar';
		window.location.assign(url);
	}
}

function refreshDay(day) {
	//UNCOMMENT TO REFRESH ONLY THE EDITED DAY
	//TODO TEST PERFORMANCES
	//if(day == "mon"){
		clearDay("monday");
		generateMonday();
	//}else if(day == "tue"){
		clearDay("tuesday");
		generateTuesday();
	//}else if (day == "wed") {
		clearDay("wednesday");
		generateWednesday();
	//}else if (day == "thu"){
		clearDay("thursday");
		generateThursday();
	//}else if (day == "fri"){
		clearDay("friday");
		generateFriday();
	//}
}

function clearDay(day){
	$('#'+day).next().find('.hour').empty();
}