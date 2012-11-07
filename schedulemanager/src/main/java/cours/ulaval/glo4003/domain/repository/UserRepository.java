package cours.ulaval.glo4003.domain.repository;

import java.util.Collection;
import java.util.List;

import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;

public interface UserRepository {

	public User findByIdul(String idul);

	public List<User> findByRole(Role role);

	void store(User user) throws Exception;

	public Collection<User> findAll();

}
