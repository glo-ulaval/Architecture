$(document).ready(function() {
	$(".btnAccept").click( function() {
		var scheduleId = $(this).closest('tr').attr('id');
		$.ajax({
			type : "POST",
			url : '/schedulemanager/schedule/accept/' + scheduleId,
			data : {
				status : "Accepted"
			},
			success : function(data) {
				if (data == "success") {
					var choice = $('#' + scheduleId).find('.choice');
					choice.html('<span class="badge badge-success"><i class="icon icon-ok icon-white"></i></span>');
				}
			}
		});
	});
	
	$(".btnRefuse").click( function() {
		var scheduleId = $(this).closest('tr').attr('id');
		$.ajax({
			type : "POST",
			url : '/schedulemanager/schedule/accept/' + scheduleId,
			data : {
				status : "Refused"
			},
			success : function(data) {
				if (data == "success") {
					var choice = $('#' + scheduleId).find('.choice');
					choice.html('<span class="badge badge-important"><i class="icon icon-remove icon-white"></i></span>');
				}
			}
		});
	});
});