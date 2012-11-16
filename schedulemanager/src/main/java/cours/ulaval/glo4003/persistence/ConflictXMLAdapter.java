package cours.ulaval.glo4003.persistence;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cours.ulaval.glo4003.domain.conflictdetection.conflict.ConcomittingCoursesConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.DisponibilityConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameLevelCourseConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.SameTeacherConflict;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.UnavailableTeacherConflict;

public class ConflictXMLAdapter extends XmlAdapter<Conflict, Conflict> {

	@Override
	public Conflict marshal(Conflict conflict)
			throws Exception {
		return conflict;
	}

	@Override
	public Conflict unmarshal(Conflict conflict)
			throws Exception {
		if (conflict.getScore() == ConcomittingCoursesConflict.SCORE || conflict.getScore() == UnavailableTeacherConflict.SCORE) {
			if (conflict.getTeacher() != null && !conflict.getTeacher().isEmpty()) {
				return new UnavailableTeacherConflict(conflict.getFirstNrc(), conflict.getTeacher(), conflict.getFirstTimeSlot());
			} else {
				return new ConcomittingCoursesConflict(conflict.getFirstNrc(), conflict.getSecondNrc(),
						conflict.getFirstTimeSlot(), conflict.getSecondTimeSlot());
			}
		}
		if (conflict.getScore() == SameLevelCourseConflict.SCORE || conflict.getScore() == SameTeacherConflict.SCORE) {
			if (conflict.getTeacher() != null && !conflict.getTeacher().isEmpty()) {
				return new SameTeacherConflict(conflict.getFirstNrc(), conflict.getSecondNrc(), conflict.getTeacher(),
						conflict.getFirstTimeSlot(), conflict.getSecondTimeSlot());

			} else {
				return new SameLevelCourseConflict(conflict.getFirstNrc(), conflict.getSecondNrc(), conflict.getFirstTimeSlot(),
						conflict.getSecondTimeSlot());
			}
		}
		if (conflict.getScore() == DisponibilityConflict.SCORE && conflict.getTeacher() != "") {
			return new DisponibilityConflict(conflict.getFirstNrc(), conflict.getTeacher(), conflict.getFirstTimeSlot());
		}
		return conflict;
	}

}
