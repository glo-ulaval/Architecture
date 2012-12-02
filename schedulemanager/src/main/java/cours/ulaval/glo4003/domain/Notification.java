package cours.ulaval.glo4003.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Notification {

	// Les messages des notifications
	public static final String NEW_SCHEDULE = "Un nouvel horaire est disponible pour approbation.";
	public static final String SECTION_MODIFIED = "Une de vos section(s) de cours a été modifiée.";

	private String message;
	private String path;

	public Notification() {

	}

	public Notification(String message, String path) {
		this.message = message;
		this.path = path;
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

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
