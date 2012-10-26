$(document).ready(function() {
	
	$('#submit').click(function() {
		postTeacherAvailabilities();
	});

	$(".selectable").bind("mousedown", function(e) {
		e.metaKey = true;
	}).selectable();
	
	$(".selectable2").bind("mousedown", function(e) {
		e.metaKey = true;
	}).selectable();
	
	$("#btn-dispo").click(function() {
		$(".selectable2").hide();
		$(".selectable").show();
	});
	
	$("#btn-ssi-dispo").click(function() {
		$(".selectable").hide();
		$(".selectable2").show();
	});
	
	console.log(json);
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
		"monday" : setDay("mon"),
		"tuesday" : setDay("tue"),
		"wednesday" : setDay("wed"),
		"thursday" : setDay("thu"),
		"friday" : setDay("fri")
	};
}

function setDay(day) {
	var availabilitiesOfADay = new Array();
	for( var i=0; i < 13; i++) {
		var hour = i+8;
		
		availabilitiesOfADay[i] = setHours(day, hour);
	}
	return availabilitiesOfADay;
}

function setHours(day, hour){
	var available= 0;
	if($('#' + day + hour  + '-1').hasClass('ui-selected')){
		available= 1;
	}
	if($('#' + day + hour  + '-2').hasClass('ui-selected')){
		available= 2;
	}
	return available;
}

function loadAvailibilitiesFromJSON(json) {
	
	var level = "Unavailable, Available, PreferedNotAvailable";
	
	for(var i = 0; i < 13; i++){
		var hour = i+8;
		
		if(json.monday[i] == "Available"){
			$('#mon'+hour+'-1').addClass('ui-selected');
		}
		if(json.tuesday[i] == "Available"){
			$('#tue'+hour+'-1').addClass('ui-selected');
		}
		if(json.wednesday[i] == "Available"){
			$('#wed'+hour+'-1').addClass('ui-selected');
		}
		if(json.thursday[i] == "Available"){
			$('#thu'+hour+'-1').addClass('ui-selected');
		}
		if(json.friday[i] == "Available"){
			$('#fri'+hour+'-1').addClass('ui-selected');
		}
		
		if(json.monday[i] == "PreferedNotAvailable"){
			$('#mon'+hour+'-2').addClass('ui-selected');
		}
		if(json.tuesday[i] == "PreferedNotAvailable"){
			$('#tue'+hour+'-2').addClass('ui-selected');
		}
		if(json.wednesday[i] == "PreferedNotAvailable"){
			$('#wed'+hour+'-2').addClass('ui-selected');
		}
		if(json.thursday[i] == "PreferedNotAvailable"){
			$('#thu'+hour+'-2').addClass('ui-selected');
		}
		if(json.friday[i] == "PreferedNotAvailable"){
			$('#fri'+hour+'-2').addClass('ui-selected');
		}
	}
}