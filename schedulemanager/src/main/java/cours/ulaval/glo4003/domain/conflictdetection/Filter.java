package cours.ulaval.glo4003.domain.conflictdetection;

import cours.ulaval.glo4003.domain.Schedule;

public abstract class Filter extends Pipe {
	public abstract void run(Schedule schedule);
}