function addRole() {
	var permission = document.getElementById("permissions");
	var span = document.createElement('span');
	span.setAttribute('class', 'per');
	span.innerHTML = "<select class=\"input-large\" name=\"permissions\"value=\"${selected}\"><option>Administrateur</option><option>Directeur de d&eacute;partement</option><option>Enseignant</option><option>Responsable horaire</option><option>Usager r&eacute;gulier</option></select>&nbsp;<a class=\"btn btn-success addRole\" onClick=addRole()><i class=\"icon-plus-sign icon-white\"></i></a>&nbsp;<a class=\"btn btn-danger addRole\" onClick=deleteRole()><i class=\"icon-minus-sign icon-white\"></i></a>";

	permission.appendChild(span);
}

function deleteRole() {
	var permission = document.getElementById("permissions");
	permission.removeChild(permission.lastChild);
}