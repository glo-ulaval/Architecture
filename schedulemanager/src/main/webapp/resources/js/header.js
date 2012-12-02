$(document).ready(function() {
	if(idul != "") {
		var notifications = "";
		$.ajax({
			type : "POST",
			url : '/schedulemanager/notification/',
			data : {
				idul: idul
			},
			success : function(data) {
				$('#notificationBtn').popover({
					placement: "bottom",
					trigger: "click",
					title: "Notifications",
					content: data
				});
			}
		});
	}
});
