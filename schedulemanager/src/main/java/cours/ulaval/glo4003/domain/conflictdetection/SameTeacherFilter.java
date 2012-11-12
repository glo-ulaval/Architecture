package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.List;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameTeacherConflict;

public class SameTeacherFilter extends Filter {

	@Override
	public void run(Schedule schedule) {
		List<Section> sections = schedule.getSectionsList();

		for (int i = 0; i < sections.size(); i++) {
			Section section = sections.get(i);

			for (String teacher : section.getTeachers()) {
				for (int j = i + 1; j < sections.size(); j++) {
					Section secondSection = sections.get(j);
					if (secondSection.hasTeacher(teacher)) {
						if (section.isOverlaping(secondSection)) {
							Conflict conflict = new SameTeacherConflict(section.getNrc(), secondSection.getNrc());
							schedule.add(conflict);
						}
					}
				}
			}
		}
	}
}
