package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.Availability;
import cours.ulaval.glo4003.domain.AvailabilityLevel;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.DisponibilityConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.UnavailableTeacherConflict;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class UnavailableTeacherFilter extends Filter {

	@Inject
	private AvailabilityRepository repository;

	public UnavailableTeacherFilter() {
	}

	@Override
	public void run(Schedule schedule) {
		for (Section section : schedule.getSectionsList()) {
			schedule.addAll(generateUnavailableTeacherConflicts(section, repository));
		}
		nextPipe(schedule);
	}

	@Override
	public void nextPipe(Schedule schedule) {
		if (nextPipe != null) {
			nextPipe.run(schedule);
		}
	}

	public void setRepository(AvailabilityRepository repository) {
		this.repository = repository;
	}

	private List<Conflict> generateUnavailableTeacherConflicts(Section section, AvailabilityRepository repository) {
		List<Conflict> conflicts = new ArrayList<Conflict>();
		for (String teacher : section.getTeachers()) {
			for (TimeSlot timeSlot : section.getCoursesAndLabTimeSlots()) {
				Availability availability = repository.findByIdul(teacher);
				if (availability != null) {
					if (availability.isAvailableForTimeSlot(timeSlot) == AvailabilityLevel.Unavailable) {
						UnavailableTeacherConflict conflict = new UnavailableTeacherConflict(section.getNrc(), teacher, timeSlot);
						conflicts.add(conflict);
					} else if (availability.isAvailableForTimeSlot(timeSlot) == AvailabilityLevel.PreferedNotAvailable) {
						DisponibilityConflict conflict = new DisponibilityConflict(section.getNrc(), teacher, timeSlot);
						conflicts.add(conflict);
					}
				}
			}
		}
		return conflicts;
	}

}
