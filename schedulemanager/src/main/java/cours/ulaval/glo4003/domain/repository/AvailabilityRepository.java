package cours.ulaval.glo4003.domain.repository;

import cours.ulaval.glo4003.domain.Availability;

public interface AvailabilityRepository {

	void clear() throws Exception;

	Availability findByIdul(String idul);

	void store(Availability availabilities) throws Exception;

}
