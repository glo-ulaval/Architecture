package cours.ulaval.glo4003.domain;

public interface AvailabilityRepository {

	public Availability findByIdul(String idul);

	public void store(Availability availabilities)
			throws Exception;

}
