package cours.ulaval.glo4003.domain.conflict;

import cours.ulaval.glo4003.domain.Schedule;

public abstract class Pipe {
	protected Pipe nextPipe;

	protected void run(Schedule schedule) {

	}

	public void connectToPipe(Pipe pipe) {
		nextPipe = pipe;
	}
}
