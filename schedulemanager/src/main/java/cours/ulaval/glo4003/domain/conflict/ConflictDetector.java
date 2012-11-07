package cours.ulaval.glo4003.domain.conflict;

import cours.ulaval.glo4003.domain.Schedule;

public class ConflictDetector extends Pipe {

	public void detectConflict(Schedule schedule) {
		run(schedule);
	}

	@Override
	protected void run(Schedule schedule) {
		// instancier la structure pipe n filter
		// à faire quand les ConcreteFilters seront implémentés
		startPipe(schedule);
	}

	private void startPipe(Schedule schedule) {
		if (nextPipe != null) {
			nextPipe.run(schedule);
		}
	}

}
