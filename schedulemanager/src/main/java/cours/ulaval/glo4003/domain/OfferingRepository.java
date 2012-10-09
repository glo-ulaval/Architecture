package cours.ulaval.glo4003.domain;

import java.util.List;

public interface OfferingRepository {

	public List<String> findYears();

	public Offering find(String year);

	public void store(Offering offering) throws Exception;

	public void delete(String year) throws Exception;

}
