package cours.ulaval.glo4003.domain.repository;

import java.util.List;

import cours.ulaval.glo4003.domain.Offering;
import cours.ulaval.glo4003.domain.Semester;

public interface OfferingRepository {

	Boolean containsOfferingFor(String year);

	void delete(String year) throws Exception;

	void delete(String year, Semester semester) throws Exception;

	Offering find(String year);

	List<String> findYears();

	void store(Offering offering) throws Exception;

}
