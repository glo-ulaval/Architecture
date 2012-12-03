package cours.ulaval.glo4003.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Notification {

	// Les messages des notifications
	public static final String NEW_SCHEDULE = "Un nouvel horaire est disponible pour approbation.";
	public static final String SECTION_MODIFIED = "Une de vos section(s) de cours a été modifiée.";

	private int id;
	private String message;
	private String path;

	public Notification() {

	}

	public Notification(String message, String path) {
		this.message = message;
		this.path = path;
		this.id = generateId(Integer.MAX_VALUE);
	}

	private synchronized int generateId(int maximum) {
		return (int) (Math.random() * maximum);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
