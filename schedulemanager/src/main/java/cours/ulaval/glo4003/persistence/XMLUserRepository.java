package cours.ulaval.glo4003.persistence;

import java.util.HashMap;
import java.util.Map;

import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.UserRepository;

public class XMLUserRepository implements UserRepository {

	private Map<String, User> users = new HashMap<String, User>();

	public XMLUserRepository() {

		// Le XML n'est pas encore implémenté alors on remplis la liste
		// directement.
		User teacher = new User("enseignant", "pass", Role.Enseignant);
		users.put(teacher.getIdul(), teacher);
	}

	public User findByIdul(String idul) {
		return users.get(idul);
	}
}
