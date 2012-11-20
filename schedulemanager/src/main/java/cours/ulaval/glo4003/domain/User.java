package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String idul;
	private String name;
	private String password;
	private List<Role> role = new ArrayList<Role>();

	public User() {

	}

	public User(String idul, String name, String password, Role role) {
		this.idul = idul;
		this.name = name;
		this.password = password;
		this.role.add(role);
	}

	public boolean validateCredentials(String password) {
		return this.password.equals(password);
	}

	public void setIdul(String idul) {
		this.idul = idul;
	}

	public String getIdul() {
		return idul;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRoles(List<Role> role) {
		this.role = role;
	}

	public void addRole(Role role) {
		if (!this.role.contains(role)) {
			this.role.add(role);
		}
	}

	public List<Role> getRoles() {
		return role;
	}

}
