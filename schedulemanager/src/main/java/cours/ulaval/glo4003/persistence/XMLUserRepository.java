package cours.ulaval.glo4003.persistence;

import java.util.*;

import cours.ulaval.glo4003.domain.*;
import cours.ulaval.glo4003.domain.repository.*;

public class XMLUserRepository implements UserRepository {

	private Map<String, User> users = new HashMap<String, User>();

	public XMLUserRepository() {

		// Le XML n'est pas encore implémenté alors on remplis la liste
		// directement.

		User teacher = new User("enseignant", "Enseignant", "pass", Role.ROLE_Enseignant);
		users.put(teacher.getIdul(), teacher);

		User director = new User("directeur", "Directeur", "pass", Role.ROLE_Directeur);
		users.put(director.getIdul(), director);

		User responsable = new User("responsable", "Responsable", "pass", Role.ROLE_Responsable);
		users.put(responsable.getIdul(), responsable);

		User administrator = new User("administrateur", "Administrateur", "pass", Role.ROLE_Administrateur);
		users.put(administrator.getIdul(), administrator);

		User utilisateur = new User("utilisateur", "Utilisateur", "pass", Role.ROLE_Usager);
		users.put(utilisateur.getIdul(), utilisateur);
	}

	public User findByIdul(String idul) {
		return users.get(idul);
	}
}
