package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.domain.repository.UserRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

public class XMLUserRepository implements UserRepository {

	private XMLSerializer<UserXMLWrapper> serializer;
	private Map<String, User> users = new HashMap<String, User>();

	public XMLUserRepository() throws Exception {
		serializer = new XMLSerializer<UserXMLWrapper>(UserXMLWrapper.class);
		parseXML();
	}

	public XMLUserRepository(XMLSerializer<UserXMLWrapper> serializer) {
		this.serializer = serializer;
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

	@Override
	public List<User> findByRole(Role role) {

		List<User> usersWithRole = new ArrayList<User>();

		for (User user : users.values()) {
			if (user.getRoles().contains(role)) {
				usersWithRole.add(user);
			}
		}

		return usersWithRole;
	}

	private void saveXML() throws Exception {
		UserXMLWrapper userXMLWrapper = new UserXMLWrapper();
		userXMLWrapper.setUsers(new ArrayList<User>(users.values()));
		serializer.serialize(userXMLWrapper, ConfigManager.getConfigManager().getUsersFilePath());
	}

	private void parseXML() throws Exception {
		List<User> deserializedUsers = serializer.deserialize(ConfigManager.getConfigManager().getUsersFilePath()).getUsers();
		for (User user : deserializedUsers) {
			users.put(user.getIdul(), user);
		}
	}

}
