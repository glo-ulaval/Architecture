package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.domain.User;

@XmlRootElement(name = "users")
public class UserXMLWrapper {
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
