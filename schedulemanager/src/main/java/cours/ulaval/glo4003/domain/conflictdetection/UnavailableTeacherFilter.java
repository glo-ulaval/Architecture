package cours.ulaval.glo4003.domain.conflictdetection;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class UnavailableTeacherFilter extends Filter {

	@Inject
	private AvailabilityRepository repository;

	@Override
	public void run(Schedule schedule) {
		for (Section section : schedule.getSectionsList()) {
			schedule.addAll(section.generateUnavailableTeacherConflicts(repository));
		}
	}

	public void setRepository(AvailabilityRepository repository) {
		this.repository = repository;
	}

}
