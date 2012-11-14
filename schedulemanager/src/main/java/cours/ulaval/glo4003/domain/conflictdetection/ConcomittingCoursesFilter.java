package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.Course;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
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
				if (areConcomitting(section, otherSection)) {
					schedule.addAll(section.generateConcomittingConflicts(otherSection));
				}
			}
		}
	}

	private boolean areConcomitting(Section section, Section otherSection) {
		Course course = repository.findByAcronym(section.getCourseAcronym());
		Course otherCourse = repository.findByAcronym(otherSection.getCourseAcronym());
		if (course.isConcomitting(otherCourse)) {
			return true;
		}
		if (otherCourse.isConcomitting(course)) {
			return true;
		}
		return false;
	}

	// WARNING -- DO NOT USE -- for test only
	public void setRepository(CourseRepository repo) {
		repository = repo;
	}
}
