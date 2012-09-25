package cours.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType
@SuppressWarnings("rawtypes")
public class Prerequisites implements Iterable {

	private List<Prerequisite> prerequisites = new ArrayList<Prerequisite>();

	public List<Prerequisite> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Prerequisite> list) {
		this.prerequisites = list;
	}

	@Override
	public Iterator<Prerequisite> iterator() {
		return prerequisites.iterator();
	}

}
