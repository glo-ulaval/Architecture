package cours.ulaval.glo4003.controller.security;

import javax.inject.*;

import org.springframework.dao.*;
import org.springframework.security.core.userdetails.*;

import cours.ulaval.glo4003.domain.repository.*;

public class UserSecurityService implements UserDetailsService {

	@Inject
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		return new UserSecurityDetails(userRepository.findByIdul(username));
	}

}
