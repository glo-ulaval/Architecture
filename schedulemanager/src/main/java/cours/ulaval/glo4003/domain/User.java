package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.notification.AbstractNotification;

public class User {

	private String idul;
	private String name;
	private String password;
	private List<Role> role = new ArrayList<Role>();

	private String emailAdress = "";
	private static final String EMAIL_VALIDATION_REGEX = "^[a-z0-9-]+(\\.[a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.[a-z]{2,6}$";

	private List<AbstractNotification> notifications = new ArrayList<AbstractNotification>();

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

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public boolean hasValidEmailAdress() {
		return emailAdress.matches(EMAIL_VALIDATION_REGEX);
	}

	public void addRole(Role role) {
		if (!this.role.contains(role)) {
			this.role.add(role);
		}
	}

	public List<Role> getRoles() {
		return role;
	}

	public void addNotification(AbstractNotification notification) {
		if (!notifications.contains(notification)) {
			notifications.add(notification);
		}
	}

	public void removeNotification(AbstractNotification notification) {
		notifications.remove(notification);
	}

	public List<AbstractNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<AbstractNotification> notifications) {
		this.notifications = notifications;
	}

	public boolean hasNotification() {
		return notifications.size() > 0;
	}

}
