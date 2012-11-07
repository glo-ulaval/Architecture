package cours.ulaval.glo4003.domain.conflict;

import cours.ulaval.glo4003.domain.Schedule;

public class Sink extends Pipe {

	@Override
	protected void run(Schedule schedule) {
		calculateTotalScore(schedule);
	}

	private void calculateTotalScore(Schedule schedule) {
		schedule.calculateScore();
	}
}
