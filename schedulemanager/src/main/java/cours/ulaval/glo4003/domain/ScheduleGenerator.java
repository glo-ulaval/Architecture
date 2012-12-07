package cours.ulaval.glo4003.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.conflictdetection.ConflictDetector;
import cours.ulaval.glo4003.domain.exception.NoPossibleTimeSlotsException;
import cours.ulaval.glo4003.domain.repository.AvailabilityRepository;

public class ScheduleGenerator {

	@Inject
	private AvailabilityRepository availabilityRepository;

	@Inject
	private ConflictDetector conflictDetector;

	public List<TimeSlot> proposeTimeSlotsForSectionForCourses(Section section, Schedule schedule) throws Exception {
		List<TimeSlot> possibleTimeSlots = new ArrayList<TimeSlot>();
		Availability availability = availabilityRepository.findByIdul(section.getTeachers().get(0));
		List<TimeSlot> courseTimeSlots = availability.generatePossibleTimeSlotsForCourse(section.getTimeDedicated().getCourseHours());
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
			throw new Exception("Impossible de proposer une plage horaire de cours qui ne génère pas de conflit pour cette section");
		}
		return possibleTimeSlots;
	}

	public List<TimeSlot> proposeTimeSlotsForSectionForLab(Section section, Schedule schedule) throws Exception {
		List<TimeSlot> possibleTimeSlots = new ArrayList<TimeSlot>();
		Availability availability = availabilityRepository.findByIdul(section.getTeachers().get(0));
		List<TimeSlot> labTimeSlots = availability.generatePossibleTimeSlotsForLab(section.getTimeDedicated().getLabHours());
		while (possibleTimeSlots.size() < 3 && !labTimeSlots.isEmpty()) {
			int index = getRandomIndex(labTimeSlots.size() - 1);
			TimeSlot timeSlot = labTimeSlots.get(index);
			section.setLabTimeSlot(timeSlot);
			if (conflictDetector.willSectionGenerateConflict(schedule, section)) {
				labTimeSlots.remove(index);
			} else {
				possibleTimeSlots.add(timeSlot);
				labTimeSlots.remove(index);
			}
		}
		if (possibleTimeSlots.isEmpty()) {
			throw new Exception("Impossible de proposer une plage horaire de laboratoire qui ne génère pas de conflit pour cette section");
		}
		return possibleTimeSlots;
	}

	public Schedule generateSchedule(List<Section> sections) throws Exception {
		Schedule schedule = new Schedule();
		while (!sections.isEmpty()) {
			int sectionIndex = getRandomIndex(sections.size() - 1);
			Section sectionToAdd = sections.get(sectionIndex);
			try {
				if (sectionToAdd.isSupposedToHaveLab()) {
					placeSectionLab(sectionToAdd, schedule);
				}
				placeSectionCourse(sections, schedule, sectionIndex, sectionToAdd);
			} catch (NoPossibleTimeSlotsException e) {
				// Si on attrape ce type d'exception, le cours ne peut pas être
				// placé dans l'horaire
				// TODO implémentation d'une manière de gérer ce cas
				e.printStackTrace();
				throw new Exception("Erreur dans la génération de l'horaire");
			}
		}
		return schedule;
	}

	private void placeSectionCourse(List<Section> sections, Schedule schedule, int sectionIndex, Section sectionToAdd) throws NoPossibleTimeSlotsException {
		Availability availability = availabilityRepository.findByIdul(sectionToAdd.getTeachers().get(0));
		List<TimeSlot> possibleTimeSlots = availability.generatePossibleTimeSlotsForCourse(sectionToAdd.getTimeDedicated().getCourseHours());
		while (!possibleTimeSlots.isEmpty()) {
			int timeSlotIndex = getRandomIndex(possibleTimeSlots.size() - 1);
			TimeSlot timeSlot = possibleTimeSlots.get(timeSlotIndex);
			sectionToAdd.setCourseTimeSlots(Arrays.asList(timeSlot));
			if (conflictDetector.willSectionGenerateConflict(schedule, sectionToAdd)) {
				possibleTimeSlots.remove(timeSlotIndex);
			} else {
				schedule.add(sectionToAdd);
				sections.remove(sectionIndex);
				return;
			}
		}
		throw new NoPossibleTimeSlotsException("Impossible de placer le cours dans l'horaire actuel");
	}

	private void placeSectionLab(Section sectionToAdd, Schedule schedule) throws NoPossibleTimeSlotsException {
		Availability availability = availabilityRepository.findByIdul(sectionToAdd.getTeachers().get(0));
		List<TimeSlot> possibleTimeSlots = availability.generatePossibleTimeSlotsForLab(sectionToAdd.getTimeDedicated().getLabHours());
		while (!possibleTimeSlots.isEmpty()) {
			int timeSlotIndex = getRandomIndex(possibleTimeSlots.size() - 1);
			TimeSlot timeSlot = possibleTimeSlots.get(timeSlotIndex);
			sectionToAdd.setCourseTimeSlots(Arrays.asList(timeSlot));
			if (conflictDetector.willSectionGenerateConflict(schedule, sectionToAdd)) {
				possibleTimeSlots.remove(timeSlotIndex);
			} else {
				return;
			}
		}
		throw new NoPossibleTimeSlotsException("Impossible de placer le lab dans l'horaire actuel");

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
