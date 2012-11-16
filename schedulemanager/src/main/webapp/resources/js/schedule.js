$(document).ready(function() {
	$(".conflicts").hide();
	$(".details-conflict").hide();

	$(".show-conflicts").click( function() {
		$(this).parent().parent().next(".conflicts").toggle();
		event.preventDefault();
	});
	
	$(".show-details-conflict").click( function() {
		$(this).next(".details-conflict").toggle();
		event.preventDefault();
	});

});