package cours.ulaval.glo4003.domain.conflict;

import cours.ulaval.glo4003.domain.Schedule;

public abstract class Pipe {
	private Pipe nextPipe;

	public void nextPipe(Schedule schedule) {

	}

	public void connectToPipe(Pipe pipe) {
		nextPipe = pipe;
	}
}
