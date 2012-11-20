package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.ArrayList;
import java.util.List;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public class Sink extends Filter {

	public List<Conflict> run(Schedule schedule) {
		return new ArrayList<Conflict>();
	}
}
