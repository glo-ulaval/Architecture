package cours.ulaval.glo4003.persistence;

import java.util.*;

import cours.ulaval.glo4003.domain.*;
import cours.ulaval.glo4003.domain.repository.*;
import cours.ulaval.glo4003.utils.*;

public class XMLUserRepository implements UserRepository {

	private XMLSerializer<UserXMLWrapper> serializer;
	private Map<String, User> users = new HashMap<String, User>();

	public XMLUserRepository() throws Exception {
		serializer = new XMLSerializer<UserXMLWrapper>(UserXMLWrapper.class);
	}

	public User findByIdul(String idul) {
		return users.get(idul);
	}

	@Override
	public void store(User user) throws Exception {
		users.put(user.getIdul(), user);
		saveXML();
	}

	@Override
	public Collection<User> findAll() {
		return users.values();
	}

	public void saveXML() throws Exception {
		UserXMLWrapper userXMLWrapper = new UserXMLWrapper();
		userXMLWrapper.setUsers(new ArrayList<User>(users.values()));
		serializer.serialize(userXMLWrapper, ConfigManager.getConfigManager().getUsersFilePath());
	}

}
