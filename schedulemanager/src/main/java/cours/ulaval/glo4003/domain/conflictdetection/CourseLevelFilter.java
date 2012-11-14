package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.List;

import javax.inject.Inject;

import cours.ulaval.glo4003.domain.ProgramSheet;
import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.repository.ProgramSheetRepository;

public class CourseLevelFilter extends Filter {

	@Inject
	private ProgramSheetRepository repository;

	@Override
	public void run(Schedule schedule) {
		List<Section> sections = schedule.getSectionsList();
		for (int i = 0; i < sections.size(); i++) {
			for (int j = i + 1; j < sections.size(); j++) {
				Section section = sections.get(i);
				Section otherSection = sections.get(j);
				if (areSameLevelCourses(section, otherSection)) {
					schedule.addAll(section.generateSameLevelCoursesConflicts(otherSection));
				}
			}
		}
		nextPipe(schedule);
	}

	@Override
	public void nextPipe(Schedule schedule) {
		if (nextPipe != null) {
			nextPipe.run(schedule);
		}
	}

	private boolean areSameLevelCourses(Section section, Section otherSection) {
		ProgramSheet iftProgramSheet = repository.getProgramSheetIFT();
		ProgramSheet gloProgramSheet = repository.getProgramSheetGLO();
		if (gloProgramSheet.areCoursesSameLevel(section.getCourseAcronym(), otherSection.getCourseAcronym())) {
			return true;
		}
		if (iftProgramSheet.areCoursesSameLevel(section.getCourseAcronym(), otherSection.getCourseAcronym())) {
			return true;
		}
		return false;
	}

	public void setRepository(ProgramSheetRepository repository) {
		this.repository = repository;
	}

}
