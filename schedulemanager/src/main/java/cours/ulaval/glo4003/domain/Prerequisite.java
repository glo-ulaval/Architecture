package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Prerequisite {

	private List<String> acronyms = new ArrayList<String>();
	private Boolean isConcomitant;

	public boolean containsAcronym(String acronym) {
		return acronyms.contains(acronym);
	}

	public List<String> getAcronyms() {
		return acronyms;
	}

	public void setAcronyms(List<String> acronyms) {
		this.acronyms = acronyms;
	}

	public Boolean getIsConcomitant() {
		return isConcomitant;
	}

	public void setIsConcomitant(Boolean isConcomitant) {
		this.isConcomitant = isConcomitant;
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
