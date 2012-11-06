$(function() {
	$(".hour").sortable({
		connectWith : ".hour"
	});

	$(".hour").disableSelection();

	$(document).tooltip();
});