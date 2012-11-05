package cours.ulaval.glo4003.domain;

public class User {

	private String idul;

	private String name;

	private String password;

	private Role role;

	public User(String idul, String name, String password, Role role) {
		this.idul = idul;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public String getIdul() {
		return idul;
	}

	public String getPassword() {
		return password;
	}

	public boolean validateCredentials(String password) {
		return (password == this.password);
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public String getName() {
		return name;
	}
}
