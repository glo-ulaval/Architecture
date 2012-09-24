package cours.ulaval.glo4003.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Prerequisites {

	private ConditionType condition;

	@XmlElement(name = "prerequisites")
	private ArrayList<Prerequisite> prerequisites = new ArrayList<Prerequisite>();

	public ConditionType getCondition() {
		return condition;
	}

	public ArrayList<Prerequisite> getPrerequisites() {
		return prerequisites;
	}

}
