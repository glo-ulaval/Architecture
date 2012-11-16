$(document).ready(function() {
	$(".conflicts").hide();
	$(".details-conflict").hide();

	$(".show-conflicts").click( function(event) {
		$(this).parent().parent().next(".conflicts").toggle();
		event.preventDefault();
	});
	
	$(".show-details-conflict").click( function(event) {
		$(this).next(".details-conflict").toggle();
		event.preventDefault();
	});

});