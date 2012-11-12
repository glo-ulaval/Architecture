package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameTeacherConflict;

public class SameTeacherFilter extends Filter {

	@Override
	public void run(Schedule schedule) {
		List<String> teachersFiltered = new ArrayList<String>();

		for (Section section : schedule.getSectionsList()) {
			for (String teacher : section.getTeachers()) {
				for (Section secondSection : schedule.getSectionsList()) {
					if (secondSection.hasTeacher(teacher) && !secondSection.equals(section)) {
						if (section.isOverlaping(secondSection)) {
							Conflict conflict = new SameTeacherConflict(section.getNrc(), secondSection.getNrc());
							schedule.add(conflict);
						}
					}
				}

				teachersFiltered.add(teacher);

			}
		}
	}
}
