package cours.ulaval.glo4003.domain.repository;

import cours.ulaval.glo4003.domain.Availability;

public interface AvailabilityRepository {

	public Availability findByIdul(String idul);

	public void store(Availability availabilities)
			throws Exception;

	public void clear()
			throws Exception;

}
