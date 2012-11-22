package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameLevelCourseConflict;
import cours.ulaval.glo4003.domain.repository.ProgramSheetRepository;

public class CourseLevelFilter extends Filter {

	@Inject
	private ProgramSheetRepository repository;

	@Override
	public List<Conflict> run(Schedule schedule) {
		List<Conflict> conflicts = new ArrayList<Conflict>();
		List<Section> sections = schedule.getSectionsList();
		for (int i = 0; i < sections.size(); i++) {
			for (int j = i + 1; j < sections.size(); j++) {
				Section section = sections.get(i);
				Section otherSection = sections.get(j);
				if (section.areSameLevel(otherSection)) {
					conflicts.addAll(generateSameLevelCoursesConflicts(section, otherSection));
				}
			}
		}
		conflicts.addAll(nextFilter.run(schedule));
		return conflicts;
	}

	@Override
	public List<Conflict> run(Schedule schedule, Section section) {
		List<Conflict> conflicts = new ArrayList<Conflict>();
		List<Section> sections = schedule.getSectionsList();
		for (Section otherSection : sections) {
			if (section.areSameLevel(otherSection)) {
				conflicts.addAll(generateSameLevelCoursesConflicts(section, otherSection));
			}
		}
		conflicts.addAll(nextFilter.run(schedule, section));
		return conflicts;
	}

	private List<Conflict> generateSameLevelCoursesConflicts(Section section, Section otherSection) {
		List<Conflict> conflicts = new ArrayList<Conflict>();
		for (TimeSlot sectionTimeSlots : section.getCoursesAndLabTimeSlots()) {
			for (TimeSlot otherSectionTimeSlots : otherSection.getCoursesAndLabTimeSlots()) {
				if (sectionTimeSlots.isOverlapping(otherSectionTimeSlots)) {
					SameLevelCourseConflict conflict = new SameLevelCourseConflict(section.getNrc(), otherSection.getNrc(),
							sectionTimeSlots, otherSectionTimeSlots);
					conflicts.add(conflict);
				}
			}
		}
		return conflicts;
	}

	public void setRepository(ProgramSheetRepository repository) {
		this.repository = repository;
	}

}
