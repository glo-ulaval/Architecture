package cours.ulaval.glo4003.domain.repository;

import java.util.Collection;
import java.util.List;

import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;

public interface UserRepository {

	Collection<User> findAll();

	User findByIdul(String idul);

	List<User> findByRole(Role role);

	void store(User user) throws Exception;

}
