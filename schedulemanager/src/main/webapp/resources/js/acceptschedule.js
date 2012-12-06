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
	
	$(".btnSeeAccepted").bind('hover', function(event) {
		var scheduleId = $(this).closest('tr').attr('id');
		var approvedUsersHtml = '';
		var approvedUsers = $('#' + scheduleId).find('#approvedUsers').val().split(",");
		for (var i=0;i<approvedUsers.length;i++) {
			approvedUsersHtml += approvedUsers[i].replace('[', '').replace(']', '') + '<br>';
		}
		var refusedUsersHtml = '';
		var refusedUsers = $('#' + scheduleId).find('#refusedUsers').val().split(",");
		for (var i=0;i<refusedUsers.length;i++) {
			refusedUsersHtml += refusedUsers[i].replace('[', '').replace(']', '') + '<br>';
		}
		$(".btnSeeAccepted").unbind('hover').popover({title: "Choix des enseignants", content: 
			"<b><span class=\"green\"> Enseignants qui ont accept&eacute; l'horaire : </span></b><br>" + 
			approvedUsersHtml + 
			"<br><b><span class=\"redtext\"> Enseignants qui ont refus&eacute; l'horaire : </span></b><br>" + 
			refusedUsersHtml}).popover('show');
	});
});