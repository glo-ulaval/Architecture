$(document).ready(function(){
	
	$('button[data-dismiss]').click(function() {
		$(this).parent().hide();
	});
	
});
$('.nav-tabs').button();

function addHours(hours) {
	var element = document.getElementById("hours");
	var html = '';
	for (i=0; i<hours; i++) {
		html += '<input type="text" placeholder="HH:MM" class="input-small"/> &agrave; <input type="text" placeholder="HH:MM" class="input-small"/> <br/>';
	}
	element.innerHTML=html;
};