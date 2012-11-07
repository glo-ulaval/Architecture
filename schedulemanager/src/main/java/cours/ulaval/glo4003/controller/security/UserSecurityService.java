package cours.ulaval.glo4003.controller.security;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cours.ulaval.glo4003.domain.repository.UserRepository;

public class UserSecurityService implements UserDetailsService {

	@Inject
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		return new UserSecurityDetails(userRepository.findByIdul(username));
	}

}
