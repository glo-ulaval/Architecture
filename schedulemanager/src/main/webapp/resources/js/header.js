$(document).ready(function() {
	if(idul != "") {
		$.ajax({
			type : "POST",
			url : '/schedulemanager/notification/',
			data : {
				idul: idul
			},
			success : function(data) {
				initNotifications(data);
			}
		});
	}
});


function initNotifications(data) {
	if (!isEmpty(data)) {
		enableNotificationBtn();
		var msg = convertData(JSON.parse(data));
		
		$('#notificationBtn').popover({
			placement: "bottom",
			trigger: "click",
			title: "Notifications",
			content: msg
		});
	}
}

function isEmpty(data) {
	return data == "[]";
}

function enableNotificationBtn() {
	$("#notificationBtn").removeClass("disabled");
	$("#notificationBtn i").addClass("icon-white");
}

function convertData(data) {
	var msg = "";
	console.log(data);
	for (var i = 0; i < data.length; i++) {
		msg += '<a href="' + data[i].path + '">' + data[i].message + '</a><br/>';
	}
	
	console.log(msg);
	
	return msg;
}