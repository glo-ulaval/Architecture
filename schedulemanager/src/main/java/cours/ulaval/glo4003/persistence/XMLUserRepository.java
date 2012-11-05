package cours.ulaval.glo4003.persistence;

import java.util.*;

import cours.ulaval.glo4003.domain.*;
import cours.ulaval.glo4003.domain.repository.*;

public class XMLUserRepository implements UserRepository {

	private Map<String, User> users = new HashMap<String, User>();

	public XMLUserRepository() {

		// Le XML n'est pas encore implémenté alors on remplis la liste
		// directement.
		User teacher = new User("enseignant", "Enseignant", "pass", Role.Enseignant);
		users.put(teacher.getIdul(), teacher);

		User director = new User("directeur", "Directeur", "pass", Role.Directeur);
		users.put(director.getIdul(), director);
	}

	public User findByIdul(String idul) {
		return users.get(idul);
	}
}
