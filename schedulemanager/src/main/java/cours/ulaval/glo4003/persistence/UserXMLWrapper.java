package cours.ulaval.glo4003.persistence;

import java.util.*;

import javax.xml.bind.annotation.*;

import cours.ulaval.glo4003.domain.*;

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
