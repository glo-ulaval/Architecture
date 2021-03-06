package cours.ulaval.glo4003.controller.model;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;

public class UserModel {

	private String idul = "";
	private String name = "";
	private String password = "";
	private String emailAddress = "";
	private List<String> roles = new ArrayList<String>();

	public UserModel() {

	}

	public UserModel(User user) {
		idul = user.getIdul();
		name = user.getName();
		password = user.getPassword();
		emailAddress = user.getEmailAddress();
		for (Role role : user.getRoles()) {
			roles.add(role.toString().replace("ROLE_", ""));
		}
	}

	public User convertToUser() {
		User user = new User();
		user.setIdul(idul);
		user.setName(name);
		user.setPassword(password);
		user.setEmailAddress(emailAddress);
		for (String role : roles) {
			user.addRole(Role.valueOf("ROLE_" + role));
		}

		return user;
	}

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
