$(document).ready(function() {
	
	$('#submit').click(function() {
		postTeacherAvailabilities();
	});

	$(".selectable").bind("mousedown", function(e) {
		e.metaKey = true;
	}).selectable();
	
	loadAvailibilitiesFromJSON(json);
});

function postTeacherAvailabilities() {

	availibilitiesJSON = generateAvailibilitiesJSON();

	$.ajax({
		url : 'availabilities/edit',
		type : 'POST',
		data : JSON.stringify(availibilitiesJSON),
		success : function(data) {
			$('#successLabel').show();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#errorLabel').show();
			console.log("Error details: " + errorThrown);
		}
	});
}

function generateAvailibilitiesJSON() {
	return {
		"monday" : [ $('#mon8').hasClass('ui-selected'),
				$('#mon9').hasClass('ui-selected'),
				$('#mon10').hasClass('ui-selected'),
				$('#mon11').hasClass('ui-selected'),
				$('#mon12').hasClass('ui-selected'),
				$('#mon13').hasClass('ui-selected'),
				$('#mon14').hasClass('ui-selected'),
				$('#mon15').hasClass('ui-selected'),
				$('#mon16').hasClass('ui-selected'),
				$('#mon17').hasClass('ui-selected'),
				$('#mon18').hasClass('ui-selected'),
				$('#mon19').hasClass('ui-selected'),
				$('#mon20').hasClass('ui-selected') ],
		"tuesday" : [ $('#tue8').hasClass('ui-selected'),
				$('#tue9').hasClass('ui-selected'),
				$('#tue10').hasClass('ui-selected'),
				$('#tue11').hasClass('ui-selected'),
				$('#tue12').hasClass('ui-selected'),
				$('#tue13').hasClass('ui-selected'),
				$('#tue14').hasClass('ui-selected'),
				$('#tue15').hasClass('ui-selected'),
				$('#tue16').hasClass('ui-selected'),
				$('#tue17').hasClass('ui-selected'),
				$('#tue18').hasClass('ui-selected'),
				$('#tue19').hasClass('ui-selected'),
				$('#tue20').hasClass('ui-selected') ],
		"wednesday" : [ $('#wed8').hasClass('ui-selected'),
				$('#wed9').hasClass('ui-selected'),
				$('#wed10').hasClass('ui-selected'),
				$('#wed11').hasClass('ui-selected'),
				$('#wed12').hasClass('ui-selected'),
				$('#wed13').hasClass('ui-selected'),
				$('#wed14').hasClass('ui-selected'),
				$('#wed15').hasClass('ui-selected'),
				$('#wed16').hasClass('ui-selected'),
				$('#wed17').hasClass('ui-selected'),
				$('#wed18').hasClass('ui-selected'),
				$('#wed19').hasClass('ui-selected'),
				$('#wed20').hasClass('ui-selected') ],
		"thursday" : [ $('#thu8').hasClass('ui-selected'),
				$('#thu9').hasClass('ui-selected'),
				$('#thu10').hasClass('ui-selected'),
				$('#thu11').hasClass('ui-selected'),
				$('#thu12').hasClass('ui-selected'),
				$('#thu13').hasClass('ui-selected'),
				$('#thu14').hasClass('ui-selected'),
				$('#thu15').hasClass('ui-selected'),
				$('#thu16').hasClass('ui-selected'),
				$('#thu17').hasClass('ui-selected'),
				$('#thu18').hasClass('ui-selected'),
				$('#thu19').hasClass('ui-selected'),
				$('#thu20').hasClass('ui-selected') ],
		"friday" : [ $('#fri8').hasClass('ui-selected'),
				$('#fri9').hasClass('ui-selected'),
				$('#fri10').hasClass('ui-selected'),
				$('#fri11').hasClass('ui-selected'),
				$('#fri12').hasClass('ui-selected'),
				$('#fri13').hasClass('ui-selected'),
				$('#fri14').hasClass('ui-selected'),
				$('#fri15').hasClass('ui-selected'),
				$('#fri16').hasClass('ui-selected'),
				$('#fri17').hasClass('ui-selected'),
				$('#fri18').hasClass('ui-selected'),
				$('#fri19').hasClass('ui-selected'),
				$('#fri20').hasClass('ui-selected') ]
	};
}

function loadAvailibilitiesFromJSON(json) {
	for(var i = 0; i < 13; i++){
		if(json.monday[i]){
			var day = i+8;
			$('#mon'+day).addClass('ui-selected');
		}
		if(json.tuesday[i]){
			var day = i+8;
			$('#tue'+day).addClass('ui-selected');
		}
		if(json.wednesday[i]){
			var day = i+8;
			$('#wed'+day).addClass('ui-selected');
		}
		if(json.thursday[i]){
			var day = i+8;
			$('#thu'+day).addClass('ui-selected');
		}
		if(json.friday[i]){
			var day = i+8;
			$('#fri'+day).addClass('ui-selected');
		}
	}
}