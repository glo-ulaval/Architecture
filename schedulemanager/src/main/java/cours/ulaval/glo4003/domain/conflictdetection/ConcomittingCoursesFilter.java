package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Prerequisite;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.TimeSlot;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;
import cours.ulaval.glo4003.domain.repository.CourseRepository;

public class ConcomittingCoursesFilter extends Filter {

	@Inject
	private CourseRepository repository;

	@Override
	public void run(Schedule schedule) {
		List<Section> sections = schedule.getSectionsList();
		for (int i = 0; i < sections.size(); i++) {
			for (int j = i + 1; j < sections.size(); j++) {
				Section section = sections.get(i);
				Section otherSection = sections.get(j);
				Course course = repository.findByAcronym(section.getCourseAcronym());
				// FIXME pour l'instant, les conflits d'heure de labo ne sont
				// pas vérifiés
				for (TimeSlot sectionCourseTimeSlot : section.getCourseTimeSlots()) {
					for (TimeSlot otherSectionCourseTimeSlot : otherSection.getCourseTimeSlots()) {
						if (sectionCourseTimeSlot.isOverlapping(otherSectionCourseTimeSlot)) {
							for (Prerequisite prerequisite : course.getPrerequisites()) {
								if (prerequisite.containsAcronym(otherSection.getCourseAcronym())
										&& prerequisite.getIsConcomitant()) {
									ConcomittingCoursesConflict conflict = new ConcomittingCoursesConflict(course.getAcronym(),
											otherSection.getCourseAcronym());
									schedule.add(conflict);
								}
							}
						}
					}
				}
			}
		}
	}

	// WARNING -- DO NOT USE -- for test only
	public void setRepo(CourseRepository repo) {
		repository = repo;
	}
}
