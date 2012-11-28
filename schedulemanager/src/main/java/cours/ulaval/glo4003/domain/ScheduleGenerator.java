package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.conflictdetection.ConflictDetector;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class ScheduleGenerator {

	@Inject
	private AvailabilityRepository availabilityRepository;

	@Inject
	private ConflictDetector conflictDetector;

	public List<TimeSlot> proposeTimeSlotsForSectionForCourses(Section section, Schedule schedule)
			throws Exception {
		List<TimeSlot> possibleTimeSlots = new ArrayList<TimeSlot>();
		Availability availability = availabilityRepository.findByIdul(section.getTeachers().get(0));
		List<TimeSlot> courseTimeSlots = availability.generatePossibleTimeSlotsForCourse(section.getTimeDedicated()
				.getCourseHours());
		while (possibleTimeSlots.size() < 3 && !courseTimeSlots.isEmpty()) {
			int index = getRandomIndex(courseTimeSlots.size() - 1);
			TimeSlot timeSlot = courseTimeSlots.get(index);
			section.setCourseTimeSlots(Arrays.asList(timeSlot));
			if (conflictDetector.willSectionGenerateConflict(schedule, section)) {
				courseTimeSlots.remove(index);
			} else {
				possibleTimeSlots.add(timeSlot);
				courseTimeSlots.remove(index);
			}
		}
		if (possibleTimeSlots.isEmpty()) {
			throw new Exception(
					"Impossible de proposer une plage horaire de cours qui ne génère pas de conflit pour cette section");
		}
		return possibleTimeSlots;
	}

	private int getRandomIndex(int maximum) {
		return (int) (Math.random() * maximum);
	}

	// DO NOT USE, for tests only
	public void setAvailabilityRepository(AvailabilityRepository availabilityRepository) {
		this.availabilityRepository = availabilityRepository;
	}

	public void setConflictDetector(ConflictDetector conflictDetector) {
		this.conflictDetector = conflictDetector;
	}

}
