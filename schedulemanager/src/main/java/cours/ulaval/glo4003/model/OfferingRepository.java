package cours.ulaval.glo4003.model;

import java.util.ArrayList;

public interface OfferingRepository {

	public ArrayList<String> findYears();

	public Offering find(String year);

	public void store(Offering offering);

	public void delete(String year);

}
