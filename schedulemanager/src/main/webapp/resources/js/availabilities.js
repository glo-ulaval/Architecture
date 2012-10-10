$(document).ready(function() {

	$('#submit').click(function() {
		postTeacherAvailabilities();
	});

});

function postTeacherAvailabilities() {

	var availibilitiesJSON = {
		"monday" : [ $('#mon8').is(':checked'), $('#mon9').is(':checked'),
				$('#mon10').is(':checked'), $('#mon11').is(':checked'),
				$('#mon12').is(':checked'), $('#mon13').is(':checked'),
				$('#mon14').is(':checked'), $('#mon15').is(':checked'),
				$('#mon16').is(':checked'), $('#mon17').is(':checked'),
				$('#mon18').is(':checked'), $('#mon19').is(':checked'),
				$('#mon20').is(':checked'), ],
		"tuesday" : [ $('#tue8').is(':checked'), $('#tue9').is(':checked'),
				$('#tue10').is(':checked'), $('#tue11').is(':checked'),
				$('#tue12').is(':checked'), $('#tue13').is(':checked'),
				$('#tue14').is(':checked'), $('#tue15').is(':checked'),
				$('#tue16').is(':checked'), $('#tue17').is(':checked'),
				$('#tue18').is(':checked'), $('#tue19').is(':checked'),
				$('#tue20').is(':checked'), ],
		"wednesday" : [ $('#wed8').is(':checked'), $('#wed9').is(':checked'),
				$('#wed10').is(':checked'), $('#wed11').is(':checked'),
				$('#wed12').is(':checked'), $('#wed13').is(':checked'),
				$('#wed14').is(':checked'), $('#wed15').is(':checked'),
				$('#wed16').is(':checked'), $('#wed17').is(':checked'),
				$('#wed18').is(':checked'), $('#wed19').is(':checked'),
				$('#wed20').is(':checked'), ],
		"thursday" : [ $('#thu8').is(':checked'), $('#thu9').is(':checked'),
				$('#thu10').is(':checked'), $('#thu11').is(':checked'),
				$('#thu12').is(':checked'), $('#thu13').is(':checked'),
				$('#thu14').is(':checked'), $('#thu15').is(':checked'),
				$('#thu16').is(':checked'), $('#thu17').is(':checked'),
				$('#thu18').is(':checked'), $('#thu19').is(':checked'),
				$('#thu20').is(':checked'), ],
		"friday" : [ $('#fri8').is(':checked'), $('#fri9').is(':checked'),
				$('#fri10').is(':checked'), $('#fri11').is(':checked'),
				$('#fri12').is(':checked'), $('#fri13').is(':checked'),
				$('#fri14').is(':checked'), $('#fri15').is(':checked'),
				$('#fri16').is(':checked'), $('#fri17').is(':checked'),
				$('#fri18').is(':checked'), $('#fri19').is(':checked'),
				$('#fri20').is(':checked'), ]
	};

	$.ajax({
		url : 'availabilities/edit',
		type : 'POST',
		data : JSON.stringify(availibilitiesJSON),
		success : callback,
		dataType : "json",
		contentType : "application/json"
	});
}

function callback() {
	
	alert('SUCCESS');
}