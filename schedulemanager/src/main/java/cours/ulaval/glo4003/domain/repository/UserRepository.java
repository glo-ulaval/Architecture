package cours.ulaval.glo4003.domain.repository;

import java.util.*;

import cours.ulaval.glo4003.domain.*;

public interface UserRepository {

	public User findByIdul(String idul);

	void store(User user) throws Exception;

	public Collection<User> findAll();

}
