package cours.ulaval.glo4003.domain.conflict;

import cours.ulaval.glo4003.domain.Schedule;

public class ConflictDetector extends Pipe {

	public void detectConflict(Schedule schedule) {
		// instancier la structure pipe n filter
		startPipe(schedule);
	}

	private void startPipe(Schedule schedule) {
		if (nextPipe != null) {
			nextPipe.run(schedule);
		}
	}

}
