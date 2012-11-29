$(document).ready(function() {
	$('#spanSuccess').hide();
	$('.loading').hide();
	
	$(".btnCrawl").click( function() {
		$('.loading').show();
		$('.btnCrawl').attr('disabled', 'disabled');
		$.ajax({
			type : "POST",
			url : '/schedulemanager/crawl',
			success : function(data) {
				$('.btnCrawl').removeAttr('disabled');
				$('.loading').hide();
				$('#spanSuccess').show();
			}
		});
	});
});