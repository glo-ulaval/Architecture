package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

	private String idul;
	private String name;
	private String password;
	private List<String> roles = new ArrayList<String>();

	public String getIdul() {
		return idul;
	}

	public void setIdul(String idul) {
		this.idul = idul;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
