package cours.ulaval.glo4003.domain;

import java.util.List;

public interface OfferingRepository {

	public Boolean containsOfferingFor(String year, Semester semester);

	public List<String> findYears();

	public Offering find(String year, Semester semester);

	public void store(Offering offering)
			throws Exception;

	public void delete(String year, Semester semester)
			throws Exception;

	public void delete(String year)
			throws Exception;

}
