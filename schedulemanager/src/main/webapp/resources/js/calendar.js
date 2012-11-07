$(function() {
	$(".hour").sortable({
		connectWith : ".hour"
	});

	$(".hour").disableSelection();

	$(document).tooltip();

	var cs = {
		    "courseSlots": [
		                    {
		                        "nrc": "90659",
		                        "acronym": "IFT-2007",
		                        "group": "A",
		                        "isLab": false,
		                        "dayOfWeek": "MONDAY",
		                        "timeSlotStart": "13:30",
		                        "timeSlotEnd": "16:30"
		                    },
		                    {
		                        "nrc": "90623",
		                        "acronym": "IFT-2007",
		                        "group": "B",
		                        "isLab": false,
		                        "dayOfWeek": "MONDAY",
		                        "timeSlotStart": "11:30",
		                        "timeSlotEnd": "14:30"
		                    },
		                    {
		                        "nrc": "90111",
		                        "acronym": "IFT-2000",
		                        "group": "B",
		                        "isLab": false,
		                        "dayOfWeek": "MONDAY",
		                        "timeSlotStart": "8:30",
		                        "timeSlotEnd": "11:30"
		                    },
		                    {
		                        "nrc": "84789",
		                        "acronym": "GLO-2003",
		                        "group": "A",
		                        "isLab": false,
		                        "dayOfWeek": "WEDNESDAY",
		                        "timeSlotStart": "8:30",
		                        "timeSlotEnd": "11:30"
		                    },
		                    {
		                        "nrc": "87135",
		                        "acronym": "GLO-4002",
		                        "group": "A",
		                        "isLab": false,
		                        "dayOfWeek": "MONDAY",
		                        "timeSlotStart": "9:30",
		                        "timeSlotEnd": "12:30"
		                    },
		                    {
		                        "nrc": "83014",
		                        "acronym": "GLO-2100",
		                        "group": "A",
		                        "isLab": false,
		                        "dayOfWeek": "FRIDAY",
		                        "timeSlotStart": "18:30",
		                        "timeSlotEnd": "21:30"
		                    },
		                    {
		                        "nrc": "86453",
		                        "acronym": "GLO-1901",
		                        "group": "A",
		                        "isLab": false,
		                        "dayOfWeek": "TUESDAY",
		                        "timeSlotStart": "10:30",
		                        "timeSlotEnd": "13:30"
		                    },
		                    {
		                        "nrc": "89782",
		                        "acronym": "GLO-1010",
		                        "group": "A",
		                        "isLab": false,
		                        "dayOfWeek": "THURSDAY",
		                        "timeSlotStart": "10:30",
		                        "timeSlotEnd": "11:30"
		                    }
		                ]
		            };
	
	for ( var i = 0; i < cs.courseSlots.length; i++) {
		console.log(cs.courseSlots[i].dayOfWeek.toLowerCase().substring(0,3));
		console.log(cs.courseSlots[i].timeSlotStart.split(":")[0]);
		console.log(minutes(cs.courseSlots[i]));
	}

	for ( var i = 0; i < cs.courseSlots.length; i++) {
		$("#"+ cs.courseSlots[i].dayOfWeek.toLowerCase().substring(0,3) + cs.courseSlots[i].timeSlotStart.split(":")[0] + minutes(cs.courseSlots[i])).append('<div class="event">' +
				'<div class="event-name" title="' + cs.courseSlots[i].acronym + '">' + 
				cs.courseSlots[i].acronym + 
				'</div>' +
				'</div>');
	}
});

function minutes(cs) {
	var minutes = cs.timeSlotStart.split(":")[1];
	if (minutes == 30) {
		return 5;
	}
	return 0;
}
