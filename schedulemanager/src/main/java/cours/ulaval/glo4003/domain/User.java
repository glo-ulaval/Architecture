package cours.ulaval.glo4003.domain;

public class User {

	private String idul;

	private String password;

	private Role role;

	public User(String idul, String password, Role role) {
		this.idul = idul;
		this.password = password;
		this.role = role;
	}

	public String getIdul() {
		return idul;
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
}
