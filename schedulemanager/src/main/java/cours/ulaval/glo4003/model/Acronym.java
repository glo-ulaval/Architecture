package cours.ulaval.glo4003.model;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class Acronym {

	@XmlValue
	private String name;

	public String getName() {
		return name;
	}
}
