package cours.ulaval.glo4003.domain.repository;

import java.util.List;

import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;

public interface OfferingRepository {

	public Boolean containsOfferingFor(String year);

	public List<String> findYears();

	public Offering find(String year, Semester semester);

	public void store(Offering offering)
			throws Exception;

	public void delete(String year, Semester semester)
			throws Exception;

	public void delete(String year)
			throws Exception;

}
