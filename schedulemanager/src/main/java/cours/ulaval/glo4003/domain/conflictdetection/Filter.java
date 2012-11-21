package cours.ulaval.glo4003.domain.conflictdetection;

import java.util.List;

import cours.ulaval.glo4003.domain.Schedule;
import cours.ulaval.glo4003.domain.Section;
import cours.ulaval.glo4003.domain.conflictdetection.conflict.Conflict;

public abstract class Filter {
	protected Filter nextFilter;

	public void connectToFilter(Filter filter) {
		nextFilter = filter;
	}

	public abstract List<Conflict> run(Schedule schedule);

	public abstract List<Conflict> run(Schedule schedule, Section section);
}
