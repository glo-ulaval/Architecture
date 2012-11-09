$(document).ready(function() {
	
	$('select').change(function() {
		
		var select = this.options[this.selectedIndex];

		console.log(select.value != 'null');
		
		if (select.value != 'null'){
			$.post('/schedulemanager/getUserRoles', {userToChange:select.value}, function(data){
				$('#rolesCheckBoxes').empty();
				$('#rolesCheckBoxes').html(data);
			});
		} else {
			
			$('#rolesCheckBoxes').empty();
		}
	});
});