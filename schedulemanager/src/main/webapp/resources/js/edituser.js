$(document).ready(function() {
	
	setRoles();
	
	$('select').bind('change', function() {
		$('#roleAdmin').removeAttr('checked');
		$('#roleDirecteur').removeAttr('checked');
		$('#roleResponsable').removeAttr('checked');
		$('#roleEnseignant').removeAttr('checked');
		$('#roleUsager').removeAttr('checked');
		
		var user = this.options[this.selectedIndex];
		
		setStateOnCheckBoxes(user);
	});

});

function setRoles() {
	var user = $('select option')[0];
	
	setStateOnCheckBoxes(user);
}

function setStateOnCheckBoxes(user) {
	if( ($.inArray('Administrateur', $(user).data('role') )) != -1){
		$('#roleAdmin').attr('checked', 'checked');
	}
	if($.inArray('Directeur', $(user).data('role')) != -1) {
		$('#roleDirecteur').attr('checked', 'checked');
	}
	if($.inArray('Responsable', $(user).data('role')) !=-1) {
		$('#roleResponsable').attr('checked', 'checked');
	}
	if($.inArray('Enseignant', $(user).data('role')) != -1) {
		$('#roleEnseignant').attr('checked', 'checked');
	}
	if($.inArray('Usager', $(user).data('role')) != -1) {
		$('#roleUsager').attr('checked', 'checked');
	}
}