package cours.ulaval.glo4003.domain.repository;

import cours.ulaval.glo4003.domain.User;

public interface UserRepository {

	public User findByIdul(String idul);

}
